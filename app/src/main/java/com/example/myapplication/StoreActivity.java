package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity for Store Orders view
 * @author Geon Yeong Kim
 */
public class StoreActivity extends AppCompatActivity {

    private TextView storeBox, totalPrice;
    private StoreOrders storeOrders;
    private Spinner numberSpinner;
    private int savedPosition;
    ArrayAdapter<String> adapter;
    private View view;

    /**
     * initializer for the store activity view.
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        totalPrice = findViewById(R.id.totalPrice);

        numberSpinner = findViewById(R.id.numberSpinner);
        storeBox = findViewById(R.id.storeBox);
        storeBox.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        storeOrders = (StoreOrders) intent.getSerializableExtra("STOREORDERS");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                storeOrders.getAllNumbers());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberSpinner.setAdapter(adapter);
        numberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id){
                storeBox.setText(storeOrders.printString(position));
                totalPrice.setText(storeOrders.getOrdersList().get(position).getTotalPrice());
                savedPosition = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){

            }
        });
    }

    /** button to cancel an order
     * @param view view
     */
    public void cancelOrder(View view){
        this.view = view;
        if(storeOrders.getSize() != 0){
            storeOrders.deleteOrder(savedPosition);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                    storeOrders.getAllNumbers());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            numberSpinner.setAdapter(adapter);

        }
        else if(storeOrders.getSize() == 1){
            storeBox.setText(null);
            storeOrders.deleteOrder(savedPosition);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                    storeOrders.getAllNumbers());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            numberSpinner.setAdapter(adapter);
        }
        else{
            Toast.makeText(this, "There's nothing to cancel", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This method is to keep the data even when we move to the previous activity
     */
    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("STOREORDERS", storeOrders);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
