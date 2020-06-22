package com.lucas.kalah.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.GameFactory;
import com.lucas.kalah.model.rule.IsGameNotOverRule;
import com.lucas.kalah.util.KalahConstants;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class IsGameNotOverRuleTest {

    @Test
    public void gameIsNotOver(){
        Game game = GameFactory.getGame();

        final IsGameNotOverRule rule = new IsGameNotOverRule(game);

        Boolean isGameNotOver = rule.validate(null);

        assertThat(isGameNotOver, is(true));
    }

    @Test
    public void gameIsOver(){
        Game game = GameFactory.getGame();
        game.setGameOver(true);

        final IsGameNotOverRule rule = new IsGameNotOverRule(game);

        Boolean isGameNotOver = rule.validate(null);

        assertThat(isGameNotOver, is(false));
    }
}
