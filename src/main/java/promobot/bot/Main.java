package promobot.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import promobot.integration.AmazonService;
import promobot.model.TokenResponse;


public class Main{
    public static void main(String[] args){
        PromoBot promoBot = new PromoBot(); //Instanciamento do bot.

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class); //Instanciamento da API do bot.
            botsApi.registerBot(promoBot); //Registar o bot na API.
            
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }



    }
}
