package com.zakariahnaf.sqlitecrud.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zakariahnaf.sqlitecrud.R;

public class HomeActivity extends AppCompatActivity {

    CardView totalDueCardViewID, sectionDueCardViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deshboard);
        totalDueCardViewID = findViewById(R.id.totalDueCardViewID);
        sectionDueCardViewID = findViewById(R.id.sectionDueCardViewID);
        totalDueCardViewID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddCategoryActivity.class);
                startActivity(intent);
            }
        });

        sectionDueCardViewID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });


    }

    public void WhenClickAddCategory(View view) {
        Intent intent = new Intent(this, VoucherActivity.class);
        startActivity(intent);
    }

    public void WhenClickAddProduct(View view) {
        Intent intent = new Intent(this, ApprovalActivity.class);
        startActivity(intent);
    }


}