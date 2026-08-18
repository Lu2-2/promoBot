pom.xml
src/main/java/promobot/
├── bot/
│   ├── Main.java              # ponto de entrada
│   └── PromoBot.java          # lógica principal do bot / registro no Telegram
├── integration/
│   ├── AmazonService.java     # integração com API de afiliados Amazon
│   ├── AliexpressService.java # integração AliExpress (pausada — auth SHA-256)
│   └── MercadoLivreService.java # integração Mercado Livre
├── model/                     # DTOs mapeando o JSON das APIs
│   ├── Item.java, ItemInfo.java, Produto.java, Loja.java
│   ├── Price.java, Money.java, Title.java, Images.java, Medium.java, Primary.java
│   ├── Listings.java, OffersV2.java
│   ├── SearchResponse.java, SearchResult.java, TokenResponse.java
└── service/
    └── LegendaDoProduto.java  # geração da legenda/texto da mensagem promocional
