package com.harissabil.librarian.ui.books;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.harissabil.librarian.core.adapter.BookListAdapter;
import com.harissabil.librarian.data.DataSource;
import com.harissabil.librarian.data.model.Book;
import com.harissabil.librarian.databinding.FragmentBooksBinding;

import java.util.ArrayList;

public class BooksFragment extends Fragment {

    private FragmentBooksBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBooksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Book> books = DataSource.provideBooks();

        BookListAdapter adapter = new BookListAdapter(book -> {

        });

        binding.rvBooks.setAdapter(adapter);
        binding.rvBooks.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.submitList(books);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}