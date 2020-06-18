package com.lucas.kalah.model.pit;

public class House extends Pit {

    public House (int numberOfSeed){
        super(numberOfSeed);
    }

    public void moveSeeds(){
        this.numberOfSeeds = 0;
    }
}
