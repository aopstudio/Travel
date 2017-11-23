package travel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RailSystem {
	
	public RailSystem() {
		// TODO Auto-generated constructor stub
	}
	public void load_services(String filename){
		FileReader reader = null;
		try {
			reader = new FileReader(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner in=new Scanner(reader);
		String line;
		/*while(in.hasNextLine()){
			line=in.nextLine();
			//这边别忘了加
		}*/
		in.close();
	}
	public void loadCities(String line){
		StringTokenizer st=new StringTokenizer(line," ",false);
		City city=new City();
		
	}
	public void calc_route(){
		
	}
	public void recover_route(){
		
	}


}
