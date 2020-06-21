package com.lucas.kalah.service;

import com.lucas.kalah.dto.GameDTO;
import com.lucas.kalah.dto.TurnDTO;
import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Player;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.orchestrator.KalahOrchestrator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of Kalah Service
 */
@Slf4j
@Service
public class KalahServiceImpl implements KalahService {

    private KalahOrchestrator orchestrator;

    @Autowired
    public KalahServiceImpl(KalahOrchestrator kalahOrchestrator ){
        this.orchestrator = kalahOrchestrator;
    }

    @Override
    public GameDTO performMove(TurnDTO turnDTO) {
        Turn turn = this.getTurnFromDTO(turnDTO);
        Game game = this.orchestrator.performMove(turn);

        return new GameDTO(game);
    }


    private Turn getTurnFromDTO(TurnDTO turnDTO){
        Player player = this.orchestrator.getGame().getPlayerFromName(turnDTO.getPlayerName());
        Turn turn = new Turn(player, turnDTO.getHouseIndex());
        return turn;
    }

    @Override
    public GameDTO getGame() {
        return new GameDTO(this.orchestrator.getGame());
    }

    @Override
    public GameDTO restartGame() {
        return new GameDTO(this.orchestrator.restartGame());
    }

}
