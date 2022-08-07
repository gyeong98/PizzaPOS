package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is to create instance of order object for a customer to use.
 * @author Geon Yeong Kim
 */
public class Order implements Serializable {

    private ArrayList<Pizza> pizzaOrders = new ArrayList<Pizza>();
    private String customerNumber;
    static final double TAX_RATE= 0.06625;

    /**
     * will contain the array list of pizzas.
     * @return return the array list of pizzas.
     */
    public ArrayList<Pizza> getPizzaOrders(){
        return pizzaOrders;
    }

    /**
     * will contain the size of the array list.
     * @return return the size of the array list.
     */
    public int getSize(){
        return pizzaOrders.size();
    }

    /**
     * this method is to get the customer's number.
     * @return returns customer number.
     */
    public String getCustomerNumber(){
        return customerNumber;
    }

    /**
     * set the customer's number into the Order class.
     * @param number is the customer's unique number.
     */
    public String setCustomerNumber(String number){
        this.customerNumber = number;
        return "Successful! You may now press on any picture of pizzas to begin the order!";
    }

    /**
     * This method is to add pizzas the customer wants to order.
     * @param pizza pizza that is set.
     * @return return a confirmed string.
     */

    public String addPizza(Pizza pizza){
        pizzaOrders.add(pizza);
        return "Successful! Pizza is added to your order.";
    }

    /**
     * remove a pizza from the order list.
     * @param pizzaIndex pizza that is to be removed.
     * @return return a confirmed string.
     */
    public void removePizza(int pizzaIndex){
        pizzaOrders.remove(pizzaIndex);
    }

    /**
     * print the order list.
     */
    public String print(){
        String result = "";
        for(int i = 0; i < pizzaOrders.size(); i++){
            result = result + pizzaOrders.get(i).toString();
        }
        return result;
    }

    /**
     * this method will calculate the total price of the pizzas in the list.
     * @return return the total price.
     */
    public String getTotalPrice(){
        double result = 0;
        for(int i =0; i < pizzaOrders.size(); i++){
            result = result + pizzaOrders.get(i).price();
        }
        result = result + (result * TAX_RATE);
        return String.format("%.2f", result);

    }
}
