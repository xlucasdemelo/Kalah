package com.lucas.kalah.model;

import lombok.Data;

import java.util.List;

/**
 * Class that will change the state of the Board
 */
@Data
public class Game {
    private Board board = new Board();

    private List<Player> players;

    private Player currentPLayerTurn;

    private Player nextTurnPlayer;

    public Game(List<Player> players){
        this.players = players;
    }

}
