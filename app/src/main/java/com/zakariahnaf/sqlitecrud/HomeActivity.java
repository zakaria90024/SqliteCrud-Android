package com.zakariahnaf.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    CardView totalDueCardViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deshboard);
        totalDueCardViewID = findViewById(R.id.totalDueCardViewID);
        totalDueCardViewID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, VoucherActivity.class);
                startActivity(intent);
            }
        });
    }

    public void WhenClickAddCategory(View view) {
        Intent intent = new Intent(this, AddCategoryActivity.class);
        startActivity(intent);
    }

    public void WhenClickAddProduct(View view) {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }


}