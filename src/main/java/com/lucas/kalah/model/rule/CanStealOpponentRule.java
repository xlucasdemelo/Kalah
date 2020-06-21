package com.lucas.kalah.model.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.model.pit.House;
import com.lucas.kalah.util.KalahConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CanStealOpponentRule implements GameRule {
    private Game game;

    /**
     * Will Validate if the last seed from the turn ends at a empty user house
     *
     * @param turn
     * @return
     */
    @Override
    public Boolean validate(Turn turn) {

        final int houseIndex = turn.getHouseIndex();
        final House movePit = (House) game.getBoard().getPits().get(houseIndex);

        int lastPit = houseIndex + movePit.getNumberOfSeeds();

        if ( lastPit > 13 ){
            lastPit = lastPit - 14;
        }

        final int playerFirstPitIndex = turn.getPlayer().getFirstPitIndex();
        final int playerLastPitIndex = turn.getPlayer().getLastPitIndex();

        if ( lastPit < playerFirstPitIndex || lastPit >= playerLastPitIndex ){
            return false;
        }

        return this.game.getBoard().getPits().get(lastPit).getNumberOfSeeds() == 0;
    }

}
