import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * This class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Parser 
{

    private CommandWords commands;  // holds all valid command words

    public Parser() 
    {
        commands = new CommandWords();
    }

    public Command getCommand() 
    {
        String inputLine = "";   // will hold the full input line
        String word1;
        String word2;

        System.out.print("> ");     // print prompt

        BufferedReader reader = 
            new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = reader.readLine();
        }
        catch(java.io.IOException exc) {
            System.out.println ("There was an error during reading: "
                                + exc.getMessage());
        }

        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        if(tokenizer.hasMoreTokens())
            word1 = tokenizer.nextToken();      // get first word
        else
            word1 = null;
        if(tokenizer.hasMoreTokens())
            word2 = tokenizer.nextToken();      // get second word
        else
            word2 = null;

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        if(commands.isCommand(word1))
            return new Command(word1, word2);
        else
            return new Command(null, word2);
    }

/*
 * 
 *  go  quit  help  stay  get  drop  inventory  map  
 * 
 */
    public void showCommands()
    {
        commands.showAll();
        System.out.println("\n\ngo: direction you wish to go to. (in relative loactions) ex: go south (will move to room below)");
        System.out.println("quit: shuts down game.");
        System.out.println("help: brings this page up.");
        System.out.println("stay: used only in cases where you are asked to leave by students and/or teachers in certain rooms.");
        System.out.println("get: if there is an item in the room, just type get then the name of the item in the room.");
        System.out.println("drop: if you, for some reason, don't want to beat the game and you just want to leave a part behind, then type drop then the name of part you wish to drop.");
        System.out.println("inventory: shows what parts you have on you. also shows the amount of parts you have out of the total.");
        System.out.println("map: opens up a picture of the whole game, in case you get lost of can't figure something out.");
        System.out.println("currentRoom: tell you the description of the current room you are in.");
    }
}
