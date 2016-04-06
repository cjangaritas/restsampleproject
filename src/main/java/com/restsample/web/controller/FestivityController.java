package com.restsample.web.controller;

import com.restsample.data.model.Festivity;
import com.restsample.service.FestivityService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@RestController
@Api(value="/festivity" , description = "Operations about festivities")
@RequestMapping(value = "/festivity")
public class FestivityController {

    @Inject
    private FestivityService festivityService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gets all the festivities")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Festivity")})
    public ResponseEntity<List<Festivity>> getAll() {
        List<Festivity> festivities = festivityService.findAll();
        return new ResponseEntity<>(festivities, HttpStatus.OK);
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gets one festivity")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Festivity")})
    public ResponseEntity getFestivity(@PathVariable String festivityId) {
        return new ResponseEntity<>(new Festivity()/* **** ANSWER A FESTIVITIES OBJECT */, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates one festivity")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Festivity")})
    public ResponseEntity create(@RequestBody Festivity festivity){
        Festivity newFestivity = festivityService.create(festivity);
        String id = newFestivity == null ? "" : newFestivity.getId();
        URI uri = URI.create("/festivity/" + id);
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifies a festivity")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Festivity")})
    public ResponseEntity update(@Valid @RequestBody Festivity festivity) { // ********** NAME BETTER ********
        return new ResponseEntity<>(new Festivity()/* **** ANSWER A FESTIVITIES OBJECT */, HttpStatus.CREATED);
    }

}