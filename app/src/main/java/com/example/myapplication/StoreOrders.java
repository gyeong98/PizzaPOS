package com.example.myapplication;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class will keep list of customers that ordered
 * @author Geon Yeong Kim
 */
public class StoreOrders implements Serializable {

    private ArrayList<Order> ordersList = new ArrayList<Order>();

    /**
     * get the store orders.
     *
     * @return returns array list of store orders.
     */
    public ArrayList<Order> getOrdersList() {
        return ordersList;
    }

    /**
     * get the size of the Store orders
     * @return size of the store orders
     */
    public int getSize(){
        return ordersList.size();
    }
    /**
     * send an array list of all the numbers in the store orders.
     *
     * @return returns the array list of numbers.
     */
    public ArrayList<String> getAllNumbers() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < ordersList.size(); i++) {
            result.add(ordersList.get(i).getCustomerNumber());
        }
        return result;
    }


    /**
     * This method will check whether the customer is already in our orders.
     *
     * @param number unique number of the customer.
     * @return returns false is the customer is within the system, return true otherwise.
     */
    public boolean checkDuplicate(Long number) {
        for (int i = 0; i < ordersList.size(); i++) {
            if (Long.parseLong(ordersList.get(i).getCustomerNumber()) == number) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method will add the customer's order to the Store order to start prep.
     *
     * @param order will be the order placed.
     * @return return a confirmed string.
     */
    public String addOrder(Order order) {
        ordersList.add(order);
        return "The order has been placed successfully.";
    }

    /**
     * deletes an order from the array list
     * @param i is the index we need to delete
     */
    public void deleteOrder(int i) {
        ordersList.remove(i);
    }

    /**
     * Print the list of confirmed orders.
     */
    public String print() {
        String result = "";
        for (int i = 0; i < ordersList.size(); i++) {
            result = result + ordersList.get(i).print();
        }
        return result;
    }

    /**
     * this method will return the index of a specific customer number.
     *
     * @param number customer's number.
     * @return returns the index of array.
     */
    public int printStore(String number) {
        int indexFound = 0;
        for (int i = 0; i < ordersList.size(); i++) {
            if (ordersList.get(i).getCustomerNumber().equals(number)) {
                indexFound = i;
                break;
            }
        }
        return indexFound;

    }

    /**
     * This method will print the string of my order
     * @param i index
     * @return string
     */
    public String printString(int i){
        return ordersList.get(i).print();
    }
}
