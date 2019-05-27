package game.logic;

import game.player.Player;
import game.room.InformationCenter;
import game.room.Lobby;
import game.room.Outside;
import game.room.Room;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
    private Parser parser;
    private Player player;
    private List<Room> roomHistories;
    private List<Room> availableRoom;
    private Room currentRoom;


    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        availableRoom = new ArrayList<>();
        roomHistories = new ArrayList<>();
        parser = new Parser();
        System.out.println("< ---- CREATE ROOMS ---- >");
        createRooms();
        System.out.println("--DONE--");
        System.out.println("< ---- CREATE A PLAYER ---- >");
        createPlayer();
        System.out.println("--Done--");


        
    }

    private void createPlayer() {
        System.out.println("Enter the player name : ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        player = new Player(name);
        System.out.println("----------------------------------------");
        System.out.println("Hi, " + name);
        System.out.println("You have 1000 hp");
        System.out.println("You are at level 1");
        System.out.println("The capacity of your bag is 10");
        System.out.println("Kill enemies to earn the score");
        System.out.println("----------------------------------------");
        System.out.println();
        System.out.println();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside = new Outside();
        Room lobby = new Lobby("<-MUIC LOBBY->");
        Room infomationCenter = new InformationCenter(" information center");



        availableRoom.add(outside);
        availableRoom.add(lobby);

        outside.setExit("up", lobby);
        lobby.setExit("left", infomationCenter);




        /* The current room is the last the last index in the list*/
        roomHistories.add(outside);
        currentRoom = outside;

    }

    /**
     * game.main.Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            System.out.print("> ");
            Command command = parser.getCommand();
            if (command.getCommandWord() == null) continue;

            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        StringBuilder s = new StringBuilder();
        s.append("Welcome to the World of Zork!\nWorld of Zork is a new, incredibly boring adventure game.\n");
        s.append("\nType 'help' if you need help.\n");
        System.out.println(s.toString());
        System.out.println();
        System.out.println(getAvailableRoom());


    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)

    {
        boolean wantToQuit = false;
        CommandWord word = command.getCommandWord();
        switch (word) {
            case GO:
                goRoom(command);
                break;
            case HELP:
                printHelp();
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            case UNKNOWN:
                System.out.println(" I don't know what you mean....");
                break;
            case LOOK:
                look();
                break;
            case INFO:
                printInfo();
                break;



        }
        return wantToQuit;

    }

    private void printInfo() {
        double proprtionHP = (player.getHp()/Player.getLEVEANDHP().get(player.getLevel()))*100.0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( player.getName()+"'s information \n " +
                "You are at level " + player.getLevel() +"\n " +
                "HP : " + player.getHp() + " (" + proprtionHP + " % )  \n " );
        System.out.println(stringBuilder.toString());

    }
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("go [direction] -- go to the specific location (e.g. go up) ps.\"go back\" will go one room backward" +
                "\nquit -- quit the game\nhelp\ninfo -- " +
                "print current player's status\n");
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)  {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        if (command.getSecondWord().equals("back")) {
            back();
            return;
        }

        String direction = command.getSecondWord();




        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);


        if (nextRoom != null) {

            currentRoom = nextRoom;
            roomHistories.add(nextRoom) ;
            System.out.println(getAvailableRoom());
            System.out.println();
        }

        else
            System.out.println("There is no door!");

    }
    /**
     * Get rid of duplicate code by implementing a method getAvailableRoom.
     * It takes no parameter, just print all the possible direction the user can go in.
     */
    private String getAvailableRoom(){
        StringBuilder roomToGo = new StringBuilder();
        roomToGo.append("You are at " + currentRoom.getLocationName() + "\n" +
                "There are " + currentRoom.getMonsters().size() + " monsters in this room \n");
        currentRoom.prettyPrintMonster();





        int countRoom = 0;
        if (currentRoom.getExit("up") != null) {
            roomToGo.append("up to ->  " + currentRoom.getExit("up").getLocationName());
            countRoom++;
        }
        if(currentRoom.getExit("down") != null) {
            roomToGo.append("down to ->  " + currentRoom.getExit("down").getLocationName());
            countRoom++;
        }
        if(currentRoom.getExit("left")!= null) {
            roomToGo.append("left ->  " + currentRoom.getExit("left").getLocationName());
            countRoom++;
        }
        if(currentRoom.getExit("right") != null) {
            roomToGo.append("right ->  " + currentRoom.getExit("right").getLocationName());
            countRoom++;
        }

        if(countRoom != 0)
        return roomToGo.toString();
        else
            return roomToGo.toString() + " \n No exit to be found, please consider going back";
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    private void look(){
        //StringBuilder printRoom = new StringBuilder();
        System.out.println(getAvailableRoom());
    }
    private void back(){

        if (roomHistories.size() < 2) {
            System.out.println("You have no where to go back");
            return;
        }

        Room previousRoom = roomHistories.get(roomHistories.size()-2);

        currentRoom = previousRoom;
        System.out.println(getAvailableRoom());
        System.out.println();
        roomHistories.remove(roomHistories.size()-1);


    }









    }





