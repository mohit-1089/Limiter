package com.example.limiter.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.limiter.R;

import java.util.ArrayList;

public class FAQRecyclerViewAdapter extends RecyclerView.Adapter<FAQRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> ques;
    private ArrayList<String>ans;
    private Context context;

    public FAQRecyclerViewAdapter( Context context,ArrayList<String> ques,ArrayList<String> ans) {
        this.ques = ques;
        this.ans=ans;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faq_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String getQues = ques.get(position);
        String getAns=ans.get(position);

        holder.tvQues.setText(getQues);
        holder.tvAns.setText(getAns);

    }

    @Override
    public int getItemCount() {
        return ques.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

         TextView tvQues;
         TextView tvAns;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            tvQues=itemView.findViewById(R.id.tvQues);
            tvAns=itemView.findViewById(R.id.tvAns);

           // tvAns.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Log.d("ClickFromViewHolder", "Clicked");
        }
    }



}
