package com.lucas.kalah.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.GameFactory;
import com.lucas.kalah.model.game.Player;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.model.rule.GameRule;
import com.lucas.kalah.model.rule.HouseHasSeedsRule;
import com.lucas.kalah.model.rule.IsNotEndZoneRule;
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
public class HouseHasSeedsRuleTest {

    private List<Player> players = new ArrayList<Player>();

    @BeforeEach
    public void initializePlayers(){
        final Player playerOne = new Player(KalahConstants.PLAYER_ONE, 6, 0);
        final Player playerTwo = new Player(KalahConstants.PLAYER_TWO, 13, 7);

        this.players.add(playerOne);
        this.players.add(playerTwo);
    }

    @Test
    public void PlayerOneMoveHouseWithSeeds_MustPass(){
        Game game = GameFactory.getGame();

        Turn turn = new Turn(this.players.get(0), 5);

        final GameRule houseHasSeedsRule = new HouseHasSeedsRule(game);
        Boolean houseHasSeeds = houseHasSeedsRule.validate(turn);

        assertThat(houseHasSeeds, is(true));
    }

    @Test
    public void PlayerOneMoveHouseWithoutSeeds_MustReturnFalse(){
        Game game = GameFactory.getGame();
        game.getBoard().getPits().get(0).setNumberOfSeeds(0);

        Turn turn = new Turn(this.players.get(0), 0);

        final GameRule houseHasSeedsRule = new HouseHasSeedsRule(game);
        Boolean houseHasSeeds = houseHasSeedsRule.validate(turn);

        assertThat(houseHasSeeds, is(false));
    }

    @Test
    public void PlayerTwoMoveHouseWithSeeds_MustPass(){
        Game game = GameFactory.getGame();

        Turn turn = new Turn(this.players.get(1), 11);

        final GameRule houseHasSeedsRule = new HouseHasSeedsRule(game);
        Boolean houseHasSeeds = houseHasSeedsRule.validate(turn);

        assertThat(houseHasSeeds, is(true));
    }

    @Test
    public void PlayerTwoMoveHouseWithoutSeeds_MustReturnFalse(){
        Game game = GameFactory.getGame();
        game.getBoard().getPits().get(11).setNumberOfSeeds(0);

        Turn turn = new Turn(this.players.get(1), 11);

        final GameRule houseHasSeedsRule = new HouseHasSeedsRule(game);
        Boolean houseHasSeeds = houseHasSeedsRule.validate(turn);

        assertThat(houseHasSeeds, is(false));
    }
}
