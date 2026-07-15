package promobot.bot;

import java.util.Locale;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import promobot.model.Loja;
import promobot.model.Produto;

public class PromoBot extends TelegramLongPollingBot {

    SendPhoto sendPhoto = new SendPhoto();

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            //Verifica se tem uma mensagem e se tem texto.

            String userMessage = update.getMessage().getText(); //Pega a mensagem.
            Long chatId = update.getMessage().getChatId(); //Pega o Id do chat.

            Produto product = new Produto(
                    Loja.AMAZON,
                    "https://m.media-amazon.com/images/I/71Z9DLS29FL._AC_SL1500_.jpg",
                    "MSI GeForce RTX 3050 6GB GDDR6 | 6G, OC, PCI Express 4.0, 1492MHz, 14000MHz, Perfil Baixo",
                    999.00,
                    "https://link.amazon/B0hrlaHMa"

            );

            try{
                String precoFormatado = String.format(Locale.of("pt", "BR"), "%.2f", product.getPrecoPromocional());
                String legenda = "\uD83D\uDD25 " + product.getNomeProduto() + "\n\n" + "\uD83D\uDCB0 Valor: R$" + precoFormatado + "\n" + product.getLink();

                sendPhoto.setChatId(Long.toString(chatId)); //Adiciona o Id do chat para enviar a mensagem.
                sendPhoto.setPhoto(new InputFile(product.getFoto()));
                sendPhoto.setCaption(legenda);

                execute(sendPhoto);

            } catch (TelegramApiException e) {
                e.printStackTrace(); //Printa o erro.
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
