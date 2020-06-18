package com.lucas.kalah.model;

import com.lucas.kalah.model.Pit.House;
import com.lucas.kalah.model.Pit.Pit;
import com.lucas.kalah.util.KalahConstants;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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
