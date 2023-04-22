import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        //fazer uma conexão HTTP

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        ExtratorDeConteudo extrator1 = new ExtratorDeConteudoDoIMDB();

       //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
       //ExtratorDeConteudo extrator1 = new ExtratorDeConteudoDaNasa();


       ClienteHttp http = new ClienteHttp();
       String json = http.buscaDados(url);

        //exibir e manipular os dados 
        ImageProcessor Gerar = new ImageProcessor();
        Gerar.novaImagem();

        
        List<Conteudo> conteudos = extrator1.extraiConteudos(json);
        var geradora = new GeradoraDeFigurinhas();
        

        for (int i = 0; i< 10; i++){ //necessário alterar caso mude da API do IMDB para a da Nasa (so vai até 3)

            Conteudo conteudo = conteudos.get(i);

            if(extrator1 instanceof ExtratorDeConteudoDoIMDB){
                if(conteudo.getClassificacao() >= 9.0){
                    extrator1.getTextoFigurinha = "Excelente";
                    extrator1.getMemojiBea = new FileInputStream(new File("sobreposicao/imagem_filmebom.jpeg"));
                }else{
                    extrator1.getTextoFigurinha = "Ruim";
                    extrator1.getMemojiBea = new FileInputStream(new File("sobreposicao/imagem_tedio.jpeg"));
                }
            }else{
                extrator1.getTextoFigurinha = "Nasa";
                extrator1.getMemojiBea = new FileInputStream(new File("sobreposicao/imagem_filmebom.jpeg"));
            }
            InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();
            String nomeArquivo = conteudo.getTitulo() +".png";
            
            geradora.cria(inputStream, nomeArquivo,extrator1.getTextoFigurinha, extrator1.getMemojiBea);
            

            System.out.println(conteudo.getTitulo());
            //System.out.println(conteudo.getClassificacao());
       }
    }

}
