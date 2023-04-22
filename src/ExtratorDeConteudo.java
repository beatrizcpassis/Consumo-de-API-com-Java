import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public abstract class ExtratorDeConteudo {

    private String textoFigurinha;
    private InputStream memojiBea;
    public String getTextoFigurinha;
    public FileInputStream setMemojiBea;
    public String setTextoFigurinha;
    public InputStream getMemojiBea;
    
    abstract List<Conteudo> extraiConteudos(String json);

    public void setTextoFigurinha(String textoFigurinha) {
        this.textoFigurinha = textoFigurinha;
    }

    public void setMemojiBea(InputStream memojiBea) {
        this.memojiBea = memojiBea;
    }
    
    public String getTextoFigurinha() {
        return textoFigurinha;
    }

    public InputStream getMemojiBea() {
        return memojiBea;
    }
  
}
