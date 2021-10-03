package com.example.projectapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    List<HistoryModel> historyModelList;

    public HistoryAdapter(Context context,List<HistoryModel> historyModelList) {
        this.context = context;
        this.historyModelList = historyModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (historyModelList != null && historyModelList.size() > 0) {
            HistoryModel model = historyModelList.get(position);
            holder.tv_date.setText(model.getDate());
            holder.tv_pass.setText("" + model.getPass());
            holder.tv_error.setText("" + model.getError());
            holder.tv_rate.setText(model.getRate());
        } else {
            return;
        }
    }
    @Override
    public int getItemCount() {
        return historyModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_date,tv_pass,tv_error,tv_rate;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            tv_date = itemView.findViewById(R.id.tv_date);
            tv_pass = itemView.findViewById(R.id.tv_pass);
            tv_error = itemView.findViewById(R.id.tv_error);
            tv_rate = itemView.findViewById(R.id.tv_rate);
        }
    }
}
