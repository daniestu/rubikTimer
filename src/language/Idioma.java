package language;

import java.io.IOException;
import java.util.Properties;
 
public class Idioma extends Properties{
 
    private static final long serialVersionUID = 1L;
 
    public Idioma(String idioma){
 
        switch(idioma){
            case "es":
                    getProperties("es.properties");
                    break;
            case "en":
                    getProperties("en.properties");
                    break;
            default:
                    getProperties("es.properties");
        }
 
    }
 
    private void getProperties(String idioma) {
        try {
            this.load( getClass().getResourceAsStream(idioma) );
        } catch (IOException ex) {
 
        }
   }
}
