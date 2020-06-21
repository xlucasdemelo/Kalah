package com.lucas.kalah.model.game;

import com.lucas.kalah.model.pit.EndZone;
import com.lucas.kalah.model.pit.House;
import com.lucas.kalah.model.pit.Pit;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Board {
    private static final int SEEDS_PER_HOUSE = 4;
    private static final int HOUSES_PER_PLAYER = 6;
    private static final int END_ZONES_PER_PLAYER = 1;
    private static final int PLAYERS = 2;

    private List<Pit> pits = new ArrayList<Pit>();

    //Board class will have the responsability to create itself
    public Board(){
        this.initializeBoard();
    }

    private void initializeBoard(){
        for (int i = 0; i < PLAYERS; i++){
            this.initializeHouses();
            this.initializeEndzones();
        }
    }

    private void initializeHouses(){
        for (int i = 0; i < HOUSES_PER_PLAYER; i++){
            final House house = new House(SEEDS_PER_HOUSE);
            this.pits.add(house);
        }
    }

    private void initializeEndzones(){
        for (int i = 0; i < END_ZONES_PER_PLAYER; i++){
            final int maxSeeds = SEEDS_PER_HOUSE * HOUSES_PER_PLAYER;
            final EndZone endZone = new EndZone(0, maxSeeds);
            this.pits.add(endZone);
        }
    }
}
