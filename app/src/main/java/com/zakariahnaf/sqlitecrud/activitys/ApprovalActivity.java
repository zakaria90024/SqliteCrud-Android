package com.zakariahnaf.sqlitecrud.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.zakariahnaf.sqlitecrud.R;
import com.zakariahnaf.sqlitecrud.adapters.ApproveAdapter;
import com.zakariahnaf.sqlitecrud.adapters.ItemDetailsAdapter;
import com.zakariahnaf.sqlitecrud.database.DatabaseHelper;
import com.zakariahnaf.sqlitecrud.entities.SalesOrder;

import java.util.List;

public class ApprovalActivity extends AppCompatActivity {

    RecyclerView recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);

        recycler = (RecyclerView) findViewById(R.id.recycler);


        loadDataSaleorder();
    }

    private void loadDataSaleorder() {

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<SalesOrder> salesOrders = databaseHelper.findAllSalesOrder();

        try {
            if (!salesOrders.isEmpty()) {
                recycler.setLayoutManager(new LinearLayoutManager(this));
                ApproveAdapter adapter = new ApproveAdapter(this, salesOrders);
                recycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Empty Sale order", Toast.LENGTH_SHORT).show();
        }
    }
}