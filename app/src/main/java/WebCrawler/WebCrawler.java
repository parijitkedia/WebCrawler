package WebCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Connection.Connect;
import Model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WebCrawler {
	private static final String URL = "https://www.themoviedb.org/";
	private static List<Movie> list;
	
	public WebCrawler() {
		list = new ArrayList<Movie>();
	}
	
    public void getMovieData(Queue<String> queue) {

		try {
			while(!queue.isEmpty()) {
				String url = queue.remove();
				
				int page = 1;
				
				//limiting page number to 10;
				while(page < 10) {
					url = url + "?page="+page;
					Document document = Jsoup.connect(url).get();
				    
				    Elements cardStyle = document.select("div.style_1");
				
			        for (Element card : cardStyle) {
			           Element title = card.selectFirst("a[title]");
			           Element link = card.selectFirst("img[src]");
			           
			           //getting title and image data
			           if (title != null && link != null) {
			        	   Movie data = new Movie();
				           data.setName(title.attr("title"));
				           data.setUrl(link.attr("src"));
				           list.add(data);
			           }
			        }
			        page++;
				}
			   		
			}
			//Insert After collecting all links otherwise writing to a DB is slow
			new Connect().insertToDb(list);
		    
		} catch (IOException e) {
		    System.err.println("For '" + URL + "': " + e.getMessage());
		}
	}
    

    public static void main(String[] args) {
    	// Initial research gave all links available on website which did not return movies, so hardcoded the links that only gave movies. 
    	Queue<String> queue = new LinkedList<>();
    	queue.add("https://www.themoviedb.org/movie/now-playing");
    	queue.add("https://www.themoviedb.org/movie/upcoming");
    	queue.add("https://www.themoviedb.org/movie/top-rated");
    	
        new WebCrawler().getMovieData(queue);
    	
    }
}
