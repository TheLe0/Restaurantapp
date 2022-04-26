package br.com.leotosin.restaurantapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.viewModels.TableViewModel;

public class MainActivity extends AppCompatActivity {

    private TableViewModel viewModel;
    private RecyclerView recyclerViewAvailableTables;
    private RecyclerViewAvailableTablesAdapter adapter;
    private Button btnOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new TableViewModel();

        btnOrderList = this.findViewById(R.id.orderList);
        recyclerViewAvailableTables = this.findViewById(R.id.recycler_available_tables);
        adapter = new RecyclerViewAvailableTablesAdapter(viewModel.getAvailableTables());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewAvailableTables.setLayoutManager(layoutManager);
        recyclerViewAvailableTables.setHasFixedSize(true);
        recyclerViewAvailableTables.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewAvailableTables.setAdapter(adapter);

        recyclerViewAvailableTables.addOnItemTouchListener(
                new RecyclerViewClickListener(this, recyclerViewAvailableTables ,new RecyclerViewClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        TextView tableNumber = (TextView) view.findViewById(R.id.table_number);
                        String table = tableNumber.getText().toString();

                        String id = viewModel.chooseTable(table);
                        Intent intent = new Intent(getBaseContext(), ServiceActivity.class);
                        intent.putExtra("order_id", id);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );

        btnOrderList.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), OrderListActivity.class);
            startActivity(intent);
        });
    }
}