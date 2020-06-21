package com.lucas.kalah;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.GameFactory;
import com.lucas.kalah.orchestrator.KalahOrchestrator;
import com.lucas.kalah.orchestrator.KalahOrchestratorImpl;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FullGameTest {

    Game game = GameFactory.getGame();

    KalahOrchestrator orchestrator = new KalahOrchestratorImpl();

    public void play(){



    }

}
