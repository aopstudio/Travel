package travel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RailSystem {
	
	public ArrayList<City> cities;
	public ArrayList<String> names;
	public ArrayList<String> strings;
	private String str1;
	private int[] dist = new int [25]; 
	private int[] path=new int[25];
	
	public static void main(String[] args){
		RailSystem system=new RailSystem();
		system.load_services("services.txt");
		system.print();
	}
	public RailSystem(){
		
	}
	
	public void load_services(String filename){
		
		cities=new ArrayList<City>();
		names=new ArrayList<String>();
		strings=new ArrayList<String>();
		City city = null;
		FileReader reader = null;
		try {
			reader = new FileReader(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner in=new Scanner(reader);
		
		try{
			String line=in.nextLine();
			strings.add(line);
			StringTokenizer st=new StringTokenizer(line," ",false);
			str1=st.nextToken();//str1代表第一行的第一个城市
			city=new City();
			city.setName(str1);
			cities.add(city);
			names.add(str1);
		}
		catch(NullPointerException p){
			p.printStackTrace();
		}
		
		while(in.hasNextLine()){//第一次读取，先把出发城市全部填入
			String line=in.nextLine();
			strings.add(line);
			StringTokenizer st=new StringTokenizer(line," ",false);
			String str2=st.nextToken();
			if(!str2.equals(str1)){
				city=new City();
				city.setName(str2);
				cities.add(city);
				names.add(str2);
				str1=str2;
			}	
			
			
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//第二次读取，添加边的信息
		
		
		for(String line:strings){
			StringTokenizer st=new StringTokenizer(line," ",false);
			String from=st.nextToken();
			int fromNumber=names.indexOf(from);
			
			city=cities.get(fromNumber);
			String toCity=st.nextToken();
			int toNumber=names.indexOf(toCity);
			Service edge=new Service(toNumber,Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			city.edges.add(edge);
			cities.set(fromNumber, city);
			
		}
		

	}
	public void print(){//检验是否读取成功
		for(City city:cities){
			System.out.println(city.getName()+city.edges.get(0).desCity);
		}
	}
	public void calc_route(){
		
	}
	public void recover_route(){
		
	}


}
