package com.example.addtodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responcible for displaying data from model to a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

    //Data for the model is a  list of string. So creating a consructor
    public ItemsAdapter(List<String> items,OnLongClickListener longClickListner) {
        this.items= items;
        this.longClickListener= longClickListner;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflater to inflate the view
        View todoView= LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);


        //Wrap it inside a view holder and return it

        return new ViewHolder(todoView);
    }

    @Override
    //responcible for binding data to a particular viewholder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //grab the item to a position
        String item = items.get(position);

        //bind the item to a specified viewholder
        holder.bind(item);



    }
//tells us how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //Container is to provide easy access to views that represent each rows of the list

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem= itemView.findViewById(android.R.id.text1);
        }

        //update the view inside the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //Notify the listner which position was long pressed

                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
