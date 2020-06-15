package com.lucas.kalah.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Turn {
    private Player player;

    private int houseIndex;
}
