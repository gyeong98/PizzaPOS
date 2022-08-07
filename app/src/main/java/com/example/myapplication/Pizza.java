package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is to define Array List of the toppings, price and size of our pizza.
 * @author Geon Yeong Kim
 */
public abstract class Pizza implements Serializable {

    static final double EXTRA_TOPPING = 1.49;
    static final double BASIC_DELUXE = 12.99;
    static final double BASIC_HAWAIIAN = 10.99;
    static final double BASIC_PEPPERONI = 8.99;
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;
    public abstract double price();

    /**
     * this method returns the string of the print statement for pizza.
     * @return string of the print statement
     */
    @Override
    public String toString(){
        return "";
    }
}
