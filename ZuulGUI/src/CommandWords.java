class CommandWords {
    // a constant array that holds all valid command words
    private static final String validCommands[] = {
        "go", "quit", "help", "stay", "get", "drop", "inventory", "map", "currentRoom", "logCLS"
    };
    public boolean isCommand(String aString) {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    public void showAll() {
        for(int i = 0; i < validCommands.length; i++) {
            Game.log.setText(Game.log.getText() + validCommands[i] + "  ");
        }
        Game.log.setText(Game.log.getText() + "\n");
    }
}
