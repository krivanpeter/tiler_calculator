package com.example.tiler_buddy;

public class ObstacleInputException extends Exception {

    public ObstacleInputException(){
        super("You did not enter all numbers");
    }
}
