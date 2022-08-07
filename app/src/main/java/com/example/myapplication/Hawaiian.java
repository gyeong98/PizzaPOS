package com.example.myapplication;

import java.io.Serializable;

/**
 * This class is to define the object Hawaiian pizza.
 * @author Geon Yeong Kim
 */
public class Hawaiian extends Pizza implements Serializable {

    static final int START_TOPPING = 2;

    /**
     * This constructor is to add basic necessity of Hawaiian Pizza.
     */
    public Hawaiian(){
        toppings.add(Topping.PINEAPPLE);
        toppings.add(Topping.HAM);
    }

    /**
     * Calculate the price of the pizza for Hawaiian.
     * @return returns the price for the certain mix for Hawaiian.
     */
    @Override
    public double price() {
        int toppingsAmount = toppings.size();
        int extraToppings = toppingsAmount - START_TOPPING;
        if (size.equals(Size.SMALL)) {
            if (toppingsAmount > START_TOPPING) {
                return BASIC_HAWAIIAN + extraToppings * EXTRA_TOPPING;
            } else {
                return BASIC_HAWAIIAN;
            }
        } else if (size.equals(Size.MEDIUM)) {
            if (toppingsAmount > START_TOPPING) {
                return BASIC_HAWAIIAN + extraToppings * EXTRA_TOPPING + Size.MEDIUM.getSize();
            } else {
                return BASIC_HAWAIIAN + Size.MEDIUM.getSize();
            }
        } else if (size.equals(Size.LARGE)) {
            if (toppingsAmount > START_TOPPING) {
                return BASIC_HAWAIIAN + extraToppings * EXTRA_TOPPING + Size.LARGE.getSize();
            } else {
                return BASIC_HAWAIIAN + Size.LARGE.getSize();
            }
        }
        return 0;
    }

    /**
     * this method returns the string of the print statement for pizza.
     * @return string of the print statement
     */
    @Override
    public String toString() {
        return super.toString() + "Hawaiian / Toppings: " + toppings.toString() +  " / Size: " + size.toString() + " / Price: " + String.format("%.2f", price()) + "\n";
    }
}
