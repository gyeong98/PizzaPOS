package com.example.myapplication;

import java.io.Serializable;

/**
 * This class defines the sizes we have for our pizza
 * @author Geon Yeong Kim
 */
public enum Size implements Serializable {
    SMALL(0.00),
    MEDIUM(2.00),
    LARGE(4.00);

    private final double size;

    /**
     * This method is to check which size the user wants to use
     * @param size is the constructor parameter that's already defined in the enum
     */
    Size(double size){
        this.size = size;
    }

    /**
     * This method will send the actual double size of the enum
     * @return returns the double value
     */
    public double getSize(){
        return size;
    }
}
