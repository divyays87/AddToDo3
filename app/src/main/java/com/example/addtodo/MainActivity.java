package com.example.addtodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button btnAdd;
    EditText etadd;
    RecyclerView rvitems;
    ItemsAdapter itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        etadd = findViewById(R.id.etadd);
        rvitems = findViewById(R.id.rvitems);



        items=new ArrayList<>();
        items.add("green gram");
        items.add("black chenna");
        items.add("roasted rice");

        ItemsAdapter.OnLongClickListener onLongClickListner= new ItemsAdapter.OnLongClickListener(){
            @Override
            public void onItemLongClicked(int position) {
                //delete the item from the model
                    items.remove(position);
                //notify the adaptor
                    itemsAdapter.notifyItemRemoved(position);
                    Toast.makeText(getApplicationContext(),"item has been removed", Toast.LENGTH_SHORT);


            }
        };

        itemsAdapter = new ItemsAdapter(items, onLongClickListner);
        rvitems.setAdapter(itemsAdapter);
         rvitems.setLayoutManager(new LinearLayoutManager(this));


         btnAdd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String todoItem = etadd.getText().toString();

                 //Add item to model
                    items.add(todoItem);
                 //Notify adaptor that item is inserted

                 itemsAdapter.notifyItemInserted(items.size()-1);
                 etadd.setText("");
                 Toast.makeText(getApplicationContext(), "item added", Toast.LENGTH_SHORT).show();



             }
         });
    }
}