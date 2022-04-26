package br.com.leotosin.restaurantapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.models.OrderLine;


public class RecyclerViewInvoiceProductsAdapter extends RecyclerView.Adapter<RecyclerViewInvoiceProductsAdapter.ViewHolder>{

    private final List<OrderLine> listOrderLines;

    public RecyclerViewInvoiceProductsAdapter(List<OrderLine> list) {
        this.listOrderLines = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView productName;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.product_name);
            price = (TextView) itemView.findViewById(R.id.product_price);
        }

        public TextView getProductName() {return productName;}
        public TextView getPrice() {return price;}
    }


    @NonNull
    @Override
    public RecyclerViewInvoiceProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_invoice_products, parent, false);

        return new RecyclerViewInvoiceProductsAdapter.ViewHolder(list);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewInvoiceProductsAdapter.ViewHolder holder, int position) {
        OrderLine orderLine = listOrderLines.get(position);

        holder.getProductName().setText(orderLine.getProduct().getName());
        holder.getPrice().setText(lineTotalPriceToString(position));
    }

    private String lineTotalPriceToString(int position){
        OrderLine orderLine = listOrderLines.get(position);

        return orderLine.getQtyFormated() +" x "+ orderLine.getProduct().getPrice() +" = " +
                orderLine.getQty()*orderLine.getProduct().getPrice();
    }

    @Override
    public int getItemCount() {
        return listOrderLines.size();
    }
}
