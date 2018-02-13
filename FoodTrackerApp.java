import java.io.File;
import java.util.Scanner;

public class FoodTrackerApp {
	
	public static void main(String[] args) {
		
		//TODO read up on file objects.
		File memory = null;
		//TODO build parser and parse file to build info
		//TODO change prevTime to parsed info
		long prevTime = System.currentTimeMillis();
		GroceryList list = new GroceryList();
		//TODO rename class as needed
		Storage food = null;
		Mode m = Mode.FRIDGE;
		int warningTime = 3;
		int grocGenerate = 7;
		//TODO make command list
		String commandList = ""
				+ "help: displays a list of commands"
				+ "setup: returns to the setup"
				+ "exit: saves data and exits the program"
				+ "quit: saves data and quits the program"
				+ "mode: returns to mode setup (fridge, freezer, pantry)";
		
		boolean running = true;
		boolean setup = true;
		Scanner scone = new Scanner(System.in);
		
		System.out.println("Welcome to the food tracker app.");
		//Main loop that runs the program.
		while(running) {
			String input = "";
			
			//prints out foods about to expire as long as there are foods
			//prints out the grocery list as long as there are foods
			if(food!=null||setup==false) {
				System.out.println("Caution; these foods are close to "
						+ "expiring: ");
				FoodTrackerApp.printCloseToExpiring(food, 
						System.currentTimeMillis());
				if(System.currentTimeMillis()-prevTime>=7) {
					//TODO change value 7 in if statement to the time 
					//provided in the setup, grocGenerate.
					System.out.println("It's time to go shopping. Here is "
							+ "your grocery list:\n");
					list.checkInventory(food.getFreezer());
					list.checkInventory(food.getFridge());
					list.checkInventory(food.getPantry());
					System.out.println(list);
				}
			}
			//otherwise perform first time setup.
			else {
				System.out.println("Welcome to your food tracker app setup.");
				System.out.println("We're going to get some setting "
						+ "squared away for you.");
				System.out.println("First, how soon before expiring would you"
						+ "like to recieve warnings? Default is 3 days");
				System.out.println("This number is a number of days.");
				int num = -1;
				while(num<0) {
					try {
						num = Integer.parseInt(scone.nextLine());
					}
					catch(NumberFormatException e) {
						System.out.println("That was not a valid entry.");
						System.out.println("For now, we'll set it to 3.");
						System.out.println("This can be changed at a later"
								+ " time.");
						num = 3;
					}
					warningTime = num;
				}
				System.out.print("\nGreat! Your wanring timer is set to ");
				System.out.println(warningTime+" days before expiration.");
				System.out.println("Now, how often would you like your"
						+ " grocery list to be generated?");
				System.out.println("This is a number of days.");
				num = -1;
				while(num<0) {
					try {
						num = Integer.parseInt(scone.nextLine());
					}
					catch(NumberFormatException e) {
						System.out.println("That was not a valid entry.");
						System.out.println("For now, we'll set it to 7.");
						System.out.println("This can be changed at a later"
								+ " time.");
						num = 7;
					}
					grocGenerate = num;
				}
				System.out.println("\nAwesome! Your grocery list will be "
						+ "provided every "+grocGenerate+" days.");
				//TODO add more setup as needed.
				System.out.println("Alright, that looks like everything for"
						+ " now. Remember to change these setting as needed.");
				setup = false;
			}
			
			//First interaction with the user after setup
			System.out.println("Where would you like to look?"
					+ "(fridge, freezer, pantry");
			input = scone.nextLine();
			input = input.toUpperCase();
			if(input.equals("EXIT")) {
				FoodTrackerApp.exit(memory);
				running = false;
				break;
			}
			if(input.equals("HELP")) {
				System.out.println(commandList);
			}
			if(input.equals("SETUP")) {
				setup = true;
			}
			else if(!(input.equals("FREEZER")||input.equals("PANTRY")||
					input.equals("FRIDGE"))) {
				System.out.println("Invalid location. Please select either"
						+ "fridge, freezer, or pantry.");
			}
			else {
				//Secondary loop where input commands are given.
				/*
				 * Command list:
				 * exit: saves data and exits program
				 * up: returns to storage selection
				 * find/look/search: searches for food in current location
				 * setup: runs the setup
				 * 
				 */
				while(running) {
					
				}
			}
		}
		
		scone.close();
		System.out.println("Goodbye.");

	}
	
	/**
	 * 
	 * @param f
	 * @param p
	 */
	public static void exit(File f/*, FTAParser p*/) {
		//TODO use parser to write to file
	}
	
	public static void printCloseToExpiring(Storage s, long t) {
		String value = "No foods are close to expiring.";
		//TODO set value to "" if food is located
		System.out.println(value);
	}
	
	/**
	 * Prints the current storage location being interacted with.
	 * @param m
	 */
	public static void printMode(Mode m) {
		System.out.print("Current mode is: ");
		switch (m) {
			case FREEZER:{
				System.out.println("freezer.");
				break;
			}
			case PANTRY:{
				System.out.println("pantry.");
				break;
			}
			default:{
				System.out.println("fridge.");
			}
		}
	}
	
	public static void removeFood(Storage food, Food f, GroceryList l, Mode m) {
		food.remove(f,m);
		l.manualAdd(f);
	}

}
