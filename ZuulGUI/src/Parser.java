import java.util.StringTokenizer;
class Parser {
    private CommandWords commands;  // holds all valid command words
    public Parser() {
        commands = new CommandWords();
    }
    public Command getCommand() {
        String inputLine = "";   // will hold the full input line
        String word1;
        String word2;
        Game.log.setText(Game.log.getText() + "\n" + "> ");     // print prompt
        inputLine = Game.input.getText();
        StringTokenizer tokenizer = new StringTokenizer(inputLine);
        if(tokenizer.hasMoreTokens())
            word1 = tokenizer.nextToken();      // get first word
        else
            word1 = null;
        if(tokenizer.hasMoreTokens())
            word2 = tokenizer.nextToken();      // get second word
        else
            word2 = null;
        if(commands.isCommand(word1))
            return new Command(word1, word2);
        else
            return new Command(null, word2);
    }
    public void showCommands() {
        commands.showAll();
        Game.log.setText(Game.log.getText() + "\n" + "\n\ngo: direction you wish to go to. (in relative loactions) ex: go south (will move to room below)");
        Game.log.setText(Game.log.getText() + "\n" + "quit: shuts down game.");
        Game.log.setText(Game.log.getText() + "\n" + "help: brings this page up.");
        Game.log.setText(Game.log.getText() + "\n" + "stay: used only in cases where you are asked to leave by students and/or teachers in certain rooms.");
        Game.log.setText(Game.log.getText() + "\n" + "get: if there is an item in the room, just type get then the name of the item in the room.");
        Game.log.setText(Game.log.getText() + "\n" + "drop: if you, for some reason, don't want to beat the game and you just want to leave a part behind, then type drop then the name of part you wish to drop.");
        Game.log.setText(Game.log.getText() + "\n" + "inventory: shows what parts you have on you. also shows the amount of parts you have out of the total.");
        Game.log.setText(Game.log.getText() + "\n" + "map: draws a map to the right showing your current room location.");
        Game.log.setText(Game.log.getText() + "\n" + "currentRoom: tell you the description of the current room you are in.");
        Game.log.setText(Game.log.getText() + "\n" + "logCLS: clears the log of the window.");
    }
}