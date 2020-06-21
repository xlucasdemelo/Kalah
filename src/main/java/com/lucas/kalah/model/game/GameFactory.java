package com.lucas.kalah.model.game;

import com.lucas.kalah.util.KalahConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GameFactory {

    public static Game getGame(){
        final List<Player> players = initPlayers();

        Game game = new Game( players );
        game.setNextTurnPlayer(players.get(0));

        return game;
    }

    private static List<Player> initPlayers(){
        final List<Player> players = new ArrayList<Player>();

        final Player playerOne = new Player(KalahConstants.PLAYER_ONE, 6, 0);
        final Player playerTwo = new Player(KalahConstants.PLAYER_TWO, 13, 7);

        players.add(playerOne);
        players.add(playerTwo);

        return players;
    }

}
