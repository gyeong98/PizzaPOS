package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import java.io.Serializable;

/**
 * This is activity for current orders view
 * @author Geon Yeong Kim
 */
public class CurrentActivity extends AppCompatActivity implements Serializable{

    private Order newOrder;
    private StoreOrders StoreOrders;
    private RecyclerView textBox;
    private MyRecyclerViewAdapter adapter;
    private TextView totalOrder;
    private String reset = "NO";

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
        /**
         * This allows us to swipe to delete orders
         * @param recyclerView recyclerView
         * @param viewHolder viewHolder
         * @param target target
         * @return return
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Toast.makeText(CurrentActivity.this, "on Move", Toast.LENGTH_SHORT).show();
            return false;
        }

        /**
         * This is the swipe method that will allow us to swipe
         * @param viewHolder viewHolder
         * @param direction direction
         */
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Toast.makeText(CurrentActivity.this, "on Swiped", Toast.LENGTH_SHORT).show();
            int position = viewHolder.getAdapterPosition();
            newOrder.removePizza(position);
            adapter.notifyDataSetChanged();
            totalOrder.setText(String.valueOf(newOrder.getTotalPrice()));
        }
    };

    /**
     * constructor
     * @param savedInstanceState savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        Intent intent = getIntent();
        newOrder = (Order) intent.getSerializableExtra("ORDER");
        StoreOrders = (StoreOrders) intent.getSerializableExtra("STOREORDERS");

        textBox = findViewById(R.id.textBox);
        textBox.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, newOrder.getPizzaOrders());
        adapter.setClickListener(this::onItemClick);
        textBox.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(textBox);

        textBox.addItemDecoration(new DividerItemDecoration(textBox.getContext(), DividerItemDecoration.VERTICAL));

        totalOrder = findViewById(R.id.totalOrder);
        totalOrder.setText(String.valueOf(newOrder.getTotalPrice()));
    }

    /**
     * For future reference. One of the method I learned through this project
     * @param view view
     * @param position position
     */
    public void onItemClick(View view, int position){
        Toast.makeText(this, "Stop pressing on me :)", Toast.LENGTH_SHORT).show();
    }

    /**
     * Override method that will keep data even when I press back button
     */
    @Override
    public void onBackPressed(){
        //reset = "NO";
        Intent intent = new Intent();
        intent.putExtra("ORDER", newOrder);
        intent.putExtra("STOREORDERS", StoreOrders);
        intent.putExtra("RESET", reset);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    /**
     * This is a button to place a order
     * @param view view
     */
    public void placeOrder(View view){
        if(newOrder.getSize() != 0){
            StoreOrders.addOrder(newOrder);
            newOrder = new Order();
            reset = "YES";
            Intent intent = new Intent();
            intent.putExtra("ORDER", newOrder);
            intent.putExtra("RESET", reset);
            intent.putExtra("STOREORDERS", StoreOrders);
            setResult(Activity.RESULT_OK, intent);
            Toast.makeText(this, "Order has been placed", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this, "Order is empty", Toast.LENGTH_SHORT).show();
        }
    }
}
