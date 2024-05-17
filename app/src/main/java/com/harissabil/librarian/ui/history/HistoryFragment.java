package com.harissabil.librarian.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.harissabil.librarian.MainViewModel;
import com.harissabil.librarian.core.adapter.BookHistoryListAdapter;
import com.harissabil.librarian.core.data_structure.searching.LinearSearch;
import com.harissabil.librarian.data.model.Book;
import com.harissabil.librarian.databinding.FragmentHistoryBinding;

import java.util.ArrayList;
import java.util.LinkedList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private MainViewModel viewModel;
    private BookHistoryListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();

        viewModel.getHistoryState().observe(getViewLifecycleOwner(), state -> {
            if (!Boolean.TRUE.equals(viewModel.isLoading().getValue())) {
                viewModel.getSearchQuery().observe(getViewLifecycleOwner(), query -> {
                    LinkedList<Pair<Long, Book>> filteredBooks = query.isEmpty() ? state.getBooks()
                            : LinearSearch.linearSearchByTitle(state.getBooks(), query);
                    adapter.submitList(new ArrayList<>(filteredBooks));
                });
            }
            if (state.getBooks().isEmpty()) {
                binding.ivEmptyBooks.setVisibility(View.VISIBLE);
            } else {
                binding.ivEmptyBooks.setVisibility(View.GONE);
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new BookHistoryListAdapter();

        binding.rvBooks.setHasFixedSize(true);
        binding.rvBooks.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvBooks.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
