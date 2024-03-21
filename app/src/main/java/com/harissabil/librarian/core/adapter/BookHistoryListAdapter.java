package com.harissabil.librarian.core.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.harissabil.librarian.R;
import com.harissabil.librarian.data.model.Book;
import com.harissabil.librarian.databinding.ItemBookHistoryBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class BookHistoryListAdapter extends ListAdapter<Pair<Long, Book>, BookHistoryListAdapter.ViewHolder> {

    public BookHistoryListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBookHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), holder.itemView.getContext());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemBookHistoryBinding binding;

        public ViewHolder(@NonNull ItemBookHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Pair<Long, Book> book, Context context) {
            Glide.with(binding.getRoot())
                    .load(book.second.getImage())
                    .error(R.drawable.ic_android_foreground)
                    .into(binding.ivBookCover);

            binding.tvBookBorrowDate.setText(timeDateStringFromTimestamp(context, book.first));
            binding.tvBookTitle.setText(book.second.getTitle());
            binding.tvBookAuthor.setText(book.second.getAuthor());
            binding.tvBookYear.setText(String.valueOf(book.second.getYear()));
        }
    }

    public static final DiffUtil.ItemCallback<Pair<Long, Book>> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull Pair<Long, Book> oldBook, @NonNull Pair<Long, Book> newBook) {
                    return Objects.equals(oldBook.first, newBook.first);
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull Pair<Long, Book> oldBook, @NonNull Pair<Long, Book> newBook) {
                    return oldBook.equals(newBook);
                }
            };

    @SuppressLint("SimpleDateFormat")
    public static String timeDateStringFromTimestamp(Context applicationContext, long timestamp) {
        String timeDate;
        String androidDateTime = android.text.format.DateFormat.getDateFormat(applicationContext).format(new Date(timestamp)) + ", " +
                android.text.format.DateFormat.getTimeFormat(applicationContext).format(new Date(timestamp));
        String javaDateTime = DateFormat.getDateTimeInstance().format(new Date(timestamp));
        String AmPm = "";
        if (!Character.isDigit(androidDateTime.charAt(androidDateTime.length() - 1))) {
            if (androidDateTime.contains(new SimpleDateFormat().getDateFormatSymbols().getAmPmStrings()[Calendar.AM])) {
                AmPm = " " + new SimpleDateFormat().getDateFormatSymbols().getAmPmStrings()[Calendar.AM];
            } else {
                AmPm = " " + new SimpleDateFormat().getDateFormatSymbols().getAmPmStrings()[Calendar.PM];
            }
            androidDateTime = androidDateTime.replace(AmPm, "");
        }
        if (!Character.isDigit(javaDateTime.charAt(javaDateTime.length() - 1))) {
            javaDateTime = javaDateTime.replace(" " + new SimpleDateFormat().getDateFormatSymbols().getAmPmStrings()[Calendar.AM], "");
            javaDateTime = javaDateTime.replace(" " + new SimpleDateFormat().getDateFormatSymbols().getAmPmStrings()[Calendar.PM], "");
        }
        javaDateTime = javaDateTime.substring(javaDateTime.length() - 3);
        timeDate = androidDateTime.concat(javaDateTime);
        return timeDate.concat(AmPm);
    }
}

