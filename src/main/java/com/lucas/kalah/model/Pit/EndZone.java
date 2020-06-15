package com.lucas.kalah.model.Pit;

import com.lucas.kalah.model.Pit.Pit;
import lombok.Data;

@Data
public class EndZone extends Pit {
    private int maxSeeds;

    public EndZone (int numberOfSeed, int maxSeeds){
        super(numberOfSeed);
        this.maxSeeds = maxSeeds;
    }
}
