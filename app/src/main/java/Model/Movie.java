package Model;

//Model for Movie
public class Movie {
	private Integer id;
	private String name;
	private String url;
	
	public Movie() {	
	}
	
	public Movie(Integer id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
