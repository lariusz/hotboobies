package pl.hotboobies;


public class Grupa {
	
	private String nazwa;
	private int id;

	public Grupa(int id, String nazwa) {
		super();
		this.id = id;
		this.nazwa = nazwa;
	}	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
}
