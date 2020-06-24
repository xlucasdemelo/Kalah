package com.lucas.kalah.model.rule;

import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.util.KalahConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IsMoveInsideBounds implements GameRule {

    @Override
    public Boolean validate(Turn turn) {

        if (turn.getHouseIndex() < 0 || turn.getHouseIndex() > 13 ){
            log.error(KalahConstants.INVALID_MOVE_OUT_OF_INDEX);
            return false;
        }

        return true;
    }

}
