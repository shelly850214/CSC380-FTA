import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author phoenix
 *
 */
public class GroceryList {
	
	ArrayList<Food> list = new ArrayList<>();
	HashMap<String, Food> compareList = new HashMap<>();
	
	public GroceryList() {
		
	}
	
	/**
	 * Method used to manually add food to the grocery list.
	 * @param f
	 */
	public void manualAdd(Food f) {
		list.add(f);
		compareList.put(f.getName(), f);
	}
	
	/**
	 * Takes a list of food and adds it to the grocery list.
	 * @param foods
	 */
	public void autoAdd(ArrayList<Food> foods) {
		for(Food f : foods) {
			list.add(f);
			compareList.put(f.getName(), f);
		}
	}
	
	/**
	 * Be sure to call checkInventory() for each section of inventory before
	 * calling toString().
	 * 
	 * Returns the list of foods in the order in which food was 
	 * added to the grocery list.
	 */
	public String toString() {
		String s = "";
		for(Food f : list) {
			s+=f.getName();
			if(f.getCost()!=0) {
				s+=", cost: "+f.getCost();
			}
			s+="\n";
		}
		if(s.equals("")) {
			s = "You don't have anything in your list.";
		}
		return s;
	}
	
	/**
	 * A method that takes an array list of food as the parameter and removes
	 * and food found in the parameter from this grocery list's lists. This 
	 * prevents the grocery list from generating duplicate foods that already
	 * exist in the storage location. This method should be called before
	 * any printed instance of toString().
	 * @param storageLoc
	 */
	public void checkInventory(ArrayList<Food> storageLoc) {
		for(Food f : storageLoc) {
			if(compareList.containsKey(f.getName())) {
				this.removeItem(f.getName());
			}
		}
	}
	
	/**
	 * Private method used to traverse the list of food and remove the item
	 * from both the compareList and actual list.
	 * @param s
	 */
	private void removeItem(String s) {
		for(int i = 0; i<list.size(); i++) {
			if(list.get(i).getName().equals(s)) {
				compareList.remove(list.get(i).getName());
				list.remove(i);
				break;
			}
		}
	}

}
