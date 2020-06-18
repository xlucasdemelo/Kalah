package com.lucas.kalah.model.pit;

import lombok.Data;

@Data
public abstract class Pit {

    protected int numberOfSeeds = 0;

    public Pit(int numberOfSeeds) {
        this.numberOfSeeds = numberOfSeeds;
    }

    public void receiveSeeds(int numberOfSeeds){
        this.numberOfSeeds += numberOfSeeds;
    };

}
