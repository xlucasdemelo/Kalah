package com.lucas.kalah.model.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Player;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.model.pit.Pit;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SetGameOverRule implements GameRule{

    private Game game;

    @Override
    public Boolean validate(Turn turn) {
        final Player player = turn.getPlayer();

        for (int i = player.getFirstPitIndex(); i < player.getLastPitIndex(); i++){
            final Pit house = game.getBoard().getPits().get(i);

            if( house.getNumberOfSeeds() != 0){
                return false;
            }
        }

        return true;
    }

    public Game setGameOver(Turn turn){
        final Boolean isGameOver = this.validate(turn);

        this.game.setGameOver(this.validate(turn));

        return this.game;
    }

}
