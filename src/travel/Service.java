package travel;

public class Service {
	public int desCity;
	public int fee;
	public int distance;
	public Service next;
	public Service(int desCity,int fee,int distance){
		this.desCity=desCity;
		this.fee=fee;
		this.distance=distance;
	}
	public int getDesCity() {
		return desCity;
	}
	public int getFee() {
		return fee;
	}
	public int getDistance() {
		return distance;
	}
	public void setDesCity(int desCity) {
		this.desCity = desCity;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	

}
