

public class Command
{
    private CommandWord commandWord;
    private String secondWord;

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null.
     * @param firstWord The first word of the command. Null if the command
     *                  was not recognised.
     * @param secondWord The second word of the command.
     */
    public Command(CommandWord firstWord, String secondWord)
    {
        this.commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return The command word.
     */
    public CommandWord getCommandWord() {return commandWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }



    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }

    public String returnOpposite(){
        String toReturn = null;
        if (getSecondWord().equals("up")) toReturn = "up";
        if (getSecondWord().equals("down")) toReturn = "down";
        if (getSecondWord().equals("left")) toReturn = "left";
        if (getSecondWord().equals("right")) toReturn = "right";

        return toReturn;
    }
}

