package br.com.leotosin.restaurantapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.models.Order;
import br.com.leotosin.restaurantapp.models.OrderLine;
import br.com.leotosin.restaurantapp.viewModels.OrderViewModel;
import br.com.leotosin.restaurantapp.viewModels.ServiceViewModel;


public class InvoiceOrderActivity extends AppCompatActivity {

    private OrderViewModel viewModel;
    private TextView tableNumber;
    private RecyclerView recyclerViewInvoiceProducts;
    private RecyclerViewInvoiceProductsAdapter adapter;
    private TextView tipText;
    private TextView orderSubtotal;
    private TextView orderTotal;
    private Button btnPayWithTip;
    private Button btnPayNoTip;

    private static final String TAG = "CS65";

    public InvoiceOrderActivity(){
        viewModel = new OrderViewModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_order);

        this.getOrderId();
        this.initFields();
        this.renderRecyclerView();


    }

    private void initFields(){
        tableNumber = (TextView) findViewById(R.id.table_number);
        tipText = (TextView) findViewById(R.id.tip);
        orderSubtotal = (TextView) findViewById(R.id.order_subtotal);
        orderTotal = (TextView) findViewById(R.id.order_total);
        btnPayWithTip = (Button) findViewById(R.id.invoice_tip);
        btnPayNoTip = (Button) findViewById(R.id.invoice_no_tip);
        recyclerViewInvoiceProducts = findViewById(R.id.adapter_recycler_invoice_products);

        tableNumber.setText("Mesa:" + viewModel.getOrderById().getTable().getNumber());
        Double subTotal = this.getSubtotal(viewModel.getOrderById().getProducts());
        orderSubtotal.setText("Subtotal R$: "+ Double.toString(subTotal));
        Double total = this.getTotal(viewModel.getOrderById().getProducts());
        orderTotal.setText("Total R$: "+ Double.toString(total));

        btnPayWithTip.setOnClickListener(v ->{
            viewModel.invoiceOrder();
            Intent intent = new Intent(getBaseContext(), OrderListActivity.class);
            startActivity(intent);
        });

        btnPayNoTip.setOnClickListener(v ->{
            viewModel.invoiceOrder();
            Intent intent = new Intent(getBaseContext(), OrderListActivity.class);
            startActivity(intent);
        });
    }

    private void getOrderId() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            viewModel.setOrderId(extras.getString("order_id"));
        }
    }

    private double getSubtotal(ArrayList<OrderLine> orderLines){
        double subTotal = 0;
        for(int i = 0; i < orderLines.size(); i++){
            subTotal = subTotal + (orderLines.get(i).getProduct().getPrice() * orderLines.get(i).getQty());
        }
        return subTotal;
    }

    private double getTotal(ArrayList<OrderLine> orderLines){
        Double total = getSubtotal(orderLines);
        return total * 1.10;
    }

    private void renderRecyclerView() {
        RecyclerViewInvoiceProductsAdapter adapter = new RecyclerViewInvoiceProductsAdapter(viewModel.listAllOrderProducts());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewInvoiceProducts.setLayoutManager(layoutManager);
        recyclerViewInvoiceProducts.setHasFixedSize(true);
        recyclerViewInvoiceProducts.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewInvoiceProducts.setAdapter(adapter);
        recyclerViewInvoiceProducts.addOnItemTouchListener(
                new RecyclerViewClickListener(this, recyclerViewInvoiceProducts ,new RecyclerViewClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }


    /*private void enableInvoiceOrder() {

        boolean result = viewModel.orderSubtotal() > 0;

        if (result) {
            btnInvoiceOrder.setBackgroundColor(getResources().getColor(R.color.green));
        } else {
            btnInvoiceOrder.setBackgroundColor(getResources().getColor(R.color.grey));
        }

        btnInvoiceOrder.setClickable(result);
        btnInvoiceOrder.setEnabled(result);
    }*/

}
