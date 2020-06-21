package com.lucas.kalah.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Player;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.model.move.CalculateLastPitSeed;
import com.lucas.kalah.model.rule.SetNextPlayerTurnRule;
import com.lucas.kalah.util.KalahConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class SetNextPlayerTurnTest {

    private List<Player> players = new ArrayList<Player>();

    @BeforeEach
    public void initializePlayers(){
        final Player playerOne = new Player(KalahConstants.PLAYER_ONE, 6, 0);
        final Player playerTwo = new Player(KalahConstants.PLAYER_TWO, 13, 7);

        this.players.add(playerOne);
        this.players.add(playerTwo);
    }

    @Test
    public void seedEndsAtPlayerOneEndzone_PlayerOneShouldPLayAgain(){
        Game game = new Game(this.players);
        game.setNextTurnPlayer(this.players.get(0));

        final Turn turn = new Turn(this.players.get(0), 2);

        int lastPitIndex = CalculateLastPitSeed.calculate(game, turn);

        final SetNextPlayerTurnRule rule = new SetNextPlayerTurnRule(game);
        rule.setNextPlayerMove(turn, lastPitIndex);

        final Player nextTurnPlayer = game.getNextTurnPlayer();

        assertThat(nextTurnPlayer.getName(), is(this.players.get(0).getName()));
    }

    @Test
    public void seedDontEndstPlayerOneEndzone_PlayerTwoShouldPlayNextTurn(){
        Game game = new Game(this.players);
        game.setNextTurnPlayer(this.players.get(0));

        final Turn turn = new Turn(this.players.get(0), 1);

        int lastPitIndex = CalculateLastPitSeed.calculate(game, turn);

        final SetNextPlayerTurnRule rule = new SetNextPlayerTurnRule(game);
        rule.setNextPlayerMove(turn, lastPitIndex);

        final Player nextTurnPlayer = game.getNextTurnPlayer();

        assertThat(nextTurnPlayer.getName(), is(this.players.get(1).getName()));
    }

    @Test
    public void seedEndAtOpponentEndzone_ShouldJumpEndzone_And_PlayerTwoShouldPlayNextTurn(){
        Game game = new Game(this.players);
        game.setNextTurnPlayer(this.players.get(0));

        game.getBoard().getPits().get(5).setNumberOfSeeds(8);

        final Turn turn = new Turn(this.players.get(0), 5);

        int lastPitIndex = CalculateLastPitSeed.calculate(game, turn);

        final SetNextPlayerTurnRule rule = new SetNextPlayerTurnRule(game);
        rule.setNextPlayerMove(turn, lastPitIndex);

        final Player nextTurnPlayer = game.getNextTurnPlayer();

        assertThat(nextTurnPlayer.getName(), is(this.players.get(1).getName()));
    }

    @Test
    public void seedEndsAtPlayerTwoEndzone_PlayerTwoShouldPLayAgain(){
        Game game = new Game(this.players);
        game.setNextTurnPlayer(this.players.get(1));

        final Turn turn = new Turn(this.players.get(1), 9);

        int lastPitIndex = CalculateLastPitSeed.calculate(game, turn);

        final SetNextPlayerTurnRule rule = new SetNextPlayerTurnRule(game);
        rule.setNextPlayerMove(turn, lastPitIndex);

        final Player nextTurnPlayer = game.getNextTurnPlayer();

        assertThat(nextTurnPlayer.getName(), is(this.players.get(1).getName()));
    }

    @Test
    public void seedDontEndstPlayerTwoEndzone_PlayerOneShouldPlayNextTurn(){
        Game game = new Game(this.players);
        game.setNextTurnPlayer(this.players.get(1));

        final Turn turn = new Turn(this.players.get(1), 10);

        int lastPitIndex = CalculateLastPitSeed.calculate(game, turn);

        final SetNextPlayerTurnRule rule = new SetNextPlayerTurnRule(game);
        rule.setNextPlayerMove(turn, lastPitIndex);

        final Player nextTurnPlayer = game.getNextTurnPlayer();

        assertThat(nextTurnPlayer.getName(), is(this.players.get(0).getName()));
    }

    @Test
    public void seedEndAtOpponentEndzone_ShouldJumpEndzone_And_PlayerOneShouldPlayNextTurn(){
        Game game = new Game(this.players);
        game.setNextTurnPlayer(this.players.get(1));

        game.getBoard().getPits().get(12).setNumberOfSeeds(8);

        final Turn turn = new Turn(this.players.get(1), 12);

        int lastPitIndex = CalculateLastPitSeed.calculate(game, turn);

        final SetNextPlayerTurnRule rule = new SetNextPlayerTurnRule(game);
        rule.setNextPlayerMove(turn, lastPitIndex);

        final Player nextTurnPlayer = game.getNextTurnPlayer();

        assertThat(nextTurnPlayer.getName(), is(this.players.get(0).getName()));
    }
}
