package game.main;

import game.logic.Game;

public class Main {
  public static void main(String args[]) {
    Game zorkGame = new Game();

    // this is the REPL loop for Zork
    zorkGame.play();
  }
}
