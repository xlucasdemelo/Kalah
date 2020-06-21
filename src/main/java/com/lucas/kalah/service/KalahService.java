package com.lucas.kalah.service;

import com.lucas.kalah.dto.GameDTO;
import com.lucas.kalah.dto.TurnDTO;

/**
 * class that will have the businness logic and access to the DB
 */
public interface KalahService {
    public GameDTO performMove(TurnDTO turnDTO);

    public GameDTO getGame();
}
