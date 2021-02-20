import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Parking {
	//parking lot
	private static final int MAX_PARKING = 10;
	private static final int MAX_HOURS = 24; //limit of time for a parked car
	private Random rand = new Random();
	public int parkedCars;
	
	private ArrayList<Car> carList = new ArrayList<Car>(this.MAX_PARKING);
	
	public Parking(){
		System.out.println("Parcarea se deschide!");
		this.start();
	}
	
	private void showAvailableCommands(){
		System.out.println("Available commands");
		System.out.println("push: \t Add new car in parking lot");
		System.out.println("pop:  \t Remove car from parking lot");
		System.out.println("popcar:  Remove car from parking lot specifying the license plate");
		System.out.println("space: \t Show free parking spaces");
		System.out.println("cars: \t Show cars in the parking");
		System.out.println("stop: \t Stop the program");
		
	}
	
	private void popCarByLicensePlate(){
		this.showParkedCars(); 
		Scanner input = new Scanner(System.in);
		boolean ok = true;
		System.out.println("Introduceti numarul masinii de eliminat");
		if(this.parkedCars>0){
			while(ok){
				String a = input.nextLine();
			
				for(int i=0; i<this.parkedCars; i++){
					if(carList.get(i).getLicensePlate().equals(a.toUpperCase())){
						System .out.println("Masina cu numarul "+carList.get(i).getLicensePlate()+" a parasit parcarea");
						System.out.println("Pret parcare "+this.getPriceForParking(carList.get(i).getParkedTime())+" lei");
						carList.remove(i);
						this.parkedCars--;
						ok=false;
						break;
					}
				}
				if(ok){		
					this.showParkedCars();
					System.out.println("Numarul introdus nu este valid! Reincercati  cu unul de mai sus!");
				}
			}
		}else{
			System.out.println("Parcarea este goala!");
		}
	}
	
	private void start(){
		this.parkingInitialization();
		this.showAvailableCommands();
		Scanner input = new Scanner(System.in);
		String action = input.nextLine();
		
		while(!action.equals("stop")) {       
	        
			switch(action) {
	        case "push": this.pushCar();
	        	break;
	        
	        case "pop": this.popCar();
	        	break;
	        
	        case "popCar": this.popCarByLicensePlate();
	        	break;
	        case "popcar": this.popCarByLicensePlate();
        		break;
        	
	        case "space": this.showAvailableLots();
	        	break;
	        
	        case "cars": this.showParkedCars();
	        	break;	
	        	
	        default: 
	    		System.out.println("Command not found!");
	    		this.showAvailableCommands();
	        	break;
	        }
	        System .out.println("Asteptare comanda....");
	        action = input.nextLine();    
		}
		
		System.out.println("Parcarea s-a inchis!");
	}
	
	private void parkingInitialization(){	  
        // Generate random integer for initial cars in Parking 
        int parkedCars1 = rand.nextInt(this.MAX_PARKING+1);
        //int parkedCars1 = 0;
		for(int i = 0; i < parkedCars1; i++ ){
        	Car a = new Car(MAX_HOURS);
        	carList.add(a);
        }
        this.setParkedCars(parkedCars1);
        this.showParkedCars();
	}
	
	private void pushCar(){
		
		if(this.parkedCars < MAX_PARKING){
			Car a = new Car(MAX_HOURS);
			carList.add(a);
			this.setParkedCars(this.parkedCars+1);
			System.out.println("Masina a fost adaugata in parcare");
			Car last = carList.get(carList.size() - 1);
			System.out.println("Masina are numarul de inmatriculare: "+ last.getLicensePlate());
			this.showAvailableLots();
		}
		else {
			System.out.println("Parcarea este plina!");
		}
	}
	
	private void popCar(){
		//remove random car
		if(this.parkedCars>0){
			int index = rand.nextInt(this.parkedCars);
			System.out.println("Masina cu numarul: " + carList.get(index).getLicensePlate()+" va fi eliminata");
			System.out.println(this.parseTime(carList.get(index).getParkedTime()));
			System.out.println("Pret parcare "+this.getPriceForParking(carList.get(index).getParkedTime())+" lei");
			carList.remove(index);
			this.setParkedCars(this.parkedCars-1);
		} else {
			System.out.println("Parcarea este goala!");
		}
	}
	
	private String parseTime(int t){
		int hours = t / 60; 
		int minutes = t % 60;
		String s = new String();
		s="Masina a fost stationata "+String.valueOf(hours)+" ore si "+String.valueOf(minutes)+" minute";
		return s;
	}
	
	private int getPriceForParking(int t){
		int price = 10;
		int hours = t / 60; 
		int minutes = t % 60;
		if(minutes>=1)
			hours++;
		if(hours >=1)
			price += (hours-1)*5;
		
		return price;
		
	}
	
	private void showAvailableLots(){
		System.out.println("Locuri disponibile in parcare: "+ (MAX_PARKING-this.getParkedCars()));
	}
	
	private void showParkedCars(){
		if(this.parkedCars==0){
		System.out.println("Parcarea este goala! ");
		} else
		for(int i=0; i < this.carList.size(); i++)
		{
			System.out.println("Masina cu numarul: "+ this.carList.get(i).getLicensePlate());
			System.out.println(this.parseTime(this.carList.get(i).getParkedTime()));
		}
		System.out.println("In parcare se afla "+this.parkedCars+" masini");
	}
	public int getParkedCars() {
		return this.parkedCars;
	}

	public void setParkedCars(int p) {
		this.parkedCars = p;
	}
}
