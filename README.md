<h1>TecPromos Bot</h1>

Um bot desenvolvido em Java projetado para integrar APIs de e-commerce e automatizar a busca e divulgação de ofertas e promoções.

## 🚀 Funcionalidades e Integrações

O **TecPromos Bot** conecta-se às principais plataformas de e-commerce para obter e formatar informações de produtos:

* **Amazon:** Integração para consulta de produtos, preços, imagens e gerenciamento de tokens.
* **AliExpress:** Gerenciamento de autenticação, renovação de tokens e busca de ofertas.
* **Mercado Livre:** Serviço de integração para busca e captura de ofertas.

## 🛠️ Tecnologias Utilizadas

* **Java** (JDK 21 ou superior).
* **Maven** (Gerenciamento de dependências via `pom.xml`).
* **APIs REST** (Amazon PA-API, AliExpress API, Mercado Livre API).

## 📁 Estrutura do Projeto

```text
promobot/
├── bot/
│   ├── Main.java                 # Ponto de entrada da aplicação
│   └── PromoBot.java             # Fluxo principal de execução do bot
├── integration/
│   ├── AliexpressService.java    # Comunicação com a API do AliExpress
│   ├── AmazonService.java        # Comunicação com a API da Amazon
│   └── MercadoLivreService.java  # Comunicação com a API do Mercado Livre
├── model/                        # DTOs de resposta das APIs e modelos de dados
│   ├── amazon/                   # Mapeamentos de resposta da Amazon
    ├── AliexpressTokenResponse.java # Resposta da requisição de Token Aliexpress
    ├── AmazonTokenResponse.java     # Resposta da requisição de Token Amazon
│   └── Loja.java
└── service/
    └── LegendaDoProduto.java      # Formatação e montagem das mensagens de oferta
```
