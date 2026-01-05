package com.project.onlineshop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.onlineshop.R;
import com.project.onlineshop.OrderDetailsActivity;
import com.project.onlineshop.Order;

import java.util.List;

/**
 * OrderAdapter
 * ------------
 * This adapter displays orders in a RecyclerView.
 * It binds Order data to order_row layout.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orderList;

    /**
     * Constructor
     */
    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    /**
     * ViewHolder class
     * Holds UI references for one order row
     */
    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView totalPrice;
        TextView orderDate;

        public OrderViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.orderItemName);
            totalPrice = itemView.findViewById(R.id.orderTotalPrice);
            orderDate = itemView.findViewById(R.id.orderDate);
        }
    }

    /**
     * Inflate layout for each row
     */
    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.order_row, parent, false);

        return new OrderViewHolder(view);
    }

    /**
     * Bind data to views
     */
    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {

        Order order = orderList.get(position);

        holder.itemName.setText(order.getItemName());
        holder.totalPrice.setText("Total: " + order.getTotalPrice());
        holder.orderDate.setText(order.getOrderDate());

        // Handle order click
        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, OrderDetailsActivity.class);

            // Pass order details
            intent.putExtra("itemName", order.getItemName());
            intent.putExtra("quantity", order.getQuantity());
            intent.putExtra("totalPrice", order.getTotalPrice());
            intent.putExtra("orderDate", order.getOrderDate());

            intent.putExtra("customerName", order.getCustomerName());
            intent.putExtra("phone", order.getPhone());
            intent.putExtra("address", order.getAddress());

            context.startActivity(intent);
        });
    }

    /**
     * Number of orders
     */
    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
