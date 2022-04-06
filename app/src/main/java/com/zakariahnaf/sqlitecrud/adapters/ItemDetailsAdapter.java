package com.zakariahnaf.sqlitecrud.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zakariahnaf.sqlitecrud.R;
import com.zakariahnaf.sqlitecrud.database.DatabaseHelper;
import com.zakariahnaf.sqlitecrud.entities.SalesOrder;
import com.zakariahnaf.sqlitecrud.model.Employees;

import java.util.List;

public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.BarCodeViewHolder> {

    private final List<SalesOrder> BarCodeDetailsList;
    private boolean isSelectedAll = false;
    private Context mContext;

    EditText editTextQty;
    Button btnUpdata;

    public ItemDetailsAdapter(Context mContext, List<SalesOrder> BarCodeDetailsList) {
        this.BarCodeDetailsList = BarCodeDetailsList;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public BarCodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_simple, parent, false);

        BarCodeViewHolder barCodeViewHolder = new BarCodeViewHolder(view);
        barCodeViewHolder.setOnClickListener(new BarCodeViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(view.getContext(), "Onclick Called" + position, Toast.LENGTH_SHORT).show();
            }
        });

        barCodeViewHolder.setOnLongClickListener(new BarCodeViewHolder.ClickLongListener() {
            @Override
            public void onItemLongClick(View view,final int position) {

                //Creating AlartDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                //option to display in dialog
                String[] option =  {"Update", "Delete"};

                builder.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){

                            final Dialog myDialog = new Dialog(parent.getContext());
                            myDialog.setContentView(R.layout.dialog_custom);
                            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            myDialog.show();
                            editTextQty = myDialog.findViewById(R.id.dialogTitleId);
                            btnUpdata = myDialog.findViewById(R.id.btn_accept);


                            btnUpdata.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                                    List<SalesOrder> salesOrders = databaseHelper.findAllSalesOrder();


                                    int QtyEdited = Integer.parseInt(editTextQty.getText().toString().trim());
                                    ContentValues data=new ContentValues();
                                    data.put("NET_QNTY",QtyEdited);
                                    databaseHelper.update(salesOrders.get(position).getId(), data);
                                    List<SalesOrder> salesOrdersNew = databaseHelper.findAllSalesOrder();
                                    BarCodeDetailsList.clear();
                                    BarCodeDetailsList.addAll(salesOrdersNew);
                                    //notifyItemChanged(position, salesOrders);
                                    notifyDataSetChanged();
                                    Toast.makeText(view.getContext(), "Updated" + salesOrders.get(position).getId(), Toast.LENGTH_SHORT).show();
                                    myDialog.dismiss();

                                }
                            });

                        }
                        if (which == 1){

                            DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                            List<SalesOrder> salesOrders = databaseHelper.findAllSalesOrder();
                            databaseHelper.delete(salesOrders.get(position).getId());
                            BarCodeDetailsList.remove(position) ;
                            notifyItemRemoved(position);
                            notifyDataSetChanged();
                            Toast.makeText(view.getContext(), "Deleted" + salesOrders.get(position).getId(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).create().show();


            }
        });


        return barCodeViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull BarCodeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_total.setText("" + BarCodeDetailsList.get(position).getId());
        holder.txt_item_name.setText(BarCodeDetailsList.get(position).getDate());
        holder.txt_Qty.setText("Qty:" + BarCodeDetailsList.get(position).getQty());
        holder.txt_group_name.setText("" + BarCodeDetailsList.get(position).getStatus());

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return BarCodeDetailsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class BarCodeViewHolder extends RecyclerView.ViewHolder {
        TextView txt_group_name, txt_total, txt_item_name, txt_Qty, txt_subtotal, txt_comission;

        public BarCodeViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_group_name = itemView.findViewById(R.id.txt_group_name);
            txt_total = itemView.findViewById(R.id.txt_total);
            txt_item_name = itemView.findViewById(R.id.txt_item_name);
            txt_Qty = itemView.findViewById(R.id.txt_Qty);
            txt_subtotal = itemView.findViewById(R.id.txt_subtotal);
            txt_comission = itemView.findViewById(R.id.txt_comission);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v, getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mClickLongListener.onItemLongClick(view, getAdapterPosition());
                    return true;
                }
            });
        }

        private ClickListener mClickListener;
        private ClickLongListener mClickLongListener;

        public interface ClickListener {
            void onItemClick(View view, int position);
        }

        public interface ClickLongListener {
            void onItemLongClick(View view, int position);
        }

        public void setOnClickListener(ClickListener clickListener) {
            mClickListener = clickListener;
        }

        public void setOnLongClickListener(ClickLongListener clickLongListener) {
            mClickLongListener = clickLongListener;
        }
    }
}

