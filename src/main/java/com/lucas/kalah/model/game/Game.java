package com.lucas.kalah.model.game;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that will have the state of the Board
 */
@Data
public class Game {
    private Board board = new Board();

    private List<Player> players;

    private Player currentPLayerTurn;

    private Player nextTurnPlayer;

    private Boolean gameOver = Boolean.FALSE;

    private String winner;

    public Game(List<Player> players){
        this.players = players;
    }

    public Player getPlayerFromName(String playerName){
        return this.players.stream()
                .filter(player -> player.getName().equals(playerName))
                .collect(Collectors.toList())
                .get(0);
    }
}
