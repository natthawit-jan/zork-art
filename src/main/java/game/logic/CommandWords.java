package game.logic;

import java.util.HashMap;



public class CommandWords
{

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
            put("fight", CommandWord.FIGHT);
            put("attack", CommandWord.ATTACK);
            put("a", CommandWord.ATTACK);
            put("flee", CommandWord.FLEE);
            put("f", CommandWord.FLEE);
        }};



    }
    public CommandWord getCommandword(String st){
        CommandWord command = ValidCommands.get(st);
        if (command != null) { return command;}
        else return CommandWord.UNKNOWN;
    }

    }

