package br.com.leotosin.restaurantapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.models.Table;

public class RecyclerViewAvailableTablesAdapter extends RecyclerView.Adapter<RecyclerViewAvailableTablesAdapter.ViewHolder> {

    private final List<Table> listTables;

    public RecyclerViewAvailableTablesAdapter(List<Table> list) {
        this.listTables = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.table_number);
        }

        public TextView getNumber() {
            return number;
        }
    }

    @NonNull
    @Override
    public RecyclerViewAvailableTablesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_avaiable_tables, parent, false);

        return new ViewHolder(list);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAvailableTablesAdapter.ViewHolder holder, int position) {

        holder.getNumber().setText(this.listTables.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return this.listTables.size();
    }
}
