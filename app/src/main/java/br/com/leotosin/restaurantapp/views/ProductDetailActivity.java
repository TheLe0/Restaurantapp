package br.com.leotosin.restaurantapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.models.Product;
import br.com.leotosin.restaurantapp.util.ImageViewUtil;
import br.com.leotosin.restaurantapp.viewModels.ProductDetailViewModel;

public class ProductDetailActivity extends AppCompatActivity {

    private final ProductDetailViewModel viewModel;

    public ProductDetailActivity() {
        viewModel = new ProductDetailViewModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        initProductFields();
    }

    private void initProductFields() {

        Product product = viewModel.getSelectedProduct();

        TextView productName = findViewById(R.id.productName);
        productName.setText(product.getName());

        ImageView productImage = findViewById(R.id.productImage);
        productImage.setImageResource(ImageViewUtil.findImageResourceIdFromImageName(product.getPicture()));

        TextView productDescription = findViewById(R.id.productDescription);
        productDescription.setText(product.getDescription());

        TextView productPrice = findViewById(R.id.productPrice);
        productPrice.setText(product.getPriceBrl());

        Button btnAddProduct = findViewById(R.id.addProduct);

        EditText productQty = findViewById(R.id.productQty);

        btnAddProduct.setOnClickListener(v -> {
            viewModel.addProductToOrder(productQty.getText().toString());
            Intent intent = new Intent(getBaseContext(), ServiceActivity.class);
            startActivity(intent);
        });

    }
}