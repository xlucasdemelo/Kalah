package com.lucas.kalah;

import com.lucas.kalah.model.Board;
import com.lucas.kalah.model.Game;
import com.lucas.kalah.model.Player;
import com.lucas.kalah.model.Turn;
import com.lucas.kalah.util.KalahConstants;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    private List<Player> players = new ArrayList<Player>();

    @BeforeEach
    public void initializePlayers(){
        final Player playerOne = new Player(KalahConstants.PLAYER_ONE, 6);
        final Player playerTwo = new Player(KalahConstants.PLAYER_TWO, 13);

        this.players.add(playerOne);
        this.players.add(playerTwo);
    }

    @Test
    public void PlayerOneMovesFirstIndex_4Next_pits_must_increase(){
        Game game = new Game(this.players);

        final Turn turn = new Turn(this.players.get(0), 0);

        game.moveSeeds(turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(0).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(1).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(2).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(3).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(4).getNumberOfSeeds(), is(5) );
    }

}
