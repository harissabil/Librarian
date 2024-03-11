package com.harissabil.librarian.ui.add_book;

import static android.os.Build.VERSION.SDK_INT;
import static com.harissabil.librarian.core.util.FileUtilKt.createTempFile;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.harissabil.librarian.MainViewModel;
import com.harissabil.librarian.MainViewModel.Screen;
import com.harissabil.librarian.R;
import com.harissabil.librarian.data.model.Book;
import com.harissabil.librarian.databinding.FragmentAddBookBinding;

import java.util.Objects;

public class AddBookFragment extends DialogFragment {

    private FragmentAddBookBinding binding;
    private MainViewModel viewModel;

    // Registers a photo picker activity launcher in single-select mode.
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);
                    binding.ivBookCover.ivPreview.setImageURI(uri);
                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        WindowCompat.setDecorFitsSystemWindows(requireActivity().getWindow(), true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddBookBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toolbar.setNavigationOnClickListener(v -> {
            FragmentManager fm = getParentFragmentManager();
            fm.popBackStack();
        });

        if (SDK_INT >= Build.VERSION_CODES.O) {
            binding.toolbar.getChildAt(0).setTooltipText(getString(R.string.close));
        } else {
            TooltipCompat.setTooltipText(binding.toolbar.getChildAt(0), getString(R.string.close));
        }

        // Launch the photo picker and let the user choose only images.
        binding.cvBookCover.setOnClickListener(v -> pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build()));

        binding.toolbar.getMenu().findItem(R.id.action_save).setOnMenuItemClickListener(item -> {
            // Hide the keyboard
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            String imagePath;

            String title = Objects.requireNonNull(binding.tietTitle.getText()).toString();
            String author = Objects.requireNonNull(binding.tietAuthor.getText()).toString();
            String year = Objects.requireNonNull(binding.tietYear.getText()).toString();
            Drawable bookCover = binding.ivBookCover.ivPreview.getDrawable();

            Bitmap bitmap;
            if (bookCover instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) bookCover).getBitmap();
            } else {
                bitmap = null;
            }

            if (title.isEmpty() || author.isEmpty() || year.isEmpty()) {
                if (title.isEmpty()) {
                    binding.tilTitle.setError("Title is required");
                }
                if (author.isEmpty()) {
                    binding.tilAuthor.setError("Author is required");
                }
                if (year.isEmpty()) {
                    binding.tilYear.setError("Year is required");
                }
                return false;
            }

            if (bitmap != null) {
                imagePath = createTempFile(bitmap, requireContext());
            } else {
                imagePath = null;
            }

            viewModel.addBook(new Book(
                    viewModel.getNextBookId(),
                    title,
                    author,
                    Integer.parseInt(year),
                    imagePath,
                    false
            ), Screen.ADD_BOOK);

            // Close the fragment
            FragmentManager fm = getParentFragmentManager();
            fm.popBackStack();

            return true;
        });
    }
}