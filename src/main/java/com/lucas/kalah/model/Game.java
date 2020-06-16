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

    public Game(List<Player> players){
        this.players = players;
    }

    public void moveSeeds(Turn turn){
        final int houseIndex = turn.getHouseIndex();
        final House movePit = (House) this.board.getPits().get(houseIndex);

        int lastPit = houseIndex + movePit.getNumberOfSeeds();

        for (int i = houseIndex + 1; i < lastPit + 1; i++){
            if ( i > 13 ){
               i = 0;
               lastPit = lastPit - 13;
            }

            int enemyEndZone =
                    this.players.stream()
                    .filter( player -> player.getName() != turn.getPlayer().getName() )
                    .collect(Collectors.toList())
                    .get(0)
                    .getLastPitIndex();

            if (i == enemyEndZone) {
                lastPit ++;
                continue;
            };

            final Pit pit = this.board.getPits().get(i);
            pit.receiveSeeds(1);
        }

        movePit.moveSeeds();
    };
}
