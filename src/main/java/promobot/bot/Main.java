package bot.promobot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class Main{
    public static void main(String[] args){
        PromoBot promoBot = new PromoBot();

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(promoBot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
