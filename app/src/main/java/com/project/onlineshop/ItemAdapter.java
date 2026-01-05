package com.project.onlineshop;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.onlineshop.R;
import com.project.onlineshop.Item;
import com.project.onlineshop.ItemDetailsActivity;

import java.util.List;

/**
 * ItemAdapter
 * -----------
 * This adapter is used to display items in a RecyclerView.
 * It binds item data to the item_row layout.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private static List<Item> itemList;
    private static OnItemLongClickListener longClickListener;

    /**
     * Constructor
     */
    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    /**
     * ViewHolder class
     * Holds UI references for one row
     */
    public static class ItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener {

        ImageView itemImage;
        TextView itemName;
        TextView itemPrice;
        TextView name, price;

        public ItemViewHolder(View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu,
                                        View v,
                                        ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem delete = menu.add("Delete Item");

            delete.setOnMenuItemClickListener(menuItem -> {
                if (longClickListener != null) {
                    longClickListener.onDelete(
                            itemList.get(getAdapterPosition()),
                            getAdapterPosition()
                    );
                }
                return true;
            });
        }
    }

    /**
     * Inflate layout for each row
     */
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_row, parent, false);

        return new ItemViewHolder(view);
    }

    /**
     * Bind data to views
     */
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Item item = itemList.get(position);

        holder.itemName.setText(item.getName());
        holder.itemPrice.setText("Price: " + item.getPrice());
        holder.itemImage.setImageResource(item.getImageResId());

        // Handle item click
        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, ItemDetailsActivity.class);

            // Pass item data
            intent.putExtra("itemName", item.getName());
            intent.putExtra("itemPrice", item.getPrice());
            intent.putExtra("itemDesc", item.getDescription());
            intent.putExtra("itemImage", item.getImageResId());

            context.startActivity(intent);
        });
    }

    /**
     * Number of items
     */
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface OnItemLongClickListener {
        void onDelete(Item item, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }

}
