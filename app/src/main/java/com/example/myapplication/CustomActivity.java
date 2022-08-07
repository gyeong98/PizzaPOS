package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.io.Serializable;

/**
 * this is the custom activity where we will decide on the size and toppings of pizzas
 * @author Geon Yeong Kim
 */
public class CustomActivity extends AppCompatActivity implements Serializable {

    private Order newOrder;
    private ImageView pizzaImage;
    private String pizzaChoice;
    private Switch pepperoni, mushroom, extraCheese, pineapple, ham, olives, onion, blackolives, greenPepper, freshGarlic;
    private Pizza newPizza;
    private Spinner sizeSpinner;
    private TextView totalBox;
    static final double MAX_TOPPING = 7;
    private boolean maxToppingLimited;

    /**
     * constructor just in case
     */
    public CustomActivity(){
    }

    /**
     * initializing once we move to the activity
     * @param savedInstanceState Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        pepperoni = findViewById(R.id.pepperoni);
        mushroom = findViewById(R.id.mushroom);
        extraCheese = findViewById(R.id.extraCheese);
        pineapple = findViewById(R.id.pineapple);
        ham = findViewById(R.id.ham);
        olives = findViewById(R.id.olives);
        onion = findViewById(R.id.onion);
        blackolives = findViewById(R.id.blackOlives);
        greenPepper = findViewById(R.id.greenPepper);
        freshGarlic = findViewById(R.id.freshGarlic);
        totalBox = findViewById(R.id.totalBox);

        Intent intent = getIntent();
        newOrder = (Order) intent.getSerializableExtra("ORDER");
        pizzaChoice = intent.getStringExtra("CHOICE");

        pizzaImage = findViewById(R.id.imageView);
        if(pizzaChoice.equals("Deluxe")){
            pizzaImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.deluxe));
            newPizza = PizzaMaker.createPizza("Deluxe");
            pepperoni.setChecked(true);
            onion.setChecked(true);
            pineapple.setChecked(true);
            blackolives.setChecked(true);
            mushroom.setChecked(true);
            newPizza.size = Size.SMALL;
        }
        else if(pizzaChoice.equals("Hawaiian")){
            pizzaImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.hawaiian));
            newPizza = PizzaMaker.createPizza("Hawaiian");
            pineapple.setChecked(true);
            ham.setChecked(true);
            newPizza.size = Size.SMALL;
        }
        else if(pizzaChoice.equals("Pepperoni")){
            pizzaImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pepperoni));
            newPizza = PizzaMaker.createPizza("Pepperoni");
            pepperoni.setChecked(true);
            newPizza.size = Size.SMALL;
        }

        totalBox.setText(String.valueOf(newPizza.price()));

        pepperoni.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                newPizza.toppings.add(Topping.PEPPERONI);
                maxToppingLimited = maxTopping(Topping.PEPPERONI);
                if(maxToppingLimited){
                    pepperoni.setChecked(false);
                }
                totalBox.setText(String.valueOf(newPizza.price()));
            }
            else{
                newPizza.toppings.remove(Topping.PEPPERONI);
                totalBox.setText(String.valueOf(newPizza.price()));
            }
        });

        mushroom.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                newPizza.toppings.add(Topping.MUSHROOM);
                maxToppingLimited = maxTopping(Topping.MUSHROOM);
                if(maxToppingLimited){
                    mushroom.setChecked(false);
                }
                totalBox.setText(String.valueOf(newPizza.price()));
            }
            else{
                newPizza.toppings.remove(Topping.MUSHROOM);
                totalBox.setText(String.valueOf(newPizza.price()));
            }
        });

        extraCheese.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                newPizza.toppings.add(Topping.EXTRACHEESE);
                maxToppingLimited = maxTopping(Topping.EXTRACHEESE);
                if(maxToppingLimited){
                    extraCheese.setChecked(false);
                }
                totalBox.setText(String.valueOf(newPizza.price()));
            }
            else{
                newPizza.toppings.remove(Topping.EXTRACHEESE);
                totalBox.setText(String.valueOf(newPizza.price()));
            }
        });

        pineapple.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                newPizza.toppings.add(Topping.PINEAPPLE);
                maxToppingLimited = maxTopping(Topping.PINEAPPLE);
                if(maxToppingLimited){
                    pineapple.setChecked(false);
                }
                totalBox.setText(String.valueOf(newPizza.price()));
            }
            else{
                newPizza.toppings.remove(Topping.PINEAPPLE);
                totalBox.setText(String.valueOf(newPizza.price()));
            }
        });

        ham.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                newPizza.toppings.add(Topping.HAM);
                maxToppingLimited = maxTopping(Topping.HAM);
                if(maxToppingLimited){
                    ham.setChecked(false);
                }
                totalBox.setText(String.valueOf(newPizza.price()));
            }
            else{
                newPizza.toppings.remove(Topping.HAM);
                totalBox.setText(String.valueOf(newPizza.price()));
            }
        });

        olives.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                newPizza.toppings.add(Topping.OLIVES);
                maxToppingLimited = maxTopping(Topping.OLIVES);
                if(maxToppingLimited){
                    olives.setChecked(false);
                }
                totalBox.setText(String.valueOf(newPizza.price()));
            }
            else{
                newPizza.toppings.remove(Topping.OLIVES);
                totalBox.setText(String.valueOf(newPizza.price()));
            }
        });

        onion.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                newPizza.toppings.add(Topping.ONION);
                maxToppingLimited = maxTopping(Topping.ONION);
                if(maxToppingLimited){
                    onion.setChecked(false);
                }
                totalBox.setText(String.valueOf(newPizza.price()));
            }
            else{
                newPizza.toppings.remove(Topping.ONION);
                totalBox.setText(String.valueOf(newPizza.price()));
            }
        });

        blackolives.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                newPizza.toppings.add(Topping.BLACKOLIVES);
                maxToppingLimited = maxTopping(Topping.BLACKOLIVES);
                if(maxToppingLimited){
                    blackolives.setChecked(false);
                }
                totalBox.setText(String.valueOf(newPizza.price()));
            }
            else{
                newPizza.toppings.remove(Topping.BLACKOLIVES);
                totalBox.setText(String.valueOf(newPizza.price()));
            }
        });

        greenPepper.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                newPizza.toppings.add(Topping.GREENPEPPER);
                maxToppingLimited = maxTopping(Topping.GREENPEPPER);
                if(maxToppingLimited){
                    greenPepper.setChecked(false);
                }
                totalBox.setText(String.valueOf(newPizza.price()));
            }
            else{
                newPizza.toppings.remove(Topping.GREENPEPPER);
                totalBox.setText(String.valueOf(newPizza.price()));
            }
        });

        freshGarlic.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                newPizza.toppings.add(Topping.FRESHGARLIC);
                maxToppingLimited = maxTopping(Topping.FRESHGARLIC);
                if(maxToppingLimited){
                    freshGarlic.setChecked(false);
                }
                totalBox.setText(String.valueOf(newPizza.price()));
            }
            else{
                newPizza.toppings.remove(Topping.FRESHGARLIC);
                totalBox.setText(String.valueOf(newPizza.price()));
            }
        });

        sizeSpinner = findViewById(R.id.sizeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.size));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            /**
             * listener to keep track of selecting item on my spinner
             * @param arg0 arg0
             * @param arg1 arg1
             * @param position position
             * @param id id
             */
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id){
                if(position == 0){
                    newPizza.size = Size.SMALL;
                    totalBox.setText(String.valueOf(newPizza.price()));
                }
                else if(position == 1){
                    newPizza.size = Size.MEDIUM;
                    totalBox.setText(String.valueOf(newPizza.price()));
                }
                else if(position == 2){
                    newPizza.size = Size.LARGE;
                    totalBox.setText(String.valueOf(newPizza.price()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0){
            }
        });
    }

    /**
     * This method will remove the topping once it goes over 7 toppings
     * @param topping topping we decide to remove
     */
    public boolean maxTopping(Topping topping){
        if(newPizza.toppings.size() > MAX_TOPPING){
            newPizza.toppings.remove(topping);
            Toast.makeText(this, "Cannot have more than 7 toppings", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    /**
     * Adds order to the Order object we created.
     * @param view View
     */
    public void addOrder(View view){
        newOrder.addPizza(newPizza);
        Intent intent = new Intent();
        intent.putExtra("KEYNAME", newOrder);
        setResult(Activity.RESULT_OK, intent);
        Toast.makeText(this, "Added successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }


}
