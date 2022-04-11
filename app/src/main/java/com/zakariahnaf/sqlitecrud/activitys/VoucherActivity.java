package com.zakariahnaf.sqlitecrud.activitys;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zakariahnaf.sqlitecrud.R;
import com.zakariahnaf.sqlitecrud.adapters.CategoryAdapter;
import com.zakariahnaf.sqlitecrud.adapters.ItemDetailsAdapter;
import com.zakariahnaf.sqlitecrud.adapters.ProductAdapter;
import com.zakariahnaf.sqlitecrud.database.DatabaseHelper;
import com.zakariahnaf.sqlitecrud.entities.Category;
import com.zakariahnaf.sqlitecrud.entities.Product;
import com.zakariahnaf.sqlitecrud.entities.SalesOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VoucherActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "mydatabase";
    SQLiteDatabase mDatabase;
    List<SalesOrder> salesOrders;
    RecyclerView recyclerView;

    EditText editTextName, editTextSalary;
    Spinner spinnerDept;


    //auto compleate
    //for autoCompleate TextView
    List<String> groupNameList = new ArrayList<String>();
    List<Integer> groupCodeList = new ArrayList<Integer>();
    public static String groupName = null;
    public static int groupCode;

    List<String> pNameList = new ArrayList<String>();
    List<Integer> pCodeList = new ArrayList<Integer>();
    public static String pName = null;
    public static int pCode;
    AutoCompleteTextView txt_autogroup, txt_autoitem;


    Spinner txt_spinner;


    //TextView
    TextView txt_VNand_Date, ammount;
    EditText qty;
    Button addtoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voucher_activity);

        //mapping
        salesOrders = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        txt_autogroup = (AutoCompleteTextView) findViewById(R.id.txt_autogroup);


        txt_spinner = (Spinner) findViewById(R.id.txt_autoitem);


        txt_VNand_Date = (TextView) findViewById(R.id.txt_VNand_Date);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String date = simpleDateFormat.format(calendar.getTime());
        txt_VNand_Date.setText("TC:14, " + date);
        ammount = (TextView) findViewById(R.id.ammount);
        qty = (EditText) findViewById(R.id.qty);
        addtoList = (Button) findViewById(R.id.buttonAddEmployee);
        addtoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whenAddTolistClicked();
            }
        });

        loadData();
        loadDataSaleorder();

    }

    private void whenAddTolistClicked() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String date = simpleDateFormat.format(calendar.getTime());
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setId(1);
        salesOrder.setDate(date);
        try {
            salesOrder.setQty(Integer.parseInt(qty.getText().toString()));
            salesOrder.setAmnt(24434);
            salesOrder.setStatus(1);

        }catch (Exception e){
            Toast.makeText(this, "Qry fineld empty", Toast.LENGTH_SHORT).show();
        }


        if (databaseHelper.createSalesOrder(salesOrder)) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            loadDataSaleorder();

        } else {
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }

    }

    private void loadDataSaleorder() {

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<SalesOrder> salesOrders = databaseHelper.findAllSalesOrder();

        try {
            if (!salesOrders.isEmpty()) {
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                ItemDetailsAdapter adapter = new ItemDetailsAdapter(this, salesOrders);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Empty Sale order", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Category> categories = databaseHelper.findAllCategory();


        try {
            if (!categories.isEmpty()) {

                for (int i = 0; i < categories.size(); i++) {
                    groupName = categories.get(i).getName().trim();
                    groupCode = categories.get(i).getId();
                    groupNameList.add(groupName);
                    groupCodeList.add(groupCode);
                }
                //CategorySpinner.setAdapter(new CategoryAdapter(getApplicationContext(), R.layout.item_category, categories));

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_dropdown_item_1line, groupNameList);
                txt_autogroup.setAdapter(adapter);

                //adapter.getPosition()


                txt_autogroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i1, long l) {
                        for (int i = 0; i < categories.size(); i++) {
                            if (txt_autogroup.getText().toString().trim().equals(groupNameList.get(i))) {
                                //String ledgerId = categories.get(i);
                                //txt_selected_ledger.setText(txt_autocompleate.getText().toString());
                                Toast.makeText(VoucherActivity.this, "id-" + groupCodeList.get(i), Toast.LENGTH_SHORT).show();

                                callWithId(groupCodeList.get(i));
                            }
                        }
                    }
                });
            }

        } catch (Exception e) {
            Toast.makeText(this, "Empty Sale Order", Toast.LENGTH_SHORT).show();
        }


    }

    private void callWithId(Integer integer) {

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Product> products = databaseHelper.findAllProducts();
        Toast.makeText(this, "size" + products.size(), Toast.LENGTH_SHORT).show();



        if (!products.isEmpty()) {

            for (int i = 0; i < products.size(); i++) {
                pName = products.get(i).getName().trim();
                pCode = products.get(i).getId();
                pNameList.add(pName);
                pCodeList.add(pCode);
            }

            txt_spinner.setAdapter(new ProductAdapter(getApplicationContext(), R.layout.item_category, products));

            //CategorySpinner.setAdapter(new CategoryAdapter(getApplicationContext(), R.layout.item_category, categories));

//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                    android.R.layout.simple_dropdown_item_1line, pNameList);
//            txt_autoitem.setAdapter(adapter);
//
//            //adapter.getPosition()
//
//
//            txt_autoitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i1, long l) {
//                    for (int i = 0; i < products.size(); i++) {
//                        if (txt_autoitem.getText().toString().trim().equals(pNameList.get(i))) {
//                            //String ledgerId = categories.get(i);
//                            //txt_selected_ledger.setText(txt_autocompleate.getText().toString());
//                            Toast.makeText(VoucherActivity.this, "id-" + pCodeList.get(i), Toast.LENGTH_SHORT).show();
//
//                            callWithId(pCodeList.get(i));
//                        }
//                    }
//                }
//            });
       }

    }

}