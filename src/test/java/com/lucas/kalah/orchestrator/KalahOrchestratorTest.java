package com.lucas.kalah.orchestrator;

import com.lucas.kalah.model.game.*;
import com.lucas.kalah.util.KalahConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(MockitoJUnitRunner.class)
public class KalahOrchestratorTest {

    private List<Player> players = new ArrayList<Player>();

    @BeforeEach
    public void initializePlayers(){
        final Player playerOne = new Player(KalahConstants.PLAYER_ONE, 6, 0);
        final Player playerTwo = new Player(KalahConstants.PLAYER_TWO, 13, 7);

        this.players.add(playerOne);
        this.players.add(playerTwo);
    }

    @Test
    public void PlayerOneMovesFirstIndex_4NextPitsMustIncrease_and_NextPlayerShouldBeTwo(){
        final KalahOrchestrator kalahOrchestrator = new KalahOrchestratorImpl();

        Player player = new Player(KalahConstants.PLAYER_ONE, 6, 0);

        final Turn turn = new Turn(player, 0);

        Game game = kalahOrchestrator.performMove(turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(0).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(1).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(2).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(3).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(4).getNumberOfSeeds(), is(5) );

        assertThat(game.getNextTurnPlayer(), notNullValue());
        assertThat(game.getNextTurnPlayer().getName(), is(KalahConstants.PLAYER_TWO));
    }

    @Test
    public void PlayerOneStealHouse2FromPlayerTwo_and_NextPLayerShoulBeTwo(){
        Game game = GameFactory.getGame();

        final KalahOrchestrator kalahOrchestrator = new KalahOrchestratorImpl();

        game.getBoard().getPits().get(4).setNumberOfSeeds(0);

        ReflectionTestUtils.setField(kalahOrchestrator, "game", game);

        Board board = kalahOrchestrator.getGame().getBoard();

        assertThat(board.getPits().get(4).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(8).getNumberOfSeeds(), is(4) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(0) );

        final Turn turn = new Turn(this.players.get(0), 0);

        kalahOrchestrator.performMove(turn);

        game = kalahOrchestrator.getGame();
        board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(4).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(8).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(5) );

        assertThat(game.getNextTurnPlayer(), notNullValue());
        assertThat(game.getNextTurnPlayer().getName(), is(KalahConstants.PLAYER_TWO));

    }

    @Test
    public void PlayerOneMovesThirdIndex_PlayerOneShouldPlayAgain(){
        final KalahOrchestrator kalahOrchestrator = new KalahOrchestratorImpl();

        Player player = new Player(KalahConstants.PLAYER_ONE, 6, 0);

        final Turn turn = new Turn(player, 2);

        Game game = kalahOrchestrator.performMove(turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(2).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(3).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(4).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(5).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(1) );

        assertThat(game.getNextTurnPlayer(), notNullValue());
        assertThat(game.getNextTurnPlayer().getName(), is(KalahConstants.PLAYER_ONE));
    }

    @Test
    public void PlayerTwoMovesThirdIndex_PlayerTwoShouldPlayAgain(){
        Game game = GameFactory.getGame();
        game.setNextTurnPlayer(this.players.get(1));

        final KalahOrchestrator kalahOrchestrator = new KalahOrchestratorImpl();

        ReflectionTestUtils.setField(kalahOrchestrator, "game", game);

        final Turn turn = new Turn(this.players.get(1), 9);

        game = kalahOrchestrator.performMove(turn);

        Board board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(9).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(10).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(11).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(12).getNumberOfSeeds(), is(5) );
        assertThat(board.getPits().get(13).getNumberOfSeeds(), is(1) );

        assertThat(game.getNextTurnPlayer(), notNullValue());
        assertThat(game.getNextTurnPlayer().getName(), is(KalahConstants.PLAYER_TWO));

        assertThat(game.getGameOver(), is(false));
    }

    @Test
    public void PlayerOnePerformMove_AndEndsTheGame(){
        Game game = GameFactory.getGame();

        //Initialize a Board almost empty
        int firstHouseIndex = this.players.get(0).getFirstPitIndex();
        int lastHouseIndex = this.players.get(1).getLastPitIndex();

        for ( int i = firstHouseIndex; i< lastHouseIndex; i++ ){
            game.getBoard().getPits().get(i).setNumberOfSeeds(0);
        }

        final KalahOrchestrator kalahOrchestrator = new KalahOrchestratorImpl();

        game.getBoard().getPits().get(5).setNumberOfSeeds(1);

        ReflectionTestUtils.setField(kalahOrchestrator, "game", game);

        Board board = kalahOrchestrator.getGame().getBoard();

        assertThat(board.getPits().get(5).getNumberOfSeeds(), is(1) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(0) );

        final Turn turn = new Turn(this.players.get(0), 5);

        kalahOrchestrator.performMove(turn);

        game = kalahOrchestrator.getGame();
        board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(5).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(6).getNumberOfSeeds(), is(1) );

        assertThat(game.getNextTurnPlayer(), notNullValue());
        assertThat(game.getNextTurnPlayer().getName(), is(KalahConstants.PLAYER_ONE));

        assertThat(game.getGameOver(), is(true));
    }

    @Test
    public void PlayerTwoPerformMove_AndEndsTheGame(){
        Game game = GameFactory.getGame();
        game.setNextTurnPlayer(this.players.get(1));

        //Initialize a Board almost empty
        int firstHouseIndex = this.players.get(0).getFirstPitIndex();
        int lastHouseIndex = this.players.get(1).getLastPitIndex();

        for ( int i = firstHouseIndex; i< lastHouseIndex; i++ ){
            game.getBoard().getPits().get(i).setNumberOfSeeds(0);
        }

        final KalahOrchestrator kalahOrchestrator = new KalahOrchestratorImpl();

        game.getBoard().getPits().get(12).setNumberOfSeeds(1);

        ReflectionTestUtils.setField(kalahOrchestrator, "game", game);

        Board board = kalahOrchestrator.getGame().getBoard();

        assertThat(board.getPits().get(12).getNumberOfSeeds(), is(1) );
        assertThat(board.getPits().get(13).getNumberOfSeeds(), is(0) );

        final Turn turn = new Turn(this.players.get(1), 12);

        kalahOrchestrator.performMove(turn);

        game = kalahOrchestrator.getGame();
        board = game.getBoard();

        assertThat(board, notNullValue());
        assertThat(board.getPits().get(12).getNumberOfSeeds(), is(0) );
        assertThat(board.getPits().get(13).getNumberOfSeeds(), is(1) );

        assertThat(game.getNextTurnPlayer(), notNullValue());
        assertThat(game.getNextTurnPlayer().getName(), is(KalahConstants.PLAYER_TWO));

        assertThat(game.getGameOver(), is(true));
    }
}
