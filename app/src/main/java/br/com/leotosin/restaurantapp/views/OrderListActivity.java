package br.com.leotosin.restaurantapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.viewModels.OrderViewModel;

public class OrderListActivity extends AppCompatActivity {

    private OrderViewModel viewModel;
    private RecyclerView recyclerViewOrdersList;
    private RecyclerViewOrdersListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        viewModel = new OrderViewModel();
        recyclerViewOrdersList = this.findViewById(R.id.recycler_order_list);
        adapter = new RecyclerViewOrdersListAdapter(viewModel.getAllOrders());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOrdersList.setLayoutManager(layoutManager);
        recyclerViewOrdersList.setHasFixedSize(true);
        recyclerViewOrdersList.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewOrdersList.setAdapter(adapter);

        recyclerViewOrdersList.addOnItemTouchListener(
                new RecyclerViewClickListener(this, recyclerViewOrdersList, new RecyclerViewClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        TextView orderId = (TextView) view.findViewById(R.id.order_number);
                        String order = orderId.getText().toString();
                        order = order.replace("Pedido:","");
                        Intent intent = new Intent(getBaseContext(), InvoiceOrderActivity.class);
                        intent.putExtra("order_id", order);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }
}