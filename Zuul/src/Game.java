import java.util.ArrayList;
//By Bryce Hahn

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    public static int curRoomPlayer = 1; //sets current location of player
    public int itemsCollected = 0;
    public static int totalItems = 6;
    public boolean allowAud = true;
    public boolean haveKey = false;
    Room outside, compRoom, lunch, office, mainHall, oneHall, twoHall, threeHall, studentParking, aHall, aWing, aud, stage, southExit, pilletRoom, hHall, janitorCloset, gym, roomSecret, footBallField, finalRoom;
    ArrayList<Items> inventory = new ArrayList<Items>();
    ArrayList<Room> roomlist = new ArrayList<Room>();
    
    public Game() 
    {
        createRooms();
        parser = new Parser();
        
    }
    public static void main(String[] args) {//MotherOfAllGamesWithAPointlessNameThisLongThatIsProbablyJustAnnoyingYouMrGalbraithOrTAGradingThisThing is the name!
    	Game MotherOfAllGamesWithAPointlessNameThisLongThatIsProbablyJustAnnoyingYouMrGalbraithOrTAGradingThisThing = new Game();
    	MotherOfAllGamesWithAPointlessNameThisLongThatIsProbablyJustAnnoyingYouMrGalbraithOrTAGradingThisThing.play();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
       
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
        aud = new Room("inside the auditorium, there is a preformance going on... STAY QUIET!"); //room 18
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

    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
    }

    //starting test/controls
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to \"Sunset Adventures\"");
        System.out.println("This game involves moving around sunset and entering popular rooms around the school");
        System.out.println("Type 'help' if you need help.");
        System.out.println("type 'go' then a direction to navigate around the school\n");
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
        {
            printHelp();
        }
        else if (commandWord.equals("go"))
        {
            wantToQuit = goRoom(command);
        }
        else if (commandWord.equals("currentRoom"))
        {
        	String room = currentRoom.getName();
        	System.out.println(room);
        }
        else if (commandWord.equals("get"))
        {
        	//Pick up items
        	getItem(command);
        }else if (commandWord.equals("stay"))
        {
        	//stay on stage
        	if (currentRoom.equals(stage)) { //if you are in the room "stage" then do the following
        		if (inventory.isEmpty() == false) {//if you do have one or more parts/items then do the following
            		currentRoom = aHall;//puts you into a-hall
            		allowAud = false;//disables the re-entry of this room
            		//make number between 0-5, test if you have that number. if you do: sysout the part has been taken and the remove the part
            		for (int i = 0; i < inventory.size(); i++) {
						if (!inventory.get(i).getDescription().equals(null)) {
		            		System.out.println("They have taken a part from you then kicked you out for good.\nGo find the new part location."); //notify they took a part from you for being disobedient
		            		itemsCollected -= 1;//takes away 1 from your collection counter
		            		
		            		int ranRoom = (int)(Math.random() * 17); //random number between 0-16
		            		roomlist.get(ranRoom).setItem(inventory.get(i));
		            		//currentRoom.setItem(inventory.get(i));
		            		inventory.remove(i);		            		
		            		i = 6;
		            		try {
		            			Thread.sleep(1500);
		                        System.out.println(currentRoom.getLongDescription());
		            		} catch(Exception e) {
		            			e.printStackTrace();
		            		}
						}
					}
        		}//if you have no parts/items yet, then do the following
        		else if (inventory.isEmpty() == true)
        		{
        			System.out.println("you have been kicked out and banned from the auditorium for good. Luckily you had nothing on you!");
        			//sends you to the aHall and blocks you from coming back in
        			currentRoom = aHall;
        			allowAud = false;
        			try {
            			Thread.sleep(1500);
                        System.out.println(currentRoom.getLongDescription());
            		} catch(Exception e) {
            			e.printStackTrace();
            		}
        		}
        	}
        	else//else tell them it doesn't work in this room
        	{
        		System.out.println("This command is not usable in the current room.");
        	}
        }
        else if (commandWord.equals("drop"))
        {
        	//Drop unwanted items
        	dropItem(command);
        }
        else if (commandWord.equals("map"))
        {
        	new mapDraw();
        }
        else if (commandWord.equals("quit"))
        {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("inventory"))
        {
        	printInventory();
        }
        return wantToQuit;
    }

    //Implementations of user commands:

    private void dropItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("drop what?");
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
            System.out.println("The item typed above currently can not be found in your inventory!");
        else {
        	inventory.remove(newItem);
        	currentRoom.setItem(newItem);
        	System.out.println("dropped " + Item);
        }
    }
	private void printInventory() {
		String output = "";
		for (int i = 0; i < inventory.size(); i++) {
				output += "-" + inventory.get(i).getDescription() + "\n";
		}
		System.out.println("You're carrying:" + itemsCollected + "/" + totalItems + "\n" + output);
	}
	private void printHelp() 
    {
        System.out.println("You are tired of school. You are bored of English. You wander off during class...");
        System.out.println("around our Sunset high school.");
        System.out.println("on a search to find the missing parts");
        System.out.println("Usable commands are:");
        parser.showCommands();
    }
	//Picks up item
	private void getItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pick up....
            System.out.println("Get what?");
            return;
        }

        String Item = command.getSecondWord();

        // Try to leave current room.
        Items newItem = currentRoom.getItem(Item);
        if (newItem == null)
            System.out.println("The item typed above can not be found in this current room!");
        else {
        	inventory.add(newItem);
        	currentRoom.removeItem(Item);
        	System.out.println("picked up " + Item);
			itemsCollected++;
			if (Item.equals("Secret-Key"))
	        {
				haveKey = true;
	        }
        }
    }
    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private boolean goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        //assign every room a value for the map! ;)
        if (nextRoom == outside) // room 1
        {
        	curRoomPlayer = 1;
        }
        if (nextRoom == mainHall) // room 2
        {
        	curRoomPlayer = 2;
        }
        if (nextRoom == office) // room 3
        {
        	curRoomPlayer = 3;
        }
        if (nextRoom == oneHall) // room 4
        {
        	curRoomPlayer = 4;
        }
        if (nextRoom == compRoom) // room 5
        {
        	curRoomPlayer = 5;
        }
        if (nextRoom == lunch) // room 6
        {
        	curRoomPlayer = 6;
        }
        if (nextRoom == janitorCloset) // room 7
        {
        	curRoomPlayer = 7;
        }
        if (nextRoom == twoHall) // room 8
        {
        	curRoomPlayer = 8;
        }
        if (nextRoom == gym) // room 9
        {
        	curRoomPlayer = 9;
        }
        if (nextRoom == threeHall) // room 10
        {
        	curRoomPlayer = 10;
        }
        if (nextRoom == roomSecret) // room 11
        {
        	curRoomPlayer = 11;
        }
        if (nextRoom == aWing) // room 12
        {
        	curRoomPlayer = 12;
        }if (nextRoom == aHall) // room 13
        {
        	curRoomPlayer = 13;
        }
        if (nextRoom == footBallField) // room 14
        {
        	curRoomPlayer = 14;
        }
        if (nextRoom == pilletRoom) // room 15
        {
        	curRoomPlayer = 15;
        }
        if (nextRoom == southExit) // room 16
        {
        	curRoomPlayer = 16;
        }
        if (nextRoom == studentParking) // room 17
        {
        	curRoomPlayer = 17;
        }
        if (nextRoom == aud) // room 18
        {
        	curRoomPlayer = 18;
        }
        if (nextRoom == stage) // room 19
        {
        	curRoomPlayer = 19;
        }
        if (nextRoom == hHall) // room 20
        {
        	curRoomPlayer = 20;
        }
        //current area
        if (nextRoom == roomSecret)// room 11
        {
        	curRoomPlayer = 11;
        	if (haveKey == false) {
        		try {
            		System.out.println("You must find the key to enter this room! Come back with the key to enter");
            		currentRoom = threeHall;
            		Thread.sleep(1500);
                    System.out.println(currentRoom.getLongDescription());
        		} catch (Exception e)
        		{
        			e.printStackTrace();
        		}
        		return false;
        	}
        }
        if (nextRoom == aud) // room 18
        {
        	curRoomPlayer = 18;
        	if (allowAud == false) {
        		try {
            		System.out.println("You have been banned from entering ever again!");
            		currentRoom = aHall;
            		Thread.sleep(1500);
                    System.out.println(currentRoom.getLongDescription());
        		} catch (Exception e)
        		{
        			e.printStackTrace();
        		}
        		return false;
        	}
        }
        if (nextRoom == null)
            System.out.println("The direction typed above can not be preformed in this current room!");
        else {
        currentRoom = nextRoom;
        System.out.println(currentRoom.getLongDescription());
        if ((currentRoom == outside) && (itemsCollected >= 6))
        {
        	try {
            	System.out.println("You have found all the pieces and escaped the horrors of the highschool! Congrats.");
            	Thread.sleep(5000);
            	System.out.println("You have just been teleported to home and will live a happier life with your 6 free, absolutely useful* items!");
            	Thread.sleep(5000);
            	System.out.println("*and by useful I mean very useless... well besides the computer, thats something quite cool. You know if you think about it");
            	Thread.sleep(5000);
            	System.out.println("people just take advantage of computers, think they can do what they want with their computers, but truly you have to ask the computer itself...");
            	Thread.sleep(5000);
            	System.out.println("Last year alone, over 200,000,000,000,000 people have abused their computers and/or celluar devices. We can make a change!");
            	Thread.sleep(5000);
            	System.out.println("Please suport us by buying out computers instead, and they totally wont (probably will) kill you in your sleep!");
            	Thread.sleep(5000);
            	System.out.println("Our prices beat Bill Gates and his... amateure company know as \"Microsoft\". or so you mean ripMeOffonMySoftWare!");
            	Thread.sleep(5000);
            	System.out.println("This has been a message implanted into your computer 50 trillion years ago in the year of the horse.");
            	Thread.sleep(5000);
            	System.out.println("THANK FOR PLAY MY GAME.");
            	Thread.sleep(8000);
            	System.out.println("game ended.");
            	return true;
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
    }
    return false;
}
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
}