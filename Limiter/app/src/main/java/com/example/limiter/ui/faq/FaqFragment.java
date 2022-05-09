package com.example.limiter.ui.faq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limiter.DBServices;
import com.example.limiter.SharedData;
import com.example.limiter.Vehicle;
import com.example.limiter.adapter.FAQRecyclerViewAdapter;
import com.example.limiter.databinding.FragmentFaqBinding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class FaqFragment extends Fragment {

    private FragmentFaqBinding binding;
    private RecyclerView recyclerView;
    private FAQRecyclerViewAdapter faqRecyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFaqBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView= binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        ArrayList<String >quesList= new ArrayList<>();
        ArrayList<String >ansList= new ArrayList<>();

        String query="select ques,ans from limiter.faq";

        ResultSet rs;
        try {
            Connection conn = DBServices.openDB();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);

            while(rs.next())
            {


                String q= "Ques : "+ rs.getString("ques");
                quesList.add(q);
                String a="Ans : "+rs.getString("ans");
                ansList.add(a);


            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        faqRecyclerViewAdapter = new FAQRecyclerViewAdapter(getContext(),quesList,ansList);
        recyclerView.setAdapter(faqRecyclerViewAdapter);




        return root;
    }
}