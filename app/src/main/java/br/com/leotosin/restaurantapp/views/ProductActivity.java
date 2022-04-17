package br.com.leotosin.restaurantapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.models.Product;
import br.com.leotosin.restaurantapp.viewModels.ProductViewModel;

public class ProductActivity extends AppCompatActivity {

    private Spinner productType;
    private final ProductViewModel viewModel;
    private ImageView productImage;
    private RecyclerView recyclerViewAvailableProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        this.initActivityFields();
        this.renderProductTypesSpinner();
        this.renderRecyclerView();
    }

    public ProductActivity() {
        viewModel = new ProductViewModel();
    }

    public void initActivityFields() {
        productType = findViewById(R.id.product_type);
        recyclerViewAvailableProducts = this.findViewById(R.id.recycler_available_products);
    }

    private void renderRecyclerView() {
        RecyclerViewAvailableProductsAdapter adapter = new RecyclerViewAvailableProductsAdapter(viewModel.listProductsByType(productType.getSelectedItem().toString()));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewAvailableProducts.setLayoutManager(layoutManager);
        recyclerViewAvailableProducts.setHasFixedSize(true);
        recyclerViewAvailableProducts.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewAvailableProducts.setAdapter(adapter);

        recyclerViewAvailableProducts.addOnItemTouchListener(
                new RecyclerViewClickListener(this, recyclerViewAvailableProducts ,new RecyclerViewClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        TextView productName = (TextView) view.findViewById(R.id.product_name);
                        viewModel.addProduct(productName.getText().toString());
                        Intent intent = new Intent(getBaseContext(), ProductDetailActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }

    private void renderProductTypesSpinner() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Bebida");
        arrayList.add("Comida");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productType.setAdapter(arrayAdapter);
        productType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                renderRecyclerView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}