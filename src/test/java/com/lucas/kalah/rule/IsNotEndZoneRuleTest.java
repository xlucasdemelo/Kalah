package com.lucas.kalah.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.GameFactory;
import com.lucas.kalah.model.game.Player;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.model.rule.IsNotEndZoneRule;
import com.lucas.kalah.model.rule.IsPlayerHouseRule;
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
public class IsNotEndZoneRuleTest {

    private List<Player> players = new ArrayList<Player>();

    @BeforeEach
    public void initializePlayers(){
        final Player playerOne = new Player(KalahConstants.PLAYER_ONE, 6, 0);
        final Player playerTwo = new Player(KalahConstants.PLAYER_TWO, 13, 7);

        this.players.add(playerOne);
        this.players.add(playerTwo);
    }

    @Test
    public void PlayerOneMoveHisHouse_NotEndZOne_ShouldPass(){
        Game game = GameFactory.getGame();

        Turn turn = new Turn(this.players.get(0), 5);

        final IsNotEndZoneRule rule = new IsNotEndZoneRule();
        Boolean isPLayerHouse = rule.validate(turn);

        assertThat(isPLayerHouse, is(true));
    }

    @Test
    public void PlayerOneMoveHisEndZOne_ShouldReturnFalse(){
        Game game = GameFactory.getGame();

        Turn turn = new Turn(this.players.get(0), 6);

        final IsNotEndZoneRule rule = new IsNotEndZoneRule();
        Boolean isPLayerHouse = rule.validate(turn);

        assertThat(isPLayerHouse, is(false));
    }

    @Test
    public void PlayerTwoMoveHisHouse_NotEndZOne_ShouldPass(){
        Game game = GameFactory.getGame();

        Turn turn = new Turn(this.players.get(1), 12);

        final IsNotEndZoneRule rule = new IsNotEndZoneRule();
        Boolean isPLayerHouse = rule.validate(turn);

        assertThat(isPLayerHouse, is(true));
    }

    @Test
    public void PlayerTwoMoveHisEndZOne_ShouldReturnFalse(){
        Game game = GameFactory.getGame();

        Turn turn = new Turn(this.players.get(1), 13);

        final IsNotEndZoneRule rule = new IsNotEndZoneRule();
        Boolean isPLayerHouse = rule.validate(turn);

        assertThat(isPLayerHouse, is(false));
    }
}
