package com.zakariahnaf.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zakariahnaf.sqlitecrud.database.DatabaseHelper;
import com.zakariahnaf.sqlitecrud.entities.Category;

public class AddCategoryActivity extends AppCompatActivity {
    private EditText editText;
    private Button buttonSave, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        initView();
    }

    private void initView() {
        editText = findViewById(R.id.edittext_categoryname);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.button2);
        
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savebtnOnclick();
                
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelbtnOnclick();
            }
        });
    }

    private void cancelbtnOnclick() {
    }

    private void savebtnOnclick() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        Category category = new Category();
        category.setName(editText.getText().toString());

        if(databaseHelper.createCategory(category)){
            Intent intent = new Intent(AddCategoryActivity.this, HomeActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }

    }
}