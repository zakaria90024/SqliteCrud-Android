package com.zakariahnaf.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.zakariahnaf.sqlitecrud.adapters.ItemDetailsAdapter;
import com.zakariahnaf.sqlitecrud.model.Employees;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "mydatabase";
    SQLiteDatabase mDatabase;
    List<Employees> employeesList;
    RecyclerView recyclerView;

    EditText editTextName, editTextSalary;
    Spinner spinnerDept;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //mapping
        employeesList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        Button button = (Button) findViewById(R.id.buttonAddEmployee);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertValues();
            }
        });

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS employees (\n" +
                "    id INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    name varchar(200) NOT NULL,\n" +
                "    department varchar(200) NOT NULL,\n" +
                "    joiningdate datetime NOT NULL,\n" +
                "    salary double NOT NULL\n" +
                ");";
        mDatabase.execSQL(sql);
    }

    private void insertValues() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String date = simpleDateFormat.format(calendar.getTime());

        String sql = "INSERT INTO employees \n" +
                "(name, department, joiningdate, salary)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?)";

        mDatabase.execSQL(sql, new String[]{"Nametext", "CMT", date, "4454545"});
        Toast.makeText(this, "Inserted Data Successfully", Toast.LENGTH_SHORT).show();

        readValues();
    }


    public void readValues() {
        employeesList.clear();

        String sql = "SELECT * FROM employees";
        Cursor cursor = mDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                employeesList.add(new Employees(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getDouble(4)
                ));
                //cursor.getString()
            } while (cursor.moveToNext());

        }
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        ItemDetailsAdapter adapter = new ItemDetailsAdapter(this, employeesList);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }



    //FOR EXAM TABLE ================================================================================
    //=================================================================================================

    private void createTableGroup() {
        String sql = "CREATE TABLE IF NOT EXISTS INV_STOCKITEM_GROUP (\n" +
                "    GROUP_CODE INTEGER NOT NULL CONSTRAINT INV_STOCKITEM_GROUP_PK PRIMARY KEY AUTOINCREMENT,\n" +
                "    GROUP_NAME varchar(200) NOT NULL\n" +
                ");";
        mDatabase.execSQL(sql);
    }


    private void createTableItem() {
        String sql = "CREATE TABLE IF NOT EXISTS INV_STOCKITEM (\n" +
                "    ITEM_CODE INTEGER NOT NULL CONSTRAINT INV_STOCKITEM_PK PRIMARY KEY AUTOINCREMENT,\n" +
                "    GROUP_NAME varchar(200) NOT NULL\n" +
                ");";
        mDatabase.execSQL(sql);
    }


}