import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    

    public void cria(InputStream inputStream, String nomeArquivo, String texto, InputStream inputStreamSobreposicao) throws Exception {

        // leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg")); //opção 1 - lendo imagem do pc
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream(); //opção 2 - lendo uma imagem de uma url
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        BufferedImage imagemSobreposicao = ImageIO.read(inputStreamSobreposicao);
        int sopreposicaoY = novaAltura - imagemSobreposicao.getHeight();
        graphics.drawImage(imagemSobreposicao, 0, sopreposicaoY, null);

        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.RED);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraTexto) / 2;

        graphics.drawString(texto, posicaoTextoX, novaAltura - 100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }
}