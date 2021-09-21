package com.uhg.opioid.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Controller {
    @Autowired
    OpioidService service;

    @RequestMapping(method = RequestMethod.GET, value = "")
    ResponseEntity<String> home(){
        try{
            return new ResponseEntity<>("Welcome to Opioid Auto-mater", HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stage")
    ResponseEntity<Object> getFromMainStage(){
        try {
            return new ResponseEntity<>(service.getStage(), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/prod")
    ResponseEntity<Object> getFromMainProd(){
        try {
            return new ResponseEntity<>(service.getProd(), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/stage/load")
    ResponseEntity<Object> loadToStages(@RequestBody ArrayList<Opioid> list){
        try {
            int r = service.postStage(list);
            return new ResponseEntity<>("Stage tables have been successfully updated with " + Integer.toString(r) + " rows of data", HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/prod/load")
    ResponseEntity<Object> loadToProds(@RequestBody ArrayList<Opioid> list){
        try {
            int r = service.postProd(list);
            return new ResponseEntity<>("Production tables have been successfully updated " + Integer.toString(r) + " rows of data", HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.BAD_GATEWAY);
        }
    }
}
