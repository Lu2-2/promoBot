package promobot.model;

public class Produto {

    private Loja loja;
    private String link;
    private String foto;
    private String nomeProduto;
    private double precoPromocional;

    public Produto(Loja loja, String foto, String nomeProduto, double precoPromocional , String link){
        this.loja = loja;
        this.foto = foto;
        this.nomeProduto = nomeProduto;
        this.precoPromocional = precoPromocional;
        this.link = link;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(double precoPromocional) {
        this.precoPromocional = precoPromocional;
    }

    public Produto(){}
}