package in.conceptarchitect.animals.app;

import in.conceptarchitect.animals.Animal;
import in.conceptarchitect.animals.Domestic;
import in.conceptarchitect.animals.Hunter;
import in.conceptarchitect.animals.Rideable;
import in.conceptarchitect.animals.birds.Eagle;
import in.conceptarchitect.animals.birds.Parrot;
import in.conceptarchitect.animals.mammals.Camel;
import in.conceptarchitect.animals.mammals.Cow;
import in.conceptarchitect.animals.mammals.Dog;
import in.conceptarchitect.animals.mammals.Horse;
import in.conceptarchitect.animals.mammals.Leopard;
import in.conceptarchitect.animals.mammals.Tiger;
import in.conceptarchitect.animals.reptiles.Crocodile;
import in.conceptarchitect.animals.reptiles.Snake;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Animal [] animals= {
				new Tiger(),
				new Horse(),
				new Leopard(),
				//new Mammal(),
				//new Bird(),
				//new CatFamily(),
				new Snake(),
				new Eagle(),
				//new Reptile(),
				new Horse(),
				new Dog(),
				new Camel(), 
				new Parrot(),
				new Crocodile(),
				//new Animal(),
				new Cow()
		};
		
		for(var animal : animals) {
			
			
			
			
			
			
			
			
			String domestic="";
			
			if(animal.isDomestic())
				domestic=" **** ";
			
			
			System.out.println(domestic+animal.toString());
			System.out.println("\t"+animal.eat());
			System.out.println("\t"+animal.move());
			System.out.println("\t"+animal.breed());
			
			//letTigerHunt(animal);
			
			letHunterHunt(animal);
			
			letRideableRide(animal);
			
			System.out.println("\n");
			
			
			
		}

	}
	
	private static void letRideableRide(Animal animal) {
		// TODO Auto-generated method stub
		if(animal instanceof Rideable) {
			Rideable rideable=(Rideable) animal;
			System.out.println("\t"+rideable.ride());
		}
	}

	private static void letHunterHunt(Animal animal) {
		// TODO Auto-generated method stub
		if(animal instanceof Hunter) {
			Hunter hunter=(Hunter) animal;
			System.out.println("\t"+hunter.hunt());
		}
	}

	private static void letTigerHunt(Animal animal) {
		// TODO Auto-generated method stub
		
		if( animal instanceof Tiger) {
			Tiger tiger= (Tiger) animal;
			System.out.println("\t"+tiger.hunt());
		}
		
	}

	private static void letTigerHuntV1(Animal animal) {
		// TODO Auto-generated method stub
		try {
			
			Tiger tiger=(Tiger) animal; //forced typecast. it may fail
			//if it is fine
			System.out.println("\t"+ tiger.hunt());
			
		} catch(ClassCastException ex) {
			System.out.println("\t"+animal+" is not a hunter");
		}
		
	}

}
