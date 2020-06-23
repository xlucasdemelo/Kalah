package com.lucas.kalah.controller;

import com.lucas.kalah.dto.GameDTO;
import com.lucas.kalah.dto.TurnDTO;
import com.lucas.kalah.model.game.Turn;
import com.lucas.kalah.service.KalahService;
import com.lucas.kalah.service.KalahServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * This class is the controller that exposes public endpoints
 * 		in order to the application could be consumed
 *
 * @author lucas
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/kalah")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KalahController {

    KalahService kalahService;

    @Autowired
    public KalahController( KalahServiceImpl kalahService ){
        this.kalahService = kalahService;
    }

    /**
     *
     * @param turn
     * @return
     */
    @ApiOperation("Perform a Movement of seeds")
    @ApiResponses({
            @ApiResponse(code = 200, message = "It will return the state of the game after the move"),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameDTO> performMove( @RequestBody(required = true) TurnDTO turn ){
        log.info(turn.toString());

        final GameDTO gameDTO = this.kalahService.performMove(turn);

        return new ResponseEntity<GameDTO>(gameDTO, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @ApiOperation("Return the state of the game")
    @ApiResponses({
            @ApiResponse(code = 200, message = "It will return the state of the game"),
    })
    @GetMapping
    public ResponseEntity<GameDTO> getGame(){
        final GameDTO gameDTO = this.kalahService.getGame();

        return new ResponseEntity<GameDTO>(gameDTO, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @ApiOperation("Restart the game")
    @ApiResponses({
            @ApiResponse(code = 200, message = "It will return the state of a new game"),
    })
    @PatchMapping(value = "/restartGame")
    public ResponseEntity<GameDTO> restartGame(){
        final GameDTO gameDTO = this.kalahService.restartGame();

        return new ResponseEntity<GameDTO>(gameDTO, HttpStatus.OK);
    }
}
