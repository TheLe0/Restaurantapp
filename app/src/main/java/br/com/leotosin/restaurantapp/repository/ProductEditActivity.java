package br.com.leotosin.restaurantapp.repository;

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
import br.com.leotosin.restaurantapp.viewModels.ProductEditViewModel;
import br.com.leotosin.restaurantapp.views.ServiceActivity;

public class ProductEditActivity extends AppCompatActivity {

    private final ProductEditViewModel viewModel;

    public ProductEditActivity() {
        viewModel = new ProductEditViewModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);

        this.getProduct();
        this.initProductFields();
    }

    private void getProduct() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            viewModel.setProductName(extras.getString("product"));
        }
    }

    private void initProductFields() {

        Product product = viewModel.getProduct();

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
            viewModel.update(Integer.parseInt(productQty.getText().toString()));
            Intent intent = new Intent(getBaseContext(), ServiceActivity.class);
            startActivity(intent);
        });

        Button btnDelProduct = findViewById(R.id.delProduct);

        btnDelProduct.setOnClickListener(v -> {
            viewModel.removeProduct();
            Intent intent = new Intent(getBaseContext(), ServiceActivity.class);
            startActivity(intent);
        });

    }
}