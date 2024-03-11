package com.harissabil.librarian.core.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.harissabil.librarian.R;
import com.harissabil.librarian.data.model.Book;
import com.harissabil.librarian.databinding.ItemBookBinding;

public class BookListAdapter extends ListAdapter<Book, BookListAdapter.ViewHolder> {

    private final OnBookClickListener onBookClickListener;

    public BookListAdapter(OnBookClickListener listener) {
        super(DIFF_CALLBACK);
        onBookClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), onBookClickListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemBookBinding binding;

        public ViewHolder(@NonNull ItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Book book, OnBookClickListener onBookClickListener) {
            Glide.with(binding.getRoot())
                    .load(book.getImage())
                    .error(R.drawable.ic_android_foreground)
                    .into(binding.ivBookCover);
            binding.tvBookTitle.setText(book.getTitle());
            binding.tvBookAuthor.setText(book.getAuthor());
            binding.tvBookYear.setText(String.valueOf(book.getYear()));

            binding.btnBookAction.setText((book.isBorrowed()) ? R.string._return : R.string.borrow);
            binding.btnBookAction.setOnClickListener(v -> onBookClickListener.onBookClick(book));
        }
    }

    public static final DiffUtil.ItemCallback<Book> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull Book oldBook, @NonNull Book newBook) {
                    return oldBook.getId() == newBook.getId();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull Book oldBook, @NonNull Book newBook) {
                    return oldBook.equals(newBook);
                }
            };

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }
}
