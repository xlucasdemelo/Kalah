package com.lucas.kalah.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.GameFactory;
import com.lucas.kalah.model.game.Player;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.model.rule.CanMoveRule;
import com.lucas.kalah.model.rule.DefineWinnerRule;
import com.lucas.kalah.model.rule.GameRule;
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
public class DefineWinnerRuleTest {

    private List<Player> players = new ArrayList<Player>();

    @BeforeEach
    public void initializePlayers(){
        final Player playerOne = new Player(KalahConstants.PLAYER_ONE, 6, 0);
        final Player playerTwo = new Player(KalahConstants.PLAYER_TWO, 13, 7);

        this.players.add(playerOne);
        this.players.add(playerTwo);
    }

    @Test
    public void PlayerOneWinsTheGame(){
        Game game = GameFactory.getGame();

        //Initialize a Player One empty Board
        int firstHouseIndex = this.players.get(0).getFirstPitIndex();
        int lastHouseIndex = this.players.get(0).getLastPitIndex();
        for ( int i = firstHouseIndex; i < lastHouseIndex; i++ ){
            game.getBoard().getPits().get(i).setNumberOfSeeds(0);
        }

        game.getBoard().getPits().get(6).setNumberOfSeeds(30);

        final DefineWinnerRule rule = new DefineWinnerRule(game);

        game = rule.defineWinner();

        assertThat(game.getWinner(), is(KalahConstants.PLAYER_ONE));
    }

    @Test
    public void PlayerTwoWinsTheGame(){
        Game game = GameFactory.getGame();

        //Initialize a Player One empty Board
        int firstHouseIndex = this.players.get(0).getFirstPitIndex();
        int lastHouseIndex = this.players.get(0).getLastPitIndex();
        for ( int i = firstHouseIndex; i < lastHouseIndex; i++ ){
            game.getBoard().getPits().get(i).setNumberOfSeeds(0);
        }

        game.getBoard().getPits().get(6).setNumberOfSeeds(20);

        final DefineWinnerRule rule = new DefineWinnerRule(game);

        game = rule.defineWinner();

        assertThat(game.getWinner(), is(KalahConstants.PLAYER_TWO));
    }

    @Test
    public void draw(){
        Game game = GameFactory.getGame();

        final DefineWinnerRule rule = new DefineWinnerRule(game);

        game = rule.defineWinner();

        assertThat(game.getWinner(), is(KalahConstants.DRAW));
    }
}
