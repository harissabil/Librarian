package com.harissabil.librarian.core.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.harissabil.librarian.R;
import com.harissabil.librarian.core.data_structure.sorting.enums.SortAlgorithm;
import com.harissabil.librarian.core.data_structure.sorting.enums.SortBy;
import com.harissabil.librarian.core.data_structure.sorting.enums.SortOrder;
import com.harissabil.librarian.data.model.Sort;
import com.harissabil.librarian.databinding.ItemSortBinding;

public class SortAdapter extends ListAdapter<Sort, SortAdapter.ViewHolder> {

    private final OnSortClickListener onSortClickListener;

    public SortAdapter(SortAdapter.OnSortClickListener listener) {
        super(DIFF_CALLBACK);
        onSortClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SortAdapter.ViewHolder(ItemSortBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), onSortClickListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemSortBinding binding;

        public ViewHolder(@NonNull ItemSortBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(
                Sort sort,
                SortAdapter.OnSortClickListener onSortClickListener
        ) {
            bindSortAlgorithm(sort.getSortAlgorithm(), onSortClickListener);
            bindSortCategory(sort.getSortBy(), onSortClickListener);
            bindSortOrder(sort.getSortOrder(), onSortClickListener);
        }

        private void bindSortAlgorithm(SortAlgorithm algorithm, SortAdapter.OnSortClickListener onSortClickListener) {
            int algorithmStringId = getSortAlgorithmStringId(algorithm);
            binding.chipSortAlgorithm.setText(algorithmStringId);
            binding.chipSortAlgorithm.setOnClickListener(v -> onSortClickListener.onSortAlgorithmClick(binding.chipSortAlgorithm));
        }

        private void bindSortCategory(SortBy sortBy, SortAdapter.OnSortClickListener onSortClickListener) {
            int categoryStringId = getSortCategoryStringId(sortBy);
            binding.chipSortCategory.setText(categoryStringId);
            binding.chipSortCategory.setOnClickListener(v -> onSortClickListener.onSortByClick(binding.chipSortCategory));
        }

        private void bindSortOrder(SortOrder order, SortAdapter.OnSortClickListener onSortClickListener) {
            int orderStringId = getOrderStringId(order);
            binding.chipSortOrder.setText(orderStringId);
            binding.chipSortOrder.setOnClickListener(v -> onSortClickListener.onSortOrderClick(binding.chipSortOrder));
        }

        private int getSortAlgorithmStringId(SortAlgorithm algorithm) {
            return switch (algorithm) {
                case SELECTION_SORT -> R.string.selection_sort;
                case BUBBLE_SORT -> R.string.bubble_sort;
                case INSERTION_SORT -> R.string.insertion_sort;
                case MERGE_SORT -> R.string.merge_sort;
                case QUICK_SORT -> R.string.quick_sort;
            };
        }

        private int getSortCategoryStringId(SortBy sortBy) {
            return switch (sortBy) {
                case ID -> R.string.book_id;
                case TITLE -> R.string.title;
                case AUTHOR -> R.string.author;
                case YEAR -> R.string.year;
            };
        }

        private int getOrderStringId(SortOrder order) {
            return switch (order) {
                case ASCENDING -> R.string.ascending;
                case DESCENDING -> R.string.descending;
            };
        }

    }

    public static final DiffUtil.ItemCallback<Sort> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull Sort oldSort, @NonNull Sort newSort) {
                    return oldSort.getSortAlgorithm() == newSort.getSortAlgorithm();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull Sort oldSort, @NonNull Sort newSort) {
                    return oldSort.equals(newSort);
                }
            };

    public interface OnSortClickListener {
        void onSortAlgorithmClick(View view);

        void onSortByClick(View view);

        void onSortOrderClick(View view);
    }
}
