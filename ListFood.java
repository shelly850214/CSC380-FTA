
public class ListFood extends Food{

	private int index;
	
	public ListFood(String name, double cost, int i) {
		index = i;
	}
	
	public int getIndex() {
		return index;
	}
}
