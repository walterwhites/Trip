package com.ecommerce.adventure.controller;

import com.ecommerce.adventure.dao.AdventureDao;
import com.ecommerce.adventure.exception.AdventureNotFoundException;
import com.ecommerce.adventure.model.Adventure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "List all adventures")
    @GetMapping(value = "adventures")
    public List<Adventure> adventureList() {
        return adventureDao.findAll();
    }

    @ApiOperation(value = "Display an adventure")
    @GetMapping(value = "adventures/{id}")
    public Adventure displayAdventure(@PathVariable int id) throws AdventureNotFoundException {
        Adventure adventure = adventureDao.findById(id);
        if (adventure == null) throw new AdventureNotFoundException("Adventure with id " + id + " doesn't exist");
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
}
