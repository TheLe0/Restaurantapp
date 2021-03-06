package br.com.leotosin.restaurantapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.viewModels.ServiceViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ServiceActivity extends AppCompatActivity {

    private TextView tableNumber;
    private final ServiceViewModel viewModel;
    private TextView orderSubtotal;
    private Button btnInvoiceOrder;
    private RecyclerView recyclerViewOrderProducts;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        this.getOrderId();
        this.initFields();
        this.renderRecyclerView();

        String amount = new DecimalFormat("0.00").format(viewModel.orderSubtotal());

        tableNumber.setText(tableNumber.getText()+" "+ viewModel.getTableNumber());
        orderSubtotal.setText("R$ "+amount);

    }

    public ServiceActivity() {
        viewModel = new ServiceViewModel();
    }

    private void initFields() {
        tableNumber = (TextView) findViewById(R.id.tableNumber);
        orderSubtotal = (TextView) findViewById(R.id.order_subtotal);
        Button btnAddProduct = (Button) findViewById(R.id.addProduct);
        btnInvoiceOrder = (Button) findViewById(R.id.invoiceOrder);
        btnBack = (Button) findViewById(R.id.back);
        enableInvoiceOrder();

        btnInvoiceOrder.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), InvoiceOrderActivity.class);
            intent.putExtra("order_id", viewModel.getOrderId());
            startActivity(intent);
        });

        btnAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), ProductActivity.class);
            intent.putExtra("order_id", viewModel.getOrderId());
            startActivity(intent);
        });

        btnBack.setOnClickListener(v ->{
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        });

        recyclerViewOrderProducts = findViewById(R.id.recycler_items_list);
    }

    private void renderRecyclerView() {
        RecyclerViewOrderProductsAdapter adapter = new RecyclerViewOrderProductsAdapter(viewModel.listAllOrderProducts());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOrderProducts.setLayoutManager(layoutManager);
        recyclerViewOrderProducts.setHasFixedSize(true);
        recyclerViewOrderProducts.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewOrderProducts.setAdapter(adapter);
        recyclerViewOrderProducts.addOnItemTouchListener(
                new RecyclerViewClickListener(this, recyclerViewOrderProducts ,new RecyclerViewClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        TextView productName = (TextView) view.findViewById(R.id.product_name);
                        Intent intent = new Intent(getBaseContext(), ProductEditActivity.class);
                        intent.putExtra("order_id", viewModel.getOrderId());
                        intent.putExtra("product", productName.getText().toString());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }

    private void getOrderId() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            viewModel.setOrderId(extras.getString("order_id"));
        }
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