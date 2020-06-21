package com.lucas.kalah.model.move;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.model.pit.House;

public class CalculateLastPitSeed {

    public static int calculate(Game game, Turn turn){
        final int houseIndex = turn.getHouseIndex();
        final House movePit = (House) game.getBoard().getPits().get(houseIndex);

        int lastPit = houseIndex + movePit.getNumberOfSeeds();

        return lastPit;
    }

}
