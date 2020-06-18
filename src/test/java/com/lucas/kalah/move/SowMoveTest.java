package com.lucas.kalah.move;

import com.lucas.kalah.model.Board;
import com.lucas.kalah.model.Game;
import com.lucas.kalah.model.move.SowMove;
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
public class SowMoveTest {
    private List<Player> players = new ArrayList<Player>();

    @BeforeEach
    public void initializePlayers(){
        final Player playerOne = new Player(KalahConstants.PLAYER_ONE, 6, 0);
        final Player playerTwo = new Player(KalahConstants.PLAYER_TWO, 13, 7);

        this.players.add(playerOne);
        this.players.add(playerTwo);
    }

    @Test
    public void PlayerOneMovesFirstIndex_4Next_pits_must_increase(){
        Game game = new Game(this.players);

        final Turn turn = new Turn(this.players.get(0), 0);

        SowMove sowMove = new SowMove();
        sowMove.move(game, turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(0).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(1).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(2).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(3).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(4).getNumberOfSeeds(), is(5) );
    }

    @Test
    public void PlayerOneMovesFourthIndex_end_zone_must_have_one_value_and_enemy_must_have_one_more(){
        Game game = new Game(this.players);

        final Turn turn = new Turn(this.players.get(0), 3);

        SowMove sowMove = new SowMove();
        sowMove.move(game, turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(3).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(4).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(5).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(1) );
        assertThat(board.getPits().get(7).getNumberOfSeeds(), is(5) );
    }

    @Test
    public void PlayerOneMoves8SeedsFromSixthIndex_must_jump_enemies_end_zone(){
        Game game = new Game(this.players);

        game.getBoard().getPits().get(5).setNumberOfSeeds(8);

        final Turn turn = new Turn(this.players.get(0), 5);

        SowMove sowMove = new SowMove();
        sowMove.move(game, turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(5).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(1) );
        assertThat(board.getPits().get(7).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(8).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(9).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(10).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(11).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(12).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(13).getNumberOfSeeds(), is(0 ));
        assertThat(board.getPits().get(0).getNumberOfSeeds(), is(5) );
    }

    @Test
    public void PlayerOneMoves10SeedsFromSixthIndex_must_jump_enemies_end_zone(){
        Game game = new Game(this.players);

        game.getBoard().getPits().get(5).setNumberOfSeeds(10);

        final Turn turn = new Turn(this.players.get(0), 5);

        SowMove sowMove = new SowMove();
        sowMove.move(game, turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(5).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(1) );
        assertThat(board.getPits().get(7).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(8).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(9).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(10).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(11).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(12).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(13).getNumberOfSeeds(), is(0 ));
        assertThat(board.getPits().get(0).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(1).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(2).getNumberOfSeeds(), is(5) );
    }

    @Test
    public void PlayerTwoMovesFirstIndex_4Next_pits_must_increase(){
        Game game = new Game(this.players);

        final Turn turn = new Turn(this.players.get(1), 7);

        SowMove sowMove = new SowMove();
        sowMove.move(game, turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(7).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(8).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(9).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(10).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(11).getNumberOfSeeds(), is(5) );
    }

    @Test
    public void PlayerTwoMovesThirdIndex_4Next_pits_must_increase_end_zone_must_have_one_value(){
        Game game = new Game(this.players);

        final Turn turn = new Turn(this.players.get(1), 9);

        SowMove sowMove = new SowMove();
        sowMove.move(game, turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(9).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(10).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(11).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(12).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(13).getNumberOfSeeds(), is(1) );
    }

    @Test
    public void PlayerTwoMovesFourthIndex_end_zone_must_have_one_value_and_enemy_must_have_one_more(){
        Game game = new Game(this.players);

        final Turn turn = new Turn(this.players.get(1), 10);

        SowMove sowMove = new SowMove();
        sowMove.move(game, turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(10).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(11).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(12).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(13).getNumberOfSeeds(), is(1) );
        assertThat(board.getPits().get(1).getNumberOfSeeds(), is(5) );
    }

    @Test
    public void PlayerTwoMovesFifthIndex_end_zone_must_have_one_value_and_enemy_must_have_two_more(){
        Game game = new Game(this.players);

        final Turn turn = new Turn(this.players.get(1), 11);

        SowMove sowMove = new SowMove();
        sowMove.move(game, turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(11).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(12).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(13).getNumberOfSeeds(), is(1) );
        assertThat(board.getPits().get(0).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(1).getNumberOfSeeds(), is(5) );
    }

    @Test
    public void PlayerTwoMoves8SeedsFromTwelveIndex_must_jump_enemies_end_zone(){
        Game game = new Game(this.players);

        game.getBoard().getPits().get(12).setNumberOfSeeds(8);

        final Turn turn = new Turn(this.players.get(1), 12);

        SowMove sowMove = new SowMove();
        sowMove.move(game, turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(12).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(13).getNumberOfSeeds(), is(1) );
        assertThat(board.getPits().get(0).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(1).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(2).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(3).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(4).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(5).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(0 ));
        assertThat(board.getPits().get(7).getNumberOfSeeds(), is(5) );
    }
}
