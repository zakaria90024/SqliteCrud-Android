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
import com.zakariahnaf.sqlitecrud.entities.SalesOrder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String dbName = "Deeplaid_DB";
    private static int dbVersion = 1;

    private static String productTable = "INV_STOCKITEM";
    private static String categoryTable = "INV_STOCKITEM_GROUP";
    private static String salesOrderTable = "INV_SALES_ORDER";
    private static String salesOrderTrnTable = "INV_SALES_ORDER_TRN";

    //COLUMN NAME FOR INV_STOCKITEM_GROUP - 1
    private static String idColumnGROUP = "GROUP_CODE";
    private static String nameColumnGROUP = "GROUP_NAME";


    //COLUMN NAME FOR INV_STOCKITEM - 2
    private static String idColumn = "ITEM_CODE";//PK
    private static String categoryIdColumn = "GROUP_CODE";//FK
    private static String nameColumn = "ITEM_NAME";
    private static String priceColumn = "UNIT";
    private static String descriptionColumn = "DESCRIPTION";

    //COLUMN NAME FOR INV_SALES_ORDER - 3
    private static String voucher_noColumnSalesOrder = "VOUCHER_NO";//PK
    private static String voucher_dateColumnSalesOrder = "VOUCHER_DATE";
    private static String net_qntyColumnSalesOrder = "NET_QNTY";
    private static String net_amntColumnSalesOrder = "NET_AMNT";
    private static String app_statusColumnSalesOrder = "APP_STATUS";


    public DatabaseHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + categoryTable + "(" +
                idColumnGROUP + " integer primary key autoincrement," +
                nameColumnGROUP + " text" +
                ")");


        sqLiteDatabase.execSQL("create table " + productTable + "(" +
                idColumn + " integer primary key autoincrement," +
                nameColumn + " text, " +
                priceColumn + " real, " +
                descriptionColumn + " text, " +
                categoryIdColumn + " integer references " + categoryTable + "(" + idColumnGROUP + ")" +
                ")");


        sqLiteDatabase.execSQL("create table " + salesOrderTable + "(" +
                voucher_noColumnSalesOrder + " integer primary key autoincrement," +
                voucher_dateColumnSalesOrder + " text," +
                net_qntyColumnSalesOrder + " integer, " +
                net_amntColumnSalesOrder + " double, " +
                app_statusColumnSalesOrder + " text " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //READ DATABASE DATA
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

    public List<SalesOrder> findAllSalesOrder() {
        List<SalesOrder> salesOrders = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + salesOrderTable, null);
            if (cursor.moveToFirst()) {
                salesOrders = new ArrayList<SalesOrder>();
                do {
                    SalesOrder salesOrder = new SalesOrder();
                    salesOrder.setId(cursor.getInt(0));
                    salesOrder.setDate(cursor.getString(1));
                    salesOrder.setQty(cursor.getInt(2));
                    salesOrder.setAmnt(cursor.getDouble(3));
                    salesOrder.setStatus(cursor.getInt(4));
                    salesOrders.add(salesOrder);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            salesOrders = null;
        }
        return salesOrders;
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


    //Write DATABASE DATA
    public boolean createCategory(Category category) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nameColumnGROUP, category.getName());
            return sqLiteDatabase.insert(categoryTable, null, contentValues) > 0;

        } catch (Exception e) {
            return false;
        }
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

    public  boolean createSalesOrder(SalesOrder salesOrder){
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            //contentValues.put(voucher_noColumnSalesOrder, salesOrder.getId());
            contentValues.put(voucher_dateColumnSalesOrder, salesOrder.getDate());
            contentValues.put(net_qntyColumnSalesOrder, salesOrder.getQty());
            contentValues.put(net_amntColumnSalesOrder, salesOrder.getAmnt());
            contentValues.put(app_statusColumnSalesOrder, salesOrder.getStatus());

            return sqLiteDatabase.insert(salesOrderTable, null, contentValues) > 0;

        }catch (Exception e){
            return  false;
        }
    }

    //FOR DELETE
    public void delete(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(salesOrderTable, voucher_noColumnSalesOrder + "=" + id, null) ;
    }

    //FOR DELETE
    public void update(int id, ContentValues data) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update(salesOrderTable, data, voucher_noColumnSalesOrder + "=" + id, null) ;
    }

}
