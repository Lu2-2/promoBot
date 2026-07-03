package promobot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PromoBot extends TelegramLongPollingBot {

    SendMessage sendMsg = new SendMessage();

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            //Verifica se tem uma mensagem e se tem texto.

            String userMessage = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            try{
                sendMsg.setChatId(Long.toString(chatId));
                sendMsg.setText("Olá");
                execute(sendMsg);
            } catch (TelegramApiException e) {
                e.getStackTrace();
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
