package com.zakariahnaf.sqlitecrud.adapters;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zakariahnaf.sqlitecrud.R;
import com.zakariahnaf.sqlitecrud.model.Employees;

import java.util.List;

public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.BarCodeViewHolder> {

    private final List<Employees> BarCodeDetailsList;
    private boolean isSelectedAll = false;
    private Context mContext;

    public ItemDetailsAdapter(Context mContext, List<Employees> BarCodeDetailsList) {
        this.BarCodeDetailsList = BarCodeDetailsList;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public BarCodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_simple, parent, false);
        return new BarCodeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BarCodeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_total.setText(""+BarCodeDetailsList.get(position).getId());
        holder.txt_item_name.setText(BarCodeDetailsList.get(position).getDepartment());
        holder.txt_Qty.setText(BarCodeDetailsList.get(position).getJoiningdate());
        holder.txt_group_name.setText(BarCodeDetailsList.get(position).getName());

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
        TextView txt_group_name,txt_total, txt_item_name, txt_Qty,txt_subtotal,txt_comission ;
        public BarCodeViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_group_name = itemView.findViewById(R.id.txt_group_name);
            txt_total = itemView.findViewById(R.id.txt_total);
            txt_item_name = itemView.findViewById(R.id.txt_item_name);
            txt_Qty = itemView.findViewById(R.id.txt_Qty);
            txt_subtotal = itemView.findViewById(R.id.txt_subtotal);
            txt_comission = itemView.findViewById(R.id.txt_comission);
        }
    }


}

