package promobot.bot;

import java.util.Locale;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import promobot.model.Loja;
import promobot.model.Produto;
import promobot.service.LegendaDoProduto;

public class PromoBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){ //Verifica se tem uma mensagem e se tem texto.

            SendPhoto sendPhoto = new SendPhoto();
            Produto produto = new Produto(
                    /* --- PARA TESTE ---
                    Loja.AMAZON,
                    "https://m.media-amazon.com/images/I/71Z9DLS29FL._AC_SL1500_.jpg",
                    "MSI GeForce RTX 3050 LP 6G OC (6GB GDDR6/PCI Express 4.0/1492MHz/14000MHz/Perfil baixo)",
                    999.00,
                    "https://link.amazon/B0gBtRtcv" asdadasd*/
            );
            LegendaDoProduto legendaProduto = new LegendaDoProduto();

            String userMessage = update.getMessage().getText(); //Pega a mensagem.
            Long chatId = update.getMessage().getChatId(); //Pega o Id do chat.

            try{
                sendPhoto.setChatId(Long.toString(chatId)); //Adiciona o Id do chat para enviar a mensagem.
                sendPhoto.setPhoto(new InputFile(legendaProduto.fotoProduto(produto))); //Função para pegar a foto do link.
                sendPhoto.setCaption(legendaProduto.legendaFormatada(produto)); //Função para pegar a legenda feita.

                execute(sendPhoto); //Envia a menssagem.

            } catch (TelegramApiException e) {
                e.printStackTrace(); //Retorna o erro.
                return;
            }

        }
    }

    @Override
    public String getBotUsername() {
        //Pega o username do bot
        return "TecPromoos_bot";
    }

    @Override // Verifica se existe
    public String getBotToken(){
        //Token para validação no Telegram
        return "8987868496:AAHRFgyPafd8JK9ItZyvNLI2xWY58zQXBhc";
    }
}
