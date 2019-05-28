package game.logic;

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
        ValidCommands = new HashMap<String, CommandWord>() {{
            put("go", CommandWord.GO);
            put("help", CommandWord.HELP);
            put("unknown", CommandWord.UNKNOWN);
            put("quit", CommandWord.QUIT);
            put("look", CommandWord.LOOK);
            put("info", CommandWord.INFO);
            put("print", CommandWord.ROOM);
        }};



    }
    public CommandWord getCommandword(String st){
        CommandWord command = ValidCommands.get(st);
        if (command != null) { return command;}
        else return CommandWord.UNKNOWN;
    }

    }

