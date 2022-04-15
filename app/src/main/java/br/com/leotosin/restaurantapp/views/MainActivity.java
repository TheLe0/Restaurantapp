package br.com.leotosin.restaurantapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.viewModels.TableViewModel;

public class MainActivity extends AppCompatActivity {

    private TableViewModel viewModel;
    private RecyclerView recyclerViewAvailableTables;
    private RecyclerViewAvailableTablesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new TableViewModel();

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
                        viewModel.chooseTable(position);
                        Intent intent = new Intent(getBaseContext(), ServiceActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }
}