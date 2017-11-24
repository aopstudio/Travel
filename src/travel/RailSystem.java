package travel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class RailSystem {
	
	public ArrayList<City> cities;
	public ArrayList<String> names;
	public ArrayList<String> strings;
	private String str1;
	private int totalDes;
	private int[] fee = new int [25];
	private int[] path=new int[25];
	private boolean[] set=new boolean[25];
	
	public static void main(String[] args){
		RailSystem system=new RailSystem();
		system.load_services("services.txt");
		//system.print();
		System.out.println("Enter a start and destination city");
		String start;
		String destination;
		Scanner sc=new Scanner(System.in);
		start=sc.next();
		destination=sc.next();
		system.shotestPath(start, destination);
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
		in.close();
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
	/*public void print(){//检验是否读取成功
		for(City city:cities){
			System.out.println(city.getName()+city.edges.get(0).desCity);
		}
	}*/
	public void shotestPath(String fromCity,String desCity){
		int from=names.indexOf(fromCity);
		int des=names.indexOf(desCity);
		int n=cities.size();
		City city=cities.get(from);//找到出发城市
		for(int i=0;i<n;i++){
			if(i==from)
				fee[i]=0;
			fee[i]=Integer.MAX_VALUE;
		}
		Iterator<Service> iter=city.edges.iterator();
		while(iter.hasNext()){
			Service ser=iter.next();
			fee[ser.desCity]=ser.fee;
		}
		for(int i=0;i<n;i++){
			if(i!=from&&fee[i]<Integer.MAX_VALUE)
				path[i]=from;
			else
				path[i]=-1;
		}
		set[from]=true;
		for(int i=0;i<n-1;i++){
			int min=Integer.MAX_VALUE;
			int u=from;
			for(int j=0;j<n;j++)
				if(!set[j]&&fee[j]<min){
					u=j;
					min=fee[j];
				}
			set[u]=true;
			city=cities.get(u);
			iter=city.edges.iterator();
			while(iter.hasNext()){
				Service ser=iter.next();
				if(!set[ser.desCity]&&ser.fee<Integer.MAX_VALUE&&
						fee[u]+ser.fee<fee[ser.desCity]){
					fee[ser.desCity]=fee[u]+ser.fee;
					path[ser.desCity]=u;
				}	
			}
		}
		System.out.println("The cheapest route from "+fromCity+" to "+desCity);
		System.out.println("costs "+fee[des]+" euros and spans "+
				calc_route(from,des)+" kilometers");
		recover_route(from,des);
	}
	public int calc_route(int from,int des){
		int i=des;
		totalDes=0;
		while(i!=from){
			int j=path[i];
			City city=cities.get(j);
			Iterator<Service> iter=city.edges.iterator();
			while(iter.hasNext()){
				Service ser=iter.next();
				if(ser.desCity==i){
					totalDes+=ser.distance;
				}
			}
			i=j;
		}
		return totalDes;
	}
	public void recover_route(int from,int des){
		int i=des;
		Stack<String> route=new Stack<String>();
		String cityName=names.get(i);
		route.push(cityName);
		while(i!=from){
			i=path[i];
			cityName=names.get(i);
			route.push(cityName);
		}
		while(route.size()>1){
			System.out.print(route.pop()+" to ");
		}
		System.out.println(route.pop());
	}
}
