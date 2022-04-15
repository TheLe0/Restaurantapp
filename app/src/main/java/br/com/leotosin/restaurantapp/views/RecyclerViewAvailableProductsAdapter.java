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
import br.com.leotosin.restaurantapp.models.Product;
import br.com.leotosin.restaurantapp.util.ImageViewUtil;

public class RecyclerViewAvailableProductsAdapter extends RecyclerView.Adapter<RecyclerViewAvailableProductsAdapter.ViewHolder> {

    private final List<Product> listProducts;

    public RecyclerViewAvailableProductsAdapter(List<Product> list) {
        this.listProducts = list;
    }

    @NonNull
    @Override
    public RecyclerViewAvailableProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_avaliable_products, parent, false);

        return new RecyclerViewAvailableProductsAdapter.ViewHolder(list);
    }

    public Product getProductByPosition(int position) {
        return this.listProducts.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAvailableProductsAdapter.ViewHolder holder, int position) {

        Product product = this.listProducts.get(position);

        holder.getName().setText(product.getName());
        holder.getPrice().setText(product.getPriceBrl());
        holder.getImage().setImageResource(ImageViewUtil.findImageResourceIdFromImageName(product.getPicture()));
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView productImage;
        private final TextView productName;
        private final TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.product_image);
            productName = (TextView) itemView.findViewById(R.id.product_name);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);
        }

        public TextView getName() {
            return productName;
        }

        public ImageView getImage() {
            return productImage;
        }

        public TextView getPrice() {
            return productPrice;
        }
    }
}
