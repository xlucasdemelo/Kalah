package com.lucas.kalah.model.rule;

import com.lucas.kalah.model.Turn;
import com.lucas.kalah.util.KalahConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IsPlayerHouseRule implements GameRule {

    @Override
    public Boolean validate(Turn turn) {
        final int moveIndex = turn.getHouseIndex();
        final int playerFirstPitIndex = turn.getPlayer().getFirstPitIndex();
        final int playerLastPitIndex = turn.getPlayer().getLastPitIndex();

        if ( moveIndex < playerFirstPitIndex || moveIndex > playerLastPitIndex ){
            log.error(KalahConstants.INVALID_MOVE_OUT_OF_INDEX);
            return false;
        }

        return true;
    }

}
