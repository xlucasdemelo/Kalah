package com.lucas.kalah.model.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Turn;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HouseHasSeedsRule implements GameRule {
    private Game game;

    @Override
    public Boolean validate(Turn turn) {
        return this.game.getBoard().getPits().get(turn.getHouseIndex()).getNumberOfSeeds() > 0;
    }
}
