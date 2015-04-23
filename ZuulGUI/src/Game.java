import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//By Bryce Hahn

public class Game implements ActionListener, KeyListener{
    private Parser parser;
    private Room currentRoom;
    public static int curRoomPlayer = 1; //sets current location of player
    public int itemsCollected = 0;
    public static int totalItems = 6;
    public boolean allowAud = true;
    public boolean haveKey = false;
    private final JFrame frame = new JFrame("Zull GUI remake - Bryce Hahn");
    Container north = new Container();
    Container south = new Container();
    public static JTextArea log = new JTextArea();
    JScrollPane pane = new JScrollPane(log);
    public static JTextArea map = new JTextArea();
    JScrollPane mapPane = new JScrollPane(map);
    public static JTextField input = new JTextField();
    JButton ok = new JButton("GO!");
    Room outside, compRoom, lunch, office, mainHall, oneHall, twoHall, threeHall, studentParking, aHall, aWing, aud, stage, southExit, pilletRoom, hHall, janitorCloset, gym, roomSecret, footBallField, finalRoom;
    ArrayList<Items> inventory = new ArrayList<Items>();
    ArrayList<Room> roomlist = new ArrayList<Room>();
    
    public Game() {
        createRooms();
        parser = new Parser();
        frame.setVisible(true);
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //add stuff
        frame.add(north, BorderLayout.CENTER);
        north.setLayout(new GridLayout(1, 2));
        north.add(pane);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        log.setEditable(false);
        log.setBackground(new Color(230, 230, 230));
        north.add(mapPane);
        mapPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mapPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        map.setEditable(false);
        map.setBackground(new Color(200, 200, 200));
        frame.add(south, BorderLayout.SOUTH);
        south.setLayout(new GridLayout(1, 2));
        south.add(input);
        input.addKeyListener(this);
        south.add(ok);
        ok.addActionListener(this);
    }
    public static void main(String[] args) {
    	Game MotherOfAllGamesWithAPointlessNameThisLongThatIsProbablyJustAnnoyingYouMrGalbraithOrTAGradingThisThing = new Game();
    	MotherOfAllGamesWithAPointlessNameThisLongThatIsProbablyJustAnnoyingYouMrGalbraithOrTAGradingThisThing.play();
    }
    private void createRooms() {
        //Create the rooms (19 total)
        outside = new Room("at the outside of Sunset high school"); //room 1
        compRoom = new Room("inside the awesome room of 1-20"); //room 5
        compRoom.setItem(new Items("Computer")); //Give the room an item
        lunch = new Room("in the lunch room"); //room 6
        janitorCloset = new Room("in the janitor's closet"); //room 7
        janitorCloset.setItem(new Items("Secret-Key"));
        office = new Room("in the main office"); //room 3
        mainHall = new Room("in the main hall of Sunset highschool"); //room 2
        oneHall = new Room("in 1 hall"); //room 4
        twoHall = new Room("in 2 hall"); //room 8
        threeHall = new Room("in 3 hall"); //room 10
        aHall = new Room("in the magnifacent hall of the A's"); //room 13
        aWing = new Room("in a-wing, don't bring any food in here or the guards will get ya"); //room 12
        hHall = new Room("in the hall full of knowledge from language arts... not too much here besides those creepy kids looking at me"); //room 20
        southExit = new Room("at the creepy hallway intersection between 1 hall and h hall"); //room 16
        pilletRoom = new Room("in that one science room when we actualy have fun in but everyone fakes it... Maybe there is something hidden in here"); //room 15
        pilletRoom.setItem(new Items("Thermometer"));
        studentParking = new Room("at the student parking lot"); //room 17
        aud = new Room("inside the auditorium, there is a preformance going on..."); //room 18
        stage = new Room("on the stage while they are preforming! I would leave if I were you"); //room 19
        gym = new Room("inside the first gym ever built here at Sunset, I wont whats in that pile of tenis raquets"); //room 9
        gym.setItem(new Items("Ball-Pump"));
        roomSecret = new Room("in the super secret room! look over there... its another key"); //room 11
        roomSecret.setItem(new Items("Skeleton-Key"));
        footBallField = new Room("on the football field. GO APOLOS!"); //room 14
        footBallField.setItem(new Items("Flattened-Football"));
        //assigns all the rooms the the "roomlist" ArrayList<>
        roomlist.add(outside);
        roomlist.add(compRoom);
        roomlist.add(lunch);
        roomlist.add(janitorCloset);
        roomlist.add(office);
        roomlist.add(oneHall);
        roomlist.add(twoHall);
        roomlist.add(threeHall);
        roomlist.add(aHall);
        roomlist.add(aWing);
        roomlist.add(hHall);
        roomlist.add(southExit);
        roomlist.add(pilletRoom);
        roomlist.add(studentParking);
        roomlist.add(gym);
        roomlist.add(roomSecret);
        roomlist.add(footBallField);
        //Initialize room exits
        outside.setExit("south", mainHall);
        mainHall.setExit("north", outside);
        outside.setExit("west", aHall);
        aHall.setExit("east", outside);
        outside.setExit("east", hHall);
        hHall.setExit("east", outside);
        mainHall.setExit("west", oneHall);
        oneHall.setExit("east", compRoom);
        compRoom.setExit("west", oneHall);
        oneHall.setExit("west", lunch);
        lunch.setExit("east", oneHall);
        lunch.setExit("south", janitorCloset);
        lunch.setExit("west", twoHall);
        janitorCloset.setExit("north", lunch);
        mainHall.setExit("south", office);
        office.setExit("north", mainHall);
        oneHall.setExit("south", southExit);
        southExit.setExit("south", studentParking);
        southExit.setExit("west", pilletRoom);
        southExit.setExit("east", hHall);
        southExit.setExit("north", oneHall);
        hHall.setExit("west", southExit);
        pilletRoom.setExit("east", southExit);
        studentParking.setExit("north", southExit);
        studentParking.setExit("west", footBallField);
        oneHall.setExit("north", aHall);
        twoHall.setExit("east", lunch);
        twoHall.setExit("west", gym);
        twoHall.setExit("north", aWing);
        gym.setExit("east", twoHall);
        gym.setExit("west", threeHall);
        threeHall.setExit("east", gym);
        threeHall.setExit("north", aWing);
        threeHall.setExit("west", roomSecret);
        roomSecret.setExit("east", threeHall);
        threeHall.setExit("south", footBallField);
        footBallField.setExit("north", threeHall);
        footBallField.setExit("east", studentParking);
        aHall.setExit("south", oneHall);
        aHall.setExit("north", aWing);
        aHall.setExit("west", aud);
        aWing.setExit("south", aHall);
        aWing.setExit("west", threeHall);
        aud.setExit("east", aHall);
        aud.setExit("south", stage);
        stage.setExit("north", aud);
        currentRoom = outside;  //Start game outside
    }
    public void play() {            
        printWelcome();
    }
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(ok)) {
	        Command command = parser.getCommand();
	        processCommand(command);
	        input.setText("");
		}
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent event) {
		if (event.getKeyChar() == KeyEvent.VK_ENTER) {
			Command command = parser.getCommand();
	        processCommand(command);
	        input.setText("");
		}
	}
	public void keyTyped(KeyEvent arg0) {
	}
    private void printWelcome() {
        log.setText(log.getText() + "\n");
        log.setText(log.getText() + "\n" + "Welcome to \"Sunset Adventures\"");
        log.setText(log.getText() + "\n" + "This game involves moving around sunset and entering popular rooms around the school");
        log.setText(log.getText() + "\n" + "Type 'help' if you need help.");
        log.setText(log.getText() + "\n" + "type 'go' then a direction to navigate around the school\n");
        log.setText(log.getText() + "\n" + currentRoom.getLongDescription());
    }
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        if(command.isUnknown()) {
            log.setText(log.getText() + "I don't know what you mean...");
            return false;
        }
        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            wantToQuit = goRoom(command);
        } else if (commandWord.equals("currentRoom")) {
        	String room = currentRoom.getName();
        	log.setText(log.getText() + room);
        } else if (commandWord.equals("get")) {
        	//Pick up items
        	getItem(command);
        } else if (commandWord.equals("stay")) {
        	//stay on stage
        	if (currentRoom.equals(stage)) { //if you are in the room "stage" then do the following
        		if (inventory.isEmpty() == false) {//if you do have one or more parts/items then do the following
            		currentRoom = aHall;//puts you into a-hall
            		allowAud = false;//disables the re-entry of this room
            		//make number between 0-5, test if you have that number. if you do: sysout the part has been taken and the remove the part
            		for (int i = 0; i < inventory.size(); i++) {
						if (!inventory.get(i).getDescription().equals(null)) {
		            		log.setText(log.getText() + "They have taken a part from you then kicked you out for good.\nGo find the new part location.\n"); //notify they took a part from you for being disobedient
		            		itemsCollected -= 1;//takes away 1 from your collection counter
		            		
		            		int ranRoom = (int)(Math.random() * 17); //random number between 0-16
		            		roomlist.get(ranRoom).setItem(inventory.get(i));
		            		//currentRoom.setItem(inventory.get(i));
		            		inventory.remove(i);
		            		i = 6;
		            		try {
		            			Thread.sleep(1500);
		                        log.setText(log.getText() + currentRoom.getLongDescription());
		            		} catch(Exception e) {
		            			e.printStackTrace();
		            		}
						}
					}
        		} else if (inventory.isEmpty() == true) {
        			log.setText(log.getText() + "\n" + "you have been kicked out and banned from the auditorium for good. Luckily you had nothing on you!");
        			//sends you to the aHall and blocks you from coming back in
        			currentRoom = aHall;
        			allowAud = false;
        			try {
            			Thread.sleep(1500);
                        log.setText(log.getText() + currentRoom.getLongDescription());
            		} catch(Exception e) {
            			e.printStackTrace();
            		}
        		}
        	} else {
        		log.setText(log.getText() + "\n" + "This command is not usable in the current room.");
        	}
        } else if (commandWord.equals("drop")) {
        	//Drop unwanted items
        	dropItem(command);
        } else if (commandWord.equals("map")) {
        	new mapDraw();
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("inventory")) {
        	printInventory();
        } else if (commandWord.equals("logCLS")) {
        	log.setText("> ");
        }
        return wantToQuit;
    }
    private void dropItem(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
        	log.setText(log.getText() + "\n" + "drop what?");
            return;
        }
        String Item = command.getSecondWord();
        // Try to leave current room.
        Items newItem = null;
        for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getDescription().equals(Item)) {
				newItem = inventory.get(i);
			}
		}
        if (newItem == null)
        	log.setText(log.getText() + "\n" + "The item typed above currently can not be found in your inventory!");
        else {
        	inventory.remove(newItem);
        	currentRoom.setItem(newItem);
        	log.setText(log.getText() + "\n" + "dropped " + Item);
        }
    }
	private void printInventory() {
		String output = "";
		for (int i = 0; i < inventory.size(); i++) {
				output += "-" + inventory.get(i).getDescription() + "\n";
		}
		log.setText(log.getText() + "\n" + "You're carrying:" + itemsCollected + "/" + totalItems + "\n" + output);
	}
	private void printHelp() {
        log.setText(log.getText() + "\n" + "You are tired of school. You are bored of English. \nYou wander off during class...");
        log.setText(log.getText() + "\n" + "around our Sunset high school.");
        log.setText(log.getText() + "\n" + "on a search to find the missing parts");
        log.setText(log.getText() + "\n" + "Usable commands are:");
        parser.showCommands();
    }
	private void getItem(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pick up....
        	log.setText(log.getText() + "\n" + "Get what?");
            return;
        }
        String Item = command.getSecondWord();
        // Try to leave current room.
        Items newItem = currentRoom.getItem(Item);
        if (newItem == null)
        	log.setText(log.getText() + "\n" + "The item typed above can not be found in this current room!");
        else {
        	inventory.add(newItem);
        	currentRoom.removeItem(Item);
        	log.setText(log.getText() + "picked up " + Item);
			itemsCollected++;
			if (Item.equals("Secret-Key")) {
				haveKey = true;
	        }
        }
    }
    private boolean goRoom(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
        	log.setText(log.getText() + "\n" + "Go where?");
            return false;
        }
        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        //assign every room a value for the map! ;)
        if (nextRoom == outside) // room 1
        {
        	curRoomPlayer = 1;
        	new mapDraw();
        }
        if (nextRoom == mainHall) // room 2
        {
        	curRoomPlayer = 2;
        	new mapDraw();
        }
        if (nextRoom == office) // room 3
        {
        	curRoomPlayer = 3;
        	new mapDraw();
        }
        if (nextRoom == oneHall) // room 4
        {
        	curRoomPlayer = 4;
        	new mapDraw();
        }
        if (nextRoom == compRoom) // room 5
        {
        	curRoomPlayer = 5;
        	new mapDraw();
        }
        if (nextRoom == lunch) // room 6
        {
        	curRoomPlayer = 6;
        	new mapDraw();
        }
        if (nextRoom == janitorCloset) // room 7
        {
        	curRoomPlayer = 7;
        	new mapDraw();
        }
        if (nextRoom == twoHall) // room 8
        {
        	curRoomPlayer = 8;
        	new mapDraw();
        }
        if (nextRoom == gym) // room 9
        {
        	curRoomPlayer = 9;
        	new mapDraw();
        }
        if (nextRoom == threeHall) // room 10
        {
        	curRoomPlayer = 10;
        	new mapDraw();
        }
        if (nextRoom == roomSecret) // room 11
        {
        	curRoomPlayer = 11;
        	new mapDraw();
        }
        if (nextRoom == aWing) // room 12
        {
        	curRoomPlayer = 12;
        	new mapDraw();
        }
        if (nextRoom == aHall) // room 13
        {
        	curRoomPlayer = 13;
        	new mapDraw();
        }
        if (nextRoom == footBallField) // room 14
        {
        	curRoomPlayer = 14;
        	new mapDraw();
        }
        if (nextRoom == pilletRoom) // room 15
        {
        	curRoomPlayer = 15;
        	new mapDraw();
        }
        if (nextRoom == southExit) // room 16
        {
        	curRoomPlayer = 16;
        	new mapDraw();
        }
        if (nextRoom == studentParking) // room 17
        {
        	curRoomPlayer = 17;
        	new mapDraw();
        }
        if (nextRoom == aud) // room 18
        {
        	curRoomPlayer = 18;
        	new mapDraw();
        }
        if (nextRoom == stage) // room 19
        {
        	curRoomPlayer = 19;
        	new mapDraw();
        }
        if (nextRoom == hHall) // room 20
        {
        	curRoomPlayer = 20;
        	new mapDraw();
        }
        //current area
        if (nextRoom == roomSecret){
        	curRoomPlayer = 11;
        	new mapDraw();
        	if (haveKey == false) {
        		try {
            		log.setText(log.getText() + "You must find the key to enter this room! Come back with the key to enter");
            		currentRoom = threeHall;
            		Thread.sleep(1500);
            		log.setText(log.getText() + "\n" + currentRoom.getLongDescription());
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        		return false;
        	}
        }
        if (nextRoom == aud) {
        	curRoomPlayer = 18;
        	new mapDraw();
        	if (allowAud == false) {
        		try {
            		log.setText(log.getText() + "You have been banned from entering ever again!\n");
            		currentRoom = aHall;
            		Thread.sleep(1500);
                    log.setText(log.getText() + currentRoom.getLongDescription());
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        		return false;
        	}
        }
        if (nextRoom == null)
        	log.setText(log.getText() + "\n" + "The direction typed above can not be preformed in this current room!");
        else {
        currentRoom = nextRoom;
    	new mapDraw();
        log.setText(log.getText() + currentRoom.getLongDescription());
        if ((currentRoom == outside) && (itemsCollected >= 6)) {
        	try {
            	log.setText(log.getText() + "\n" + "You have found all the pieces and escaped the horrors of the highschool! Congrats.");
            	Thread.sleep(5000);
            	log.setText(log.getText() + "\n" + "You have just been teleported to home and will live a happier life with your 6 free, absolutely useful* items!");
            	Thread.sleep(5000);
            	log.setText(log.getText() + "\n" + "*and by useful I mean very useless... well besides the computer, thats something quite cool. You know if you think about it");
            	Thread.sleep(5000);
            	log.setText(log.getText() + "\n" + "people just take advantage of computers, think they can do what they want with their computers, but truly you have to ask the computer itself...");
            	Thread.sleep(5000);
            	log.setText(log.getText() + "\n" + "Last year alone, over 200,000,000,000,000 people have abused their computers and/or celluar devices. We can make a change!");
            	Thread.sleep(5000);
            	log.setText(log.getText() + "\n" + "Please suport us by buying out computers instead, and they totally wont (probably will) kill you in your sleep!");
            	Thread.sleep(5000);
            	log.setText(log.getText() + "\n" + "Our prices beat Bill Gates and his... amateure company know as \"Microsoft\". or so you mean ripMeOffonMySoftWare!");
            	Thread.sleep(5000);
            	log.setText(log.getText() + "\n" + "This has been a message implanted into your computer 50 trillion years ago in the year of the horse.");
            	Thread.sleep(5000);
            	log.setText(log.getText() + "\n" + "THANK FOR PLAY MY GAME.");
            	Thread.sleep(8000);
            	log.setText(log.getText() + "\n" + "game ended.");
            	return true;
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
    }
    return false;
}
    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            log.setText(log.getText() + "Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
}