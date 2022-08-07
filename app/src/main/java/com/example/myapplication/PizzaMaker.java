package com.example.myapplication;

import java.io.Serializable;

/**
 * This class is to create an instance of pizza.
 * @author Geon Yeong Kim
 */
public class PizzaMaker implements Serializable {

    /**
     * This method creates an instance of pizza.
     * @param flavor flavor the flavor of three pizzas we have.
     * @return a pizza instance we created.
     */
    public static Pizza createPizza(String flavor){
        Pizza newPizza;
        if(flavor.equals("Deluxe")){
            newPizza = new Deluxe();
        }
        else if(flavor.equals("Hawaiian")){
            newPizza = new Hawaiian();
        }
        else if(flavor.equals("Pepperoni")){
            newPizza = new Pepperoni();
        }
        else {
            return null;
        }
        return newPizza;
    }
}
