package com.lucas.kalah.rule;

import com.lucas.kalah.model.Player;
import com.lucas.kalah.model.Turn;
import com.lucas.kalah.model.rule.CanMoveRule;
import com.lucas.kalah.model.rule.GameRule;
import com.lucas.kalah.util.KalahConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class CanMoveRuleTest {

    private List<Player> players = new ArrayList<Player>();

    @BeforeEach
    public void initializePlayers(){
        final Player playerOne = new Player(KalahConstants.PLAYER_ONE, 6, 0);
        final Player playerTwo = new Player(KalahConstants.PLAYER_TWO, 13, 7);

        this.players.add(playerOne);
        this.players.add(playerTwo);
    }

    @Test
    public void testMoveToOutOfBounds_shouldReturnFalse(){
        final Turn turn = new Turn(this.players.get(0), 15);

        final GameRule rule = new CanMoveRule();

        final boolean isValid = rule.validate(turn);

        assertThat(isValid, is(false));
    }

    @Test
    public void testMoveToValidBounds_shouldReturntrue(){
        final Turn turn = new Turn(this.players.get(0), 3);

        final GameRule rule = new CanMoveRule();

        final boolean isValid = rule.validate(turn);

        assertThat(isValid, is(true));
    }

}
