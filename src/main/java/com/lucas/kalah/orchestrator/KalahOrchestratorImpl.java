package com.lucas.kalah.orchestrator;

import com.lucas.kalah.exception.GameValidationException;
import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.GameFactory;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.model.move.CalculateLastPitSeed;
import com.lucas.kalah.model.move.GameMove;
import com.lucas.kalah.model.move.SowMove;
import com.lucas.kalah.model.move.StealMove;
import com.lucas.kalah.model.rule.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class KalahOrchestratorImpl implements KalahOrchestrator {

    private Game game;

    final List<GameRule> rules = new ArrayList<GameRule>();

    public KalahOrchestratorImpl(){
        this.game = GameFactory.getGame();
        log.info("Starting game: " + this.game);
    }

    @Override
    public Game performMove(Turn turn){

        try {
            this.validateMove(turn);
        } catch (GameValidationException e) {
            log.error(e.getMessage());
            //TODO: Create dto
            return null;
        }

        final Boolean canStealOpponent = this.canStealOpponent(turn);
        int lastPitIndex = CalculateLastPitSeed.calculate(game, turn);

        final GameMove sowMove = new SowMove();
        this.game = sowMove.move(game, turn);

        if (canStealOpponent){
            this.game = this.stealOpponent(turn, lastPitIndex);
        }

        final SetNextPlayerTurnRule setNextPlayerTurnRule = new SetNextPlayerTurnRule(game);
        this.game = setNextPlayerTurnRule.setNextPlayerMove(turn, lastPitIndex);

        final SetGameOverRule setGameOverRule = new SetGameOverRule(this.game);
        this.game = setGameOverRule.setGameOver(turn);

        //TODO fix this class, remove implements
        final DefineWinnerRule defineWinnerRule = new DefineWinnerRule(this.game);
        this.game = defineWinnerRule.defineWinner();

        return game;
    }

    private Boolean validateMove(Turn turn) throws GameValidationException {
        final GameRule isGameOver = new IsGameOverRule(this.game);
        final GameRule isPlayerTurnRule = new IsPlayerTurnRule(this.game);
        final GameRule canMoveRule = new CanMoveRule();
        final GameRule isPlayerTurnHouseRule = new IsPlayerHouseRule();

        this.rules.add(isGameOver);
        this.rules.add(isPlayerTurnRule);
        this.rules.add(canMoveRule);
        this.rules.add(isPlayerTurnHouseRule);

        for ( GameRule rule : this.rules) {
            Boolean isValid = rule.validate(turn);

            if (!isValid){
                throw new GameValidationException( "Validation failed for: " + rule.getClass() );
            };

        }

        return true;
    }

    private Boolean canStealOpponent(Turn turn){
        return new CanStealOpponentRule(game).validate(turn);
    }

    private Game stealOpponent(Turn turn, int lastPit){
        Turn stealTurn = new Turn(turn.getPlayer(), lastPit);
        final GameMove stealMove = new StealMove();
        return stealMove.move(game, stealTurn);
    }

    public Game getGame(){
        return this.game;
    }
}