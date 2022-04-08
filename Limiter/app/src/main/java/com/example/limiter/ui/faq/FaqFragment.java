package com.example.limiter.ui.faq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.limiter.databinding.FragmentFaqBinding;



public class FaqFragment extends Fragment {

    private FragmentFaqBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFaqBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFaq;
        return root;
    }
}