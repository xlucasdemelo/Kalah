package com.lucas.kalah.model.rule;

import com.lucas.kalah.model.Turn;

public interface GameRule {
    public Boolean validate(Turn turn);
}
