package com.zakariahnaf.sqlitecrud.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zakariahnaf.sqlitecrud.entities.Category;
import com.zakariahnaf.sqlitecrud.entities.Product;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String dbName = "productDB";
    private static int dbVersion = 1;

    private static String productTable = "product";
    private static String categoryTable = "category";

    private static String idColumn = "id";
    private static String nameColumn = "name";
    private static String priceColumn = "price";
    private static String descriptionColumn = "description";
    private static String categoryIdColumn = "categoryId";

    public DatabaseHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + categoryTable + "(" +
                idColumn + " integer primary key autoincrement," +
                nameColumn + " text" +
                ")");


        sqLiteDatabase.execSQL("create table " + productTable + "(" +
                idColumn + " integer primary key autoincrement," +
                nameColumn + " text, " +
                priceColumn + " real, " +
                descriptionColumn + " text, " +
                categoryIdColumn + " integer references " + categoryTable + "(" + idColumn + ")" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean createCategory(Category category) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nameColumn, category.getName());
            return sqLiteDatabase.insert(categoryTable, null, contentValues) > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public List<Category> findAllCategory() {
        List<Category> categories = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + categoryTable, null);
            if (cursor.moveToFirst()) {
                categories = new ArrayList<Category>();
                do {
                    Category category = new Category();
                    category.setId(cursor.getInt(0));
                    category.setName(cursor.getString(1));
                    categories.add(category);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            categories = null;
        }
        return categories;
    }

    public List<Product> findAllProducts() {
        List<Product> products = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + productTable, null);
            if (cursor.moveToFirst()) {
                products = new ArrayList<Product>();
                do {
                    Product product = new Product();
                    product.setId(cursor.getInt(0));
                    product.setName(cursor.getString(1));
                    products.add(product);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            products = null;
        }
        return products;
    }


    //Category Wise data get
    //SELECT * FROM 'product' where categoryId = 1 LIMIT 0,30
    public List<Product> findCategoryWise(String categoryId) {
        List<Product> products = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + productTable+ " where "+categoryId, null);
            if (cursor.moveToFirst()) {
                products = new ArrayList<Product>();
                do {
                    Product product = new Product();
                    product.setId(cursor.getInt(0));
                    product.setName(cursor.getString(1));
                    products.add(product);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            products = null;
        }
        return products;
    }


    public  boolean createProduct(Product product){
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nameColumn,product.getName());
            contentValues.put(priceColumn,product.getPrice());
            contentValues.put(descriptionColumn,product.getDescription());
            contentValues.put(categoryIdColumn,product.getCategoryId());
            return sqLiteDatabase.insert(productTable, null, contentValues) > 0;

        }catch (Exception e){
            return  false;
        }
    }



}
