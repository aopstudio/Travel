package travel;

import java.util.LinkedList;

public class City {
	private String name;
	private City fromCity;
	private int shortest;
	public LinkedList<Service> edges;
	
	public City(){
		edges=new LinkedList<Service>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
