import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoIMDB {
    
    public List<Conteudo> extractContent (String json){
        
        var parser = new JsonParser();
		List<Map<String, String>>listOfAttributes = parser.parse(json);

        List <Conteudo> contents = new ArrayList<>();

        for (Map<String, String> attributes : listOfAttributes) {
            
            String title = attributes.get("title");
            String urlImage = attributes.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            var content = new Conteudo(title, urlImage);

            contents.add(content);
        }

        return contents;

    }

}
