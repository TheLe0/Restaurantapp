package br.com.leotosin.restaurantapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.viewModels.ProductViewModel;

public class ProductActivity extends AppCompatActivity {

    private Spinner productType;
    private final ProductViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        this.renderProductTypesSpinner();
    }

    public ProductActivity() {
        viewModel = new ProductViewModel();
    }

    private void renderProductTypesSpinner() {
        productType = findViewById(R.id.product_type);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Bebida");
        arrayList.add("Comida");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productType.setAdapter(arrayAdapter);
        productType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tutorialsName = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}