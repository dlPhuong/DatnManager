package com.example.tpbook.view.MainPage.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpbook.databinding.ItemTopicBinding;
import com.example.tpbook.model.data.Topic;
import com.example.tpbook.view.login.onEventBillAdapter;

import java.util.ArrayList;
import java.util.List;

public class adapterTopic extends RecyclerView.Adapter<adapterTopic.ViewHolder> {
    public List<Topic> listTopic;
    Context context;
    onEventTopicAdapter monEvent;

    public adapterTopic(List<Topic> listTopic, Context context, onEventTopicAdapter monEvent) {
        this.listTopic = listTopic;
        this.context = context;
        this.monEvent = monEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemTopicBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic topic = listTopic.get(position);
        holder.binding.txtTopicName.setText(topic.getTopicName());
        holder.binding.txtDescription.setText(topic.getDescription());
        holder.binding.txtNameStudent.setText(topic.getNameStudent());
        holder.binding.txtStatus.setText(topic.getStatus());
        holder.binding.itemTopic.setOnClickListener(new View.OnClickListener() {
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
        private ItemTopicBinding binding;

        public ViewHolder(ItemTopicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}