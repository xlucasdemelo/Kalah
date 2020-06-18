package com.lucas.kalah.move;

import com.lucas.kalah.model.Board;
import com.lucas.kalah.model.Game;
import com.lucas.kalah.model.move.StealMove;
import com.lucas.kalah.model.Player;
import com.lucas.kalah.model.Turn;
import com.lucas.kalah.util.KalahConstants;
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
public class StealMoveTest {

    private List<Player> players = new ArrayList<Player>();

    @BeforeEach
    public void initializePlayers(){
        final Player playerOne = new Player(KalahConstants.PLAYER_ONE, 6, 0);
        final Player playerTwo = new Player(KalahConstants.PLAYER_TWO, 13, 7);

        this.players.add(playerOne);
        this.players.add(playerTwo);
    }

    @Test
    public void PlayerOneStealHouse2FromPlayerTwo(){
        Game game = new Game(this.players);

        game.getBoard().getPits().get(4).setNumberOfSeeds(1);

        Board board = game.getBoard();

        assertThat(board.getPits().get(4).getNumberOfSeeds(), is(1) );
        assertThat(board.getPits().get(8).getNumberOfSeeds(), is(4) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(0) );

        final Turn turn = new Turn(this.players.get(0), 4);

        StealMove stealMove = new StealMove();
        stealMove.move(game, turn);

        board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(4).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(8).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(5) );
    }

    @Test
    public void PlayerOneStealHouse3_With6Seeds_FromPlayerTwo(){
        Game game = new Game(this.players);

        game.getBoard().getPits().get(3).setNumberOfSeeds(1);
        game.getBoard().getPits().get(9).setNumberOfSeeds(6);

        Board board = game.getBoard();

        assertThat(board.getPits().get(3).getNumberOfSeeds(), is(1) );
        assertThat(board.getPits().get(9).getNumberOfSeeds(), is(6) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(0) );

        final Turn turn = new Turn(this.players.get(0), 3);

        StealMove stealMove = new StealMove();
        stealMove.move(game, turn);

        board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(3).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(9).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(7) );
    }

    @Test
    public void PlayerTwoStealHouse2FromPlayerOne(){
        Game game = new Game(this.players);

        game.getBoard().getPits().get(11).setNumberOfSeeds(1);

        Board board = game.getBoard();

        assertThat(board.getPits().get(11).getNumberOfSeeds(), is(1) );
        assertThat(board.getPits().get(1).getNumberOfSeeds(), is(4) );
        assertThat(board.getPits().get(13).getNumberOfSeeds(), is(0) );

        final Turn turn = new Turn(this.players.get(1), 11);

        StealMove stealMove = new StealMove();
        stealMove.move(game, turn);

        board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(11).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(1).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(13).getNumberOfSeeds(), is(5) );
    }
}
