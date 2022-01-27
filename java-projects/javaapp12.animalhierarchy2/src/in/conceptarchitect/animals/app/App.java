package in.conceptarchitect.animals.app;

import in.conceptarchitect.animals.Animal;
import in.conceptarchitect.animals.birds.Eagle;
import in.conceptarchitect.animals.birds.Parrot;
import in.conceptarchitect.animals.mammals.Camel;
import in.conceptarchitect.animals.mammals.CatFamily;
import in.conceptarchitect.animals.mammals.Cow;
import in.conceptarchitect.animals.mammals.Dog;
import in.conceptarchitect.animals.mammals.Horse;
import in.conceptarchitect.animals.mammals.Leopard;
import in.conceptarchitect.animals.mammals.Tiger;
import in.conceptarchitect.animals.reptiles.Crocodile;
import in.conceptarchitect.animals.reptiles.Reptile;
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
			
			System.out.println(animal);
			System.out.println("\t"+animal.eat());
			System.out.println("\t"+animal.move());
			System.out.println("\t"+animal.breed());
			
			
			
			
			System.out.println("\n");
			
			
			
		}

	}

}
