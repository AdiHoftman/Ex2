package gameClient;

import Server.Game_Server_Ex2;
import api.game_service;

public class Ex2 {
    public static void main(String[] args) {
        Ex2_Client e = new Ex2_Client();
        int level_number = 0;
        game_service game = Game_Server_Ex2.getServer(level_number);
        System.out.println(game.getGraph());
        System.out.println(game.getPokemons());
        System.out.println(e);
        game.startGame();

}
}
