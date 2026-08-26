package promobot.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class Main{
    public static void main(String[] args){
        PromoBot promoBot = new PromoBot(); //Instanciamento do bot.

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class); //Instanciamento da API do bot.
            botsApi.registerBot(promoBot); //Registar o bot na API.
            
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        /* Produto Amazon:
            Medium -> url; Link da imagem
            Title -> displayValue; Titulo
            Money -> displayAmount; Valor com R$
            Item -> detailPageURL; Link

        */


        /* Teste: Renovação de Token Aliexpress
        AliexpressService aliexpressService = new AliexpressService();
        AliexpressRefreshToken refreshToken = null;
        try {
            refreshToken = aliexpressService.renovarToken("50001200627rvHtJes9nEpyqxb8YK0CKRdWRDZF115cce43cq7sy2ElguJ8c5vqoTNYv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(refreshToken.getAccessToken());
        System.out.println(refreshToken.getRefreshToken());

        */
    }
}
