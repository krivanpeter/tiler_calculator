package com.pk.tiler_buddy;

public class ObstacleInputException extends Exception {

    public ObstacleInputException(){
        super("You did not enter all numbers");
    }
}
