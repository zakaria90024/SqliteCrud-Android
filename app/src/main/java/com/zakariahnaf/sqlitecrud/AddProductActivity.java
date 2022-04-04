package com.zakariahnaf.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.zakariahnaf.sqlitecrud.adapters.CategoryAdapter;
import com.zakariahnaf.sqlitecrud.adapters.ProductAdapter;
import com.zakariahnaf.sqlitecrud.database.DatabaseHelper;
import com.zakariahnaf.sqlitecrud.entities.Category;
import com.zakariahnaf.sqlitecrud.entities.Product;

import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    EditText name, price, description;
    Button saveBtn, CancelBtn;
    Spinner CategorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        initView();
        loadData();
    }

    private void initView() {
        name = findViewById(R.id.editTextTextPersonName);
        price = findViewById(R.id.editTextTextPersonName3);
        description = findViewById(R.id.editTextTextPersonName4);
        CategorySpinner = findViewById(R.id.editTextTextPersonName5);

        saveBtn = findViewById(R.id.buttonAddPro);
        CancelBtn = findViewById(R.id.button3);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBtnOnclick();
            }
        });
        
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelButtonONclick();
            }
        });

    }

    private void cancelButtonONclick() {
    }

    private void saveBtnOnclick() {

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        Product product = new Product();
        product.setName(name.getText().toString());
        product.setPrice(Double.parseDouble(price.getText().toString()));
        product.setDescription(description.getText().toString());


        //product.setCategoryId(Integer.parseInt(CategorySpinner.getSelectedItem().toString()));

        //spinner data set
        Category category =(Category) CategorySpinner.getSelectedItem();
        product.setCategoryId(category.getId());

        if(databaseHelper.createProduct(product)){
            Intent intent = new Intent(AddProductActivity.this, HomeActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Fail Product", Toast.LENGTH_SHORT).show();
        }


    }


    public void loadData(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Category> categories = databaseHelper.findAllCategory();
        if(!categories.isEmpty()){
            CategorySpinner.setAdapter(new CategoryAdapter(getApplicationContext(), R.layout.item_category, categories));
        }
    }

    //for all products show
    public void loadDataProduct(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Product> products = databaseHelper.findAllProducts();
        if(!products.isEmpty()){
//            ListView listView = new ListView();
//            listView.setAdapter(new ProductAdapter(getApplicationContext(),R.layout.item_details_simple), products);
        }
    }
}