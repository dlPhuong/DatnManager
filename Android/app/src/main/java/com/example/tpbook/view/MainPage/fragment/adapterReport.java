package com.example.tpbook.view.MainPage.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpbook.databinding.ItemReportBinding;
import com.example.tpbook.model.data.Report;
import com.example.tpbook.model.data.Topic;

import java.util.List;

public class adapterReport extends RecyclerView.Adapter<adapterReport.ViewHolder> {
    public List<Report> listTopic;
    Context context;
    onEventReportAdapter monEvent;

    public adapterReport(List<Report> listTopic, Context context, onEventReportAdapter monEvent) {
        this.listTopic = listTopic;
        this.context = context;
        this.monEvent = monEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemReportBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Report topic = listTopic.get(position);
        holder.binding.txtNameReport.setText("Tên báo cáo: "+topic.getNameReport());
        holder.binding.txtDeadline.setText("hạn chót: "+topic.getDeadline());
        holder.binding.txtmission.setText("nhiệm vụ: "+topic.getMission());
        holder.binding.txtprocess.setText("Quá trình: "+topic.getProcess());
        holder.binding.txtStatus.setText("Trạng thái: "+topic.getStatus());
        if(position%2==0){
            holder.binding.itemReport.setBackgroundColor(Color.parseColor("#CCCCCC"));
        }
        if(topic.getStatus().equals("DOME")){
            holder.binding.itemReport.setBackgroundColor(Color.parseColor("#33FF00"));
        }
        holder.binding.itemReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monEvent.onClickItem(listTopic.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTopic == null ? 0 :
                listTopic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemReportBinding binding;

        public ViewHolder(ItemReportBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
