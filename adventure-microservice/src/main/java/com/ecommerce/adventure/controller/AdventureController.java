package com.ecommerce.adventure.controller;

import com.ecommerce.adventure.configuration.ApplicationPropertiesConfig;
import com.ecommerce.adventure.dao.AdventureDao;
import com.ecommerce.adventure.exception.AdventureNotFoundException;
import com.ecommerce.adventure.model.Adventure;
import com.ecommerce.adventure.requestDTO.AdventureEntrantRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(description = "Adventures management")
@RestController
public class AdventureController {

    @Autowired
    private AdventureDao adventureDao;

    @Autowired
    ApplicationPropertiesConfig applicationPropertiesConfig;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "List all adventures")
    @GetMapping(value = "adventures")
    public List<Adventure> adventureList() {
        log.info("Get all adventures datas");
        List<Adventure> adventureList = adventureDao.findAll();
        if(adventureList.isEmpty()) throw new AdventureNotFoundException("No adventures found");
        return adventureList;
    }

    @ApiOperation(value = "Display an adventure")
    @GetMapping(value = "adventures/{id}")
    public Adventure displayAdventure(@PathVariable int id) throws AdventureNotFoundException {
        Adventure adventure = adventureDao.findById(id);
        if (adventure == null) throw new AdventureNotFoundException("Adventure with id " + id + " doesn't exist");
        return adventure;
    }

    @ApiOperation(value = "Get adventure by name")
    @GetMapping(value = "adventures/names/{name}")
    public Adventure getAdventureByName(@PathVariable String name) throws AdventureNotFoundException {
        Adventure adventure = adventureDao.findByName(name);
        if (adventure == null) throw new AdventureNotFoundException("Adventure with name " + name + " doesn't exist");
        return adventure;
    }

    @ApiOperation(value = "Create an adventures")
    @PostMapping(value = "adventures")
    public ResponseEntity<Void> addAdventure(@Valid @RequestBody Adventure adventure) {
        Adventure adventure1 = adventureDao.save(adventure);
        if (adventure1 == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(adventure1.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Reduce entrant in an adventure")
    @PostMapping(value = "adventures/entrants/reduce")
    public ResponseEntity<Void> reduceEntrant(@RequestBody AdventureEntrantRequestDTO adventureEntrantRequestDTO) {
        Adventure adventure1 = adventureDao.findByName(adventureEntrantRequestDTO.getAdventure());
        adventureDao.updateMaxEntrant(adventure1.getMaxEntrant() - 1, adventureEntrantRequestDTO.getAdventure());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Up entrant in an adventure")
    @PostMapping(value = "adventures/entrants/up")
    public ResponseEntity<Void> upEntrant(@RequestBody AdventureEntrantRequestDTO adventureEntrantRequestDTO) {
        Adventure adventure1 = adventureDao.findByName(adventureEntrantRequestDTO.getAdventure());
        adventureDao.updateMaxEntrant(adventure1.getMaxEntrant() + 1, adventureEntrantRequestDTO.getAdventure());
        return ResponseEntity.ok().build();
    }
}
