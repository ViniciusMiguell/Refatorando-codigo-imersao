import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.swing.JOptionPane;

public class App {

	public static void main(String[] args) throws Exception {
		
		String[] opc = {"Stickers de Filmes", "Sair"};
		var generator = new GeradorFigurinhas();
		while (true){

			char rep=0;
			int selectedOption = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Gerador de Stickers", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opc, opc[0]);

			switch (selectedOption){
				case 0:
					String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
					//String url = "https://api.nasa.gov/planetary/apod?api_key=SzfFRg5gbc3hQb71WStsdYgi0O5Yir5Y99gIxG9M"; 
					
					ClienteHttp http = new ClienteHttp();
					String json = http.searchData(url);
					
					ExtratorConteudoNasa extract = new ExtratorConteudoNasa();
					List<Conteudo> contents = extract.extractContent(json);

					for (int i=0;i<3;i++) {

						Conteudo content = contents.get(i);

						InputStream inputStream = new URL(content.getUrlImage()).openStream();
						String nomeArquivo = "saida/" + content.getTitle() + ".png";

						generator.cria(inputStream, nomeArquivo);
			
						System.out.println(content.getTitle());
						System.out.println();

					}
					break;

				case 1:
					rep=1;
					break;
			}
			if (rep==1) {
				break;
			}
		}
	}
}