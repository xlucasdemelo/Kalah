package com.lucas.kalah.model.game;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Turn {
    private Player player;

    private int houseIndex;
}
