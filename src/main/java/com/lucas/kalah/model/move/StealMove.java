package com.lucas.kalah.model.move;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.pit.House;
import com.lucas.kalah.model.game.Turn;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StealMove implements GameMove {

    @Override
    public Game move(Game game, Turn turn) {
        House movePit = (House) game.getBoard().getPits().get(turn.getHouseIndex());

        int difference = turn.getPlayer().getLastPitIndex() - turn.getHouseIndex();
        int stealPitIndex = turn.getPlayer().getLastPitIndex() + difference;

        if (stealPitIndex > 13){
            stealPitIndex = stealPitIndex - 14;
        }

        House stealPit = (House) game.getBoard().getPits().get(stealPitIndex);

        int totalOfSeeds = stealPit.getNumberOfSeeds() + movePit.getNumberOfSeeds();

        game.getBoard().getPits().get(turn.getPlayer().getLastPitIndex()).receiveSeeds(totalOfSeeds);
        stealPit.moveSeeds();
        movePit.moveSeeds();

        log.info(turn.getPlayer().getName() + " Steal House: " + stealPitIndex + " Seeds: " + totalOfSeeds );

        return game;
    }
}
