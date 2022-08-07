package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;

/**
 * This class is our main activity class. This class will allow us to start the project and implement
 * what we need for the main activity as soon as we open the app
 * @author Geon Yeong Kim
 */
public class MainActivity extends AppCompatActivity implements Serializable{

    private TextView phoneNumber;
    private Button submitButton;
    private ImageButton deluxeButton, hawaiianButton, pepperoniButton;
    private String pizzaChoice;
    private StoreOrders storeOrders;
    private Order newOrder;
    private String reset;
    static final int PHONE_NUMBER = 10;

    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                /**
                 * opening another activity and receive the data
                 * @param result result
                 */
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        assert data != null;
                        newOrder = (Order) data.getSerializableExtra("KEYNAME");
                    }
                }
            });

    ActivityResultLauncher<Intent> launchCurrentActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                /**
                 * opening another activity and receive the data
                 * @param result result
                 */
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        assert data != null;
                        newOrder = (Order) data.getSerializableExtra("ORDER");
                        storeOrders = (StoreOrders) data.getSerializableExtra("STOREORDERS");
                        reset = data.getStringExtra("RESET");
                        if(reset.equals("YES")){
                            resetAfter();
                        }
                    }
                }
            });

    ActivityResultLauncher<Intent> launchStoreActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                /**
                 * opening another activity and receive the data
                 * @param result result
                 */
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        assert data != null;
                        storeOrders = (StoreOrders) data.getSerializableExtra("STOREORDERS");
                    }
                }
            });

    /**
     * empty constructor just in case
     */
    public MainActivity() {
    }

    /**
     * our initializer for the main activity.
     * @param savedInstanceState Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deluxeButton = findViewById(R.id.deluxeButton);
        hawaiianButton = findViewById(R.id.hawaiianButton);
        pepperoniButton = findViewById(R.id.pepperoniButton);
        phoneNumber = findViewById(R.id.phoneNumber);
        submitButton = findViewById(R.id.addButton);
        pizzaButtonsOnOff();
        storeOrders = new StoreOrders();
        newOrder = new Order();
        //PhoneNumberUtils.formatNumber(phoneNumber.getText().toString(), String.valueOf(1));
        //phoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    /**
     * This will allow us to turn on and off unnecessary buttons for pizza buttons.
     */
    public void pizzaButtonsOnOff(){
        if(deluxeButton.isEnabled()){
            deluxeButton.setEnabled(false);
            hawaiianButton.setEnabled(false);
            pepperoniButton.setEnabled(false);
        }
        else{
            deluxeButton.setEnabled(true);
            hawaiianButton.setEnabled(true);
            pepperoniButton.setEnabled(true);
        }
    }

    /**
     * This method will reset my project once I place an order
     */
    public void resetAfter(){
        deluxeButton.setEnabled(false);
        hawaiianButton.setEnabled(false);
        pepperoniButton.setEnabled(false);
        phoneNumber.setEnabled(true);
        submitButton.setEnabled(true);
        phoneNumber.setText("");

    }

    /**
     * This button is to submit the customer's number so that we start the order
     * @param view View
     */
    public void submitNumber(View view){
        try{
            long checkInt;
            if(phoneNumber.getText().toString().isEmpty()){
                Toast.makeText(this, "Phone number is empty", Toast.LENGTH_SHORT).show();
            }
            else{
                checkInt = Long.parseLong(phoneNumber.getText().toString());
                if(phoneNumber.length() != PHONE_NUMBER){
                    Toast.makeText(this, "Not a valid Number", Toast.LENGTH_SHORT).show();
                }
                else if(!storeOrders.checkDuplicate(checkInt)){
                    Toast.makeText(this, "Already ordered", Toast.LENGTH_SHORT).show();
                }
                else{
                    newOrder.setCustomerNumber(phoneNumber.getText().toString());
                    Toast.makeText(this, "You may now add orders", Toast.LENGTH_SHORT).show();
                    phoneNumber.setEnabled(false);
                    submitButton.setEnabled(false);
                    pizzaButtonsOnOff();
                    view = this.getCurrentFocus();
                    if(view != null){
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                    }
                }
            }
        }
        catch(NullPointerException e){
            Toast.makeText(this, "Something is obviously wrong", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Button for deluxe pizza
     * @param view View
     */
    public void addDeluxe(View view){
        pizzaChoice = "Deluxe";
        Intent intent = new Intent(this, CustomActivity.class);
        intent.putExtra("ORDER", newOrder);
        intent.putExtra("CHOICE", pizzaChoice);
        launchSomeActivity.launch(intent);
    }

    /**
     * button for hawaiian pizza
     * @param view View
     */
    public void addHawaiian(View view){
        pizzaChoice = "Hawaiian";
        Intent intent = new Intent(this, CustomActivity.class);
        intent.putExtra("ORDER", newOrder);
        intent.putExtra("CHOICE", pizzaChoice);
        launchSomeActivity.launch(intent);
    }

    /**
     * button for pepperoni pizza
     * @param view View
     */
    public void addPepperoni(View view){
        pizzaChoice = "Pepperoni";
        Intent intent = new Intent(this, CustomActivity.class);
        intent.putExtra("ORDER", newOrder);
        intent.putExtra("CHOICE", pizzaChoice);
        launchSomeActivity.launch(intent);
    }

    /**
     * button to view current orders
     * @param view view
     */
    public void viewCurrentOrders(View view){
        if(newOrder.getSize() != 0){
            Intent intent = new Intent(this, CurrentActivity.class);
            intent.putExtra("ORDER", newOrder);
            intent.putExtra("STOREORDERS", storeOrders);
            launchCurrentActivity.launch(intent);
        }
        else{
            Toast.makeText(this, "Order is currently empty", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * button to view store orders
     * @param view view
     */
    public void viewStoreOrders(View view){
        if(storeOrders.getSize() != 0){
            Intent intent = new Intent(this, StoreActivity.class);
            intent.putExtra("STOREORDERS", storeOrders);
            launchStoreActivity.launch(intent);
        }
        else{
            Toast.makeText(this, "Store order is currently empty", Toast.LENGTH_SHORT).show();
        }
    }
}