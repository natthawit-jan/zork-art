import java.util.HashMap;



public class CommandWords
{
    // a constant array that holds all valid command words
//    private static final String[] validCommands = {
//        "go", "quit", "help"
//    };
    // create HashMap as a member variable.


    private HashMap<String, CommandWord> ValidCommands;
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // Initialise all enum type and store them in HashMap
        ValidCommands = new HashMap<>();
        ValidCommands.put("go", CommandWord.GO);
        ValidCommands.put("help", CommandWord.HELP);
        ValidCommands.put("unknown", CommandWord.UNKNOWN);
        ValidCommands.put("quit", CommandWord.QUIT);
        ValidCommands.put("look", CommandWord.LOOK);
        ValidCommands.put("info", CommandWord.INFO);


    }
    public CommandWord getCommandword(String st){
        CommandWord command = ValidCommands.get(st);
        if (command != null) { return command;}
        else return CommandWord.UNKNOWN;
    }

    }

