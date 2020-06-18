package com.lucas.kalah.model.move;

import com.lucas.kalah.model.Game;
import com.lucas.kalah.model.Turn;
import com.lucas.kalah.model.rule.GameRule;

import java.util.ArrayList;

public interface GameMove {

    public void validate(ArrayList<GameRule> rules);

    public void move(Game game, Turn turn);

    public Game outPut();
}
