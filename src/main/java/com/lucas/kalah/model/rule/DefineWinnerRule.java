package com.lucas.kalah.model.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Player;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.util.KalahConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class DefineWinnerRule implements GameRule{
    private Game game;

    @Override
    public Boolean validate(Turn turn) {
        final IsGameNotOverRule isGamenNotOverRule = new IsGameNotOverRule(this.game);
        return isGamenNotOverRule.validate(turn);
    }

    public Game defineWinner(Turn turn){
        if (!this.validate(turn)){
            log.info("Game not finished yet");
            return this.game;
        }

        final Player playerOne = this.game.getPlayers().get(0);
        final Player playerTwo = this.game.getPlayers().get(1);

        int playerOneTotalSeeds = 0;
        int playerTwoTotalSeeds = 0;

        for (int i = playerOne.getFirstPitIndex(); i <= playerOne.getLastPitIndex(); i ++){
            playerOneTotalSeeds += this.game.getBoard().getPits().get(i).getNumberOfSeeds();
        }

        for (int k = playerTwo.getFirstPitIndex(); k <= playerTwo.getLastPitIndex(); k ++){
            playerTwoTotalSeeds += this.game.getBoard().getPits().get(k).getNumberOfSeeds();
        }

        if (playerOneTotalSeeds == playerTwoTotalSeeds){
            this.game.setWinner(KalahConstants.DRAW);
            return this.game;
        }

        Player winner = playerOneTotalSeeds > playerTwoTotalSeeds ? playerOne : playerTwo;
        this.game.setWinner(winner.getName());

        log.info( "Player one Seeds: " + playerOneTotalSeeds + ", PLayer Two Seeds: " + playerTwoTotalSeeds );

        return this.game;
    }
}
