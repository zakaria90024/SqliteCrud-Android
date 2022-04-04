package com.zakariahnaf.sqlitecrud.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zakariahnaf.sqlitecrud.R;
import com.zakariahnaf.sqlitecrud.entities.Category;
import com.zakariahnaf.sqlitecrud.entities.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private int layout;
    private List<Product> categories;

    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.categories = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product category = categories.get(position);
        View view = LayoutInflater.from(context).inflate(layout, null);
        TextView textViewName = view.findViewById(R.id.textView);
        textViewName.setText(category.getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    public View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product category = categories.get(position);
        View view = LayoutInflater.from(context).inflate(layout, null);
        TextView textViewName = view.findViewById(R.id.textView);
        textViewName.setText(category.getName());
        return view;
    }
}
