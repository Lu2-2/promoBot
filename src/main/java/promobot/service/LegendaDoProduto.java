package promobot.service;

import promobot.model.Loja;
import promobot.model.amazon.Produto;

import java.util.Locale;

public class LegendaDoProduto {

    public String fotoProduto(Produto produto){

        String fotoProduto = produto.getFoto();
        return fotoProduto;
    }

    public String legendaFormatada(Produto produto){
        String precoFormatado = String.format(Locale.of("pt", "BR"), "%.2f", produto.getPrecoPromocional()); //Adiciona os centavos com vírgula.
        String legenda = "\uD83D\uDED2 #" + Loja.AMAZON + "\n\n" + "\uD83D\uDD25 " + produto.getNomeProduto() + "\n\n" + "\uD83D\uDCB0 Valor: R$" + precoFormatado + "\n" + produto.getLink();

        return legenda;
    }

}


