import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
class Room {
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    ArrayList<Items> items = new ArrayList<Items>();
    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }
    public String getShortDescription() {
        return description;
    }
    public String getName() {
    	return "you're " + description;
    }
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(Iterator<String> iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        returnString += "\nItems in room:\n" + getRoomItem();
        return returnString;
    }
    public Room getExit(String direction) {
        return (Room)exits.get(direction);
    }
    //get items from the room
    public Items getItem(int index) {
    	return items.get(index);
    }
    //set up a one of a kind item in the room
    public void setItem(Items newitem) {
    	items.add(newitem);
    }
    public Items getItem(String itemName) {
    	for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getDescription().equals(itemName)) {
				return items.get(i);
			}
		}
		return null;
    }
    public void removeItem(String item) {
    	for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getDescription().equals(item)) {
				items.remove(i);
			}
		}
    }
    public String getRoomItem() {
    	String output = "";
		for (int i = 0; i < items.size(); i++) {
			output += "-" + items.get(i).getDescription() + "\n";
		}
		return output;
    }
}

