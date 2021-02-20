import java.security.SecureRandom;
import java.util.Random;

public class Car {
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public final int licensePlate_LEN = 7;
	static SecureRandom rnd = new SecureRandom();
	Random rand = new Random(); 
	private int maxParkingTime;
	
	private String licensePlate;
	private int parkedTime;
	
	public Car(int maxTime){
		this.maxParkingTime = maxTime;
		
		generateLicensePlate(licensePlate_LEN);
		generateParkedTime(maxParkingTime*60);
	}
	
	//In a real parking we would scan the license plate for every car that enter/exit the park
	public void generateLicensePlate(int len){
		   StringBuilder sb = new StringBuilder(len);
		   for(int i = 0; i < len; i++)
			   sb.append(AB.charAt(rnd.nextInt(AB.length())));
		   
		   this.setLicensePlate(sb.toString());
		   //return sb.toString();
	}
	
	public void generateParkedTime(int maxTime){
        int rand_int = rand.nextInt(maxTime);
        this.setParkedTime(rand_int);
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getParkedTime() {
		return parkedTime;
	}

	public void setParkedTime(int parkedTime) {
		this.parkedTime = parkedTime;
	}
	
	
}
