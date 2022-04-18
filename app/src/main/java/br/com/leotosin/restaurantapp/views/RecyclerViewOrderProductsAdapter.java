package br.com.leotosin.restaurantapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.models.OrderLine;
import br.com.leotosin.restaurantapp.models.Product;
import br.com.leotosin.restaurantapp.util.ImageViewUtil;

public class RecyclerViewOrderProductsAdapter extends RecyclerView.Adapter<RecyclerViewOrderProductsAdapter.ViewHolder> {

    private final List<OrderLine> listProducts;

    public RecyclerViewOrderProductsAdapter(List<OrderLine> list) {

        this.listProducts = list;
    }

    @NonNull
    @Override
    public RecyclerViewOrderProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_order_products, parent, false);

        return new RecyclerViewOrderProductsAdapter.ViewHolder(list);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewOrderProductsAdapter.ViewHolder holder, int position) {
        OrderLine line = this.listProducts.get(position);
        Product product = line.getProduct();

        holder.getName().setText(product.getName());
        holder.getPrice().setText(line.getLineTotalBrl());
        holder.getQty().setText(line.getQtyFormated());
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView productName;
        private final TextView productPrice;
        private final TextView productQty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.product_name);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);
            productQty = (TextView) itemView.findViewById(R.id.product_qty);
        }

        public TextView getName() {
            return productName;
        }

        public TextView getPrice() {
            return productPrice;
        }

        public TextView getQty() {
            return productQty;
        }
    }
}
