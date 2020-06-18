package com.lucas.kalah.model.move;

import com.lucas.kalah.model.Game;
import com.lucas.kalah.model.pit.House;
import com.lucas.kalah.model.pit.Pit;
import com.lucas.kalah.model.Turn;
import com.lucas.kalah.model.rule.GameRule;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SowMove implements GameMove {

    @Override
    public void validate(ArrayList<GameRule> rules) {

    }

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

    @Override
    public Game outPut() {
        return null;
    }
}
