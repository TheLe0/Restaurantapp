package br.com.leotosin.restaurantapp.views;

import androidx.appcompat.app.AppCompatActivity;
import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.viewModels.ServiceViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ServiceActivity extends AppCompatActivity {

    private TextView tableNumber;
    private final ServiceViewModel viewModel;
    private Button btnAddProduct;
    private TextView orderSubtotal;
    private Button btnInvoiceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        this.initFields();

        String amount = new DecimalFormat("0.00").format(viewModel.orderSubtotal());

        tableNumber.setText(tableNumber.getText()+" "+viewModel.findTableNumber());
        orderSubtotal.setText("R$ "+amount);

    }

    public ServiceActivity() {
        viewModel = new ServiceViewModel();
    }

    private void initFields() {
        tableNumber = (TextView) findViewById(R.id.tableNumber);
        orderSubtotal = (TextView) findViewById(R.id.order_subtotal);
        btnAddProduct = (Button) findViewById(R.id.addProduct);
        btnInvoiceOrder = (Button) findViewById(R.id.invoiceOrder);
        enableInvoiceOrder();

        btnInvoiceOrder.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        });

        btnAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), ProductActivity.class);
            startActivity(intent);
        });
    }

    private void enableInvoiceOrder() {

        boolean result = viewModel.orderSubtotal() > 0;

        if (result) {
            btnInvoiceOrder.setBackgroundColor(getResources().getColor(R.color.green));
        } else {
            btnInvoiceOrder.setBackgroundColor(getResources().getColor(R.color.grey));
        }

        btnInvoiceOrder.setClickable(result);
        btnInvoiceOrder.setEnabled(result);
    }
}