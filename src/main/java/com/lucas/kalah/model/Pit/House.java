package com.lucas.kalah.model.Pit;

import com.lucas.kalah.model.Pit.Pit;

public class House extends Pit {

    public House (int numberOfSeed){
        super(numberOfSeed);
    }

    public void moveSeeds(){
        this.numberOfSeeds = 0;
    }
}
