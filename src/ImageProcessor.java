import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessor {
    public static BufferedImage removeBackground(BufferedImage inputImage, Color backgroundColor) {
        // cria uma nova imagem do mesmo tamanho da imagem de entrada
        BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // percorre todos os pixels da imagem de entrada
        for (int y = 0; y < inputImage.getHeight(); y++) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                // obtém a cor do pixel atual
                Color color = new Color(inputImage.getRGB(x, y), true);

                // verifica se o pixel é parte do fundo
                if (color.equals(backgroundColor)) {
                    // define a transparência do pixel para 0
                    color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 0);
                }

                // define a cor do pixel na nova imagem
                outputImage.setRGB(x, y, color.getRGB());
            }

        }

        // retorna a nova imagem sem o fundo
        return outputImage;
    }

    public void novaImagem() throws IOException{
        
        // diretório com as imagens
        File dir = new File("sobreposicao");
        // lista de arquivos JPEG no diretório
        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".jpeg"));

        // define a cor do fundo que deve ser removido (branco)
        Color backgroundColor = Color.WHITE;

        // percorre todos os arquivos de imagem
        for (File file : files) {
        // carrega a imagem JPEG
        BufferedImage inputImage = ImageIO.read(file);

        // remove o fundo branco da imagem
        BufferedImage outputImage = ImageProcessor.removeBackground(inputImage, backgroundColor);

        // salva a imagem processada sem o fundo
        String outputFileName = "imagem_" + file.getName();
        File outputFile = new File(dir, outputFileName);
        ImageIO.write(outputImage, "png", outputFile);
    }

}
}