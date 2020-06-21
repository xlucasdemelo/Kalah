package com.lucas.kalah.model.game;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private String name;

    private int lastPitIndex;

    private int firstPitIndex;

}
