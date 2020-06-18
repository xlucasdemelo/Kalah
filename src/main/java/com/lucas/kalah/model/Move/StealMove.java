package com.lucas.kalah.model.Move;

import com.lucas.kalah.model.Game;
import com.lucas.kalah.model.Pit.House;
import com.lucas.kalah.model.Turn;
import com.lucas.kalah.util.KalahConstants;

public class StealMove implements GameMove {

    @Override
    public void move(Game game, Turn turn) {
        final House movePit = (House) game.getBoard().getPits().get(turn.getHouseIndex());

        int difference = turn.getPlayer().getLastPitIndex() - turn.getHouseIndex();
        int stealPitIndex = turn.getPlayer().getLastPitIndex() + difference;

        if (stealPitIndex > 13){
            stealPitIndex = stealPitIndex - 14;
        }

        final House stealPit = (House) game.getBoard().getPits().get(stealPitIndex);

        int totalOfSeeds = stealPit.getNumberOfSeeds() + movePit.getNumberOfSeeds();

        game.getBoard().getPits().get(turn.getPlayer().getLastPitIndex()).receiveSeeds(totalOfSeeds);
        stealPit.moveSeeds();
        movePit.moveSeeds();
    }
}
