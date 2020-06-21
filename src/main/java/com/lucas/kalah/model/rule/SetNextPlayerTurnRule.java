package com.lucas.kalah.model.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Player;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.model.pit.House;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class SetNextPlayerTurnRule implements GameRule {

    private Game game;

    /**
     * Validate if last seed fall into player's End zone
     *
     * @param turn
     * @return
     */
    @Override
    public Boolean validate(Turn turn) {
        final int houseIndex = turn.getHouseIndex();
        final House movePit = (House) game.getBoard().getPits().get(houseIndex);

        int lastPit = houseIndex + movePit.getNumberOfSeeds();

        if ( lastPit > 13 ){
            lastPit = lastPit - 14;
        }

        final int playerEndzoneIndex = turn.getPlayer().getLastPitIndex();

        return lastPit == playerEndzoneIndex;
    };

    public Boolean validate(Turn turn, int lastIndex){
        final int playerEndzoneIndex = turn.getPlayer().getLastPitIndex();

        return lastIndex == playerEndzoneIndex;
    }

    public Game setNextPlayerMove(Turn turn, int lastIndex){
        if (this.validate(turn, lastIndex)){
            this.game.setNextTurnPlayer(turn.getPlayer());
        }
        else{
            Player player = game.getPlayers().stream()
                    .filter(playerEach -> !playerEach.getName().equals(turn.getPlayer().getName()))
                    .collect(Collectors.toList())
                    .get(0);

            this.game.setNextTurnPlayer(player);
        }

        return this.game;
    }
}
