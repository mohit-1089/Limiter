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

import com.example.limiter.adapter.FAQRecyclerViewAdapter;
import com.example.limiter.databinding.FragmentFaqBinding;

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

        quesList.add("Hello");
        ansList.add("Inder");
        quesList.add("Hello");
        ansList.add("Inder");
        quesList.add("Hello");
        ansList.add("Inder");

        faqRecyclerViewAdapter = new FAQRecyclerViewAdapter(getContext(),quesList,ansList);
        recyclerView.setAdapter(faqRecyclerViewAdapter);




        return root;
    }
}