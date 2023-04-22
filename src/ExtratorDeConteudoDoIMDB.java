import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB extends ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json){
        
        //extrair só os dados que interessam(titulo,poster,nota)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        //popular a lista de conteudos

        for (Map<String, String> atributos: listaDeAtributos) {

            String urlImage = atributos.get("image");
            String titulo = atributos.get("title");
            String nota = atributos.get("imDbRating");
            double classificacao = Double.parseDouble(nota);
            Conteudo conteudo = new Conteudo(titulo, urlImage,classificacao);

            //Mudando a partir da nota (imagem e texto)
            
            conteudos.add(conteudo);

            
        }

        return conteudos;

    }

}
