public class Conteudo {

    private final String titulo;
    private final String urlImage;
    private final double classificacao;

    public Conteudo(String titulo, String urlImage, double classificacao) {
        this.titulo = titulo;
        this.urlImage = urlImage;
        this.classificacao = classificacao;
    }

    
    public Conteudo(String titulo, String urlImage) {
        this(titulo,urlImage,0);
    }

    public String getTitulo() {
        return titulo;
    }
    public String getUrlImage() {
        return urlImage;
    }

    public double getClassificacao() {
        return classificacao;
    }
}
