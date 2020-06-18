package com.lucas.kalah.model.Move;

import com.lucas.kalah.model.Game;
import com.lucas.kalah.model.Pit.House;
import com.lucas.kalah.model.Pit.Pit;
import com.lucas.kalah.model.Turn;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

@AllArgsConstructor
public class SowMove implements GameMove {

    @Override
    public void move(Game game, Turn turn) {

        final int houseIndex = turn.getHouseIndex();
        final House movePit = (House) game.getBoard().getPits().get(houseIndex);

        int lastPit = houseIndex + movePit.getNumberOfSeeds();

        for (int i = houseIndex + 1; i < lastPit + 1; i++){
            if ( i > 13 ){
                i = 0;
                lastPit = lastPit - 13;
            }

            int enemyEndZone =
                    game.getPlayers().stream()
                            .filter( player -> player.getName() != turn.getPlayer().getName() )
                            .collect(Collectors.toList())
                            .get(0)
                            .getLastPitIndex();

            if (i == enemyEndZone) {
                lastPit ++;
                continue;
            };

            final Pit pit = game.getBoard().getPits().get(i);
            pit.receiveSeeds(1);
        }

        movePit.moveSeeds();

    }
}
