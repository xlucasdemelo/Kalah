package com.lucas.kalah.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Player;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.model.move.CalculateLastPitSeed;
import com.lucas.kalah.model.rule.SetGameOverRule;
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
public class SetGameOverRuleTest {

    private List<Player> players = new ArrayList<Player>();

    @BeforeEach
    public void initializePlayers(){
        final Player playerOne = new Player(KalahConstants.PLAYER_ONE, 6, 0);
        final Player playerTwo = new Player(KalahConstants.PLAYER_TWO, 13, 7);

        this.players.add(playerOne);
        this.players.add(playerTwo);
    }

    @Test
    public void PlayerOneEndSeeds_SetGameOver(){
        Game game = new Game(this.players);

        //Initialize a Board almost empty
        int firstHouseIndex = this.players.get(0).getFirstPitIndex();
        int lastHouseIndex = this.players.get(1).getLastPitIndex();

        for ( int i = firstHouseIndex; i< lastHouseIndex; i++ ){
            game.getBoard().getPits().get(i).setNumberOfSeeds(0);
        }

        game.getBoard().getPits().get(5).setNumberOfSeeds(0);

        assertThat(game.getGameOver(), is(false));

        final Turn turn = new Turn(this.players.get(0), 5);

        SetGameOverRule rule = new SetGameOverRule(game);
        game = rule.setGameOver(turn);

        assertThat(game.getGameOver(), is(true));
    }

}
