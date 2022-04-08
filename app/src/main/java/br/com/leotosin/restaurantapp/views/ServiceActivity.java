package br.com.leotosin.restaurantapp.views;

import androidx.appcompat.app.AppCompatActivity;
import br.com.leotosin.restaurantapp.R;
import br.com.leotosin.restaurantapp.viewModels.ServiceViewModel;

import android.os.Bundle;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    private TextView tableNumber;
    private ServiceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        viewModel = new ServiceViewModel();
        tableNumber = (TextView) findViewById(R.id.tableNumber);

        tableNumber.setText(tableNumber.getText()+" "+viewModel.findTableNumber());
    }
}