package com.lucas.kalah.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.GameFactory;
import com.lucas.kalah.model.game.Player;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.model.rule.GameRule;
import com.lucas.kalah.model.rule.IsPlayerTurnRule;
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
public class IsPlayerTurnRuleTest {

    private List<Player> players = new ArrayList<Player>();

    @BeforeEach
    public void initializePlayers(){
        final Player playerOne = new Player(KalahConstants.PLAYER_ONE, 6, 0);
        final Player playerTwo = new Player(KalahConstants.PLAYER_TWO, 13, 7);

        this.players.add(playerOne);
        this.players.add(playerTwo);
    }

    @Test
    public void PlayerOnePlayInHisTurn_SHouldPass(){
        Game game = GameFactory.getGame();

        Turn turn = new Turn(this.players.get(0), 5);

        GameRule isPLayerTurnRule = new IsPlayerTurnRule(game);
        Boolean isPLayerTurn = isPLayerTurnRule.validate(turn);

        assertThat(isPLayerTurn, is(true));
    }

    @Test
    public void PlayerOnePlayNotInHisTurn_SHouldReturnFalse(){
        Game game = GameFactory.getGame();
        game.setNextTurnPlayer(this.players.get(1));

        Turn turn = new Turn(this.players.get(0), 5);

        GameRule isPLayerTurnRule = new IsPlayerTurnRule(game);
        Boolean isPLayerTurn = isPLayerTurnRule.validate(turn);

        assertThat(isPLayerTurn, is(false));
    }

    @Test
    public void PlayerTwoPlayInHisTurn_SHouldPass(){
        Game game = GameFactory.getGame();
        game.setNextTurnPlayer(this.players.get(1));

        Turn turn = new Turn(this.players.get(1), 5);

        GameRule isPLayerTurnRule = new IsPlayerTurnRule(game);
        Boolean isPLayerTurn = isPLayerTurnRule.validate(turn);

        assertThat(isPLayerTurn, is(true));
    }

    @Test
    public void PlayerTwoPlayNotInHisTurn_SHouldReturnFalse(){
        Game game = GameFactory.getGame();

        Turn turn = new Turn(this.players.get(1), 5);

        GameRule isPLayerTurnRule = new IsPlayerTurnRule(game);
        Boolean isPLayerTurn = isPLayerTurnRule.validate(turn);

        assertThat(isPLayerTurn, is(false));
    }
}
