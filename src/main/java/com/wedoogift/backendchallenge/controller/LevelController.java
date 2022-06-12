package com.wedoogift.backendchallenge.controller;

import com.wedoogift.backendchallenge.dto.LevelDTO;
import com.wedoogift.backendchallenge.facade.LevelFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class LevelController {

    private final LevelFacade levelFacade;

    @GetMapping("{levelNumber}")
    public ResponseEntity<LevelDTO> level(@PathVariable String levelNumber)  {
        return ResponseEntity.ok(levelFacade.findAll(levelNumber));
    }

    @PostMapping("/save/{userId}/{distributionAmount}/{typeWallet}")
    public void saveDistribution( @PathVariable long userId,
                                  @PathVariable int distributionAmount,
                                  @PathVariable String typeWallet) {
        levelFacade.calculateBalance(distributionAmount, userId, typeWallet);
    }
}
