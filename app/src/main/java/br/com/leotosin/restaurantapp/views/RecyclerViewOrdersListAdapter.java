package br.com.leotosin.restaurantapp.views;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.models.Order;

public class RecyclerViewOrdersListAdapter extends RecyclerView.Adapter<RecyclerViewOrdersListAdapter.ViewHolder>{

    private final List<Order> listOrders;

    public RecyclerViewOrdersListAdapter(List<Order> list) {
        this.listOrders = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView orderNumber;
        private final TextView tableNumber;
        private final Button buttonInvoice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumber = (TextView) itemView.findViewById(R.id.order_number);
            tableNumber = (TextView) itemView.findViewById(R.id.table_number);
            buttonInvoice = (Button) itemView.findViewById(R.id.pay_order);
        }

        public TextView getOrderNumber() {return orderNumber;}
        public TextView getTableNumber() {return tableNumber;}
        public Button getButtonInvoice() {return buttonInvoice;}
    }


    @NonNull
    @Override
    public RecyclerViewOrdersListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_orders_list, parent, false);

        return new RecyclerViewOrdersListAdapter.ViewHolder(list);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = listOrders.get(position);

        holder.getTableNumber().setText(order.getTable().getNumber());
        holder.getOrderNumber().setText(order.getOrderId());
        if(order.isInvoiced()){
            holder.getButtonInvoice().setText("Pagar");
        } else {
            holder.getButtonInvoice().setVisibility(View.GONE);
        }

       /* holder.getButtonInvoice().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!listOrders.get(position).isInvoiced()) {

                } else {
                }
            }
        });*/
    }
    @Override
    public int getItemCount() {
        return this.listOrders.size();
    }
}
