package com.harissabil.librarian.ui.util

import android.content.Context
import android.view.Gravity
import android.view.View
import com.harissabil.librarian.MainViewModel
import com.harissabil.librarian.R
import com.harissabil.librarian.core.adapter.BookListAdapter
import com.harissabil.librarian.core.data_structure.sorting.InsertionSort
import com.harissabil.librarian.core.data_structure.sorting.MergeSort
import com.harissabil.librarian.core.data_structure.sorting.QuickSort
import com.harissabil.librarian.core.data_structure.sorting.enums.SortAlgorithm
import com.harissabil.librarian.core.data_structure.sorting.enums.SortBy
import com.harissabil.librarian.core.data_structure.sorting.enums.SortOrder
import com.harissabil.librarian.data.model.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun popupMenu(
    context: Context,
    view: View,
    menuRes: Int,
    viewModel: MainViewModel,
    books: List<Book>,
    scope: CoroutineScope,
    bookListAdapter: BookListAdapter,
    screen: MainViewModel.Screen,
) {
    val popupMenu = android.widget.PopupMenu(
        context,
        view,
        Gravity.CENTER,
        com.google.android.material.R.attr.popupMenuStyle,
        R.style.OptionsMenuCustomStyle
    )
    popupMenu.inflate(menuRes)
    popupMenu.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.action_sort_book_id -> {
                if (viewModel.isLoading.value == true) {
                    return@setOnMenuItemClickListener false
                }
                viewModel.setSortBy(SortBy.ID, screen)
                sortBooks(
                    viewModel = viewModel,
                    scope = scope,
                    books = books,
                    bookListAdapter = bookListAdapter,
                    screen = screen
                )
                true
            }

            R.id.action_sort_title -> {
                if (viewModel.isLoading.value == true) {
                    return@setOnMenuItemClickListener false
                }
                viewModel.setSortBy(SortBy.TITLE, screen)
                sortBooks(
                    viewModel = viewModel,
                    scope = scope,
                    books = books,
                    bookListAdapter = bookListAdapter,
                    screen = screen
                )
                true
            }

            R.id.action_sort_author -> {
                if (viewModel.isLoading.value == true) {
                    return@setOnMenuItemClickListener false
                }
                viewModel.setSortBy(SortBy.AUTHOR, screen)
                sortBooks(
                    viewModel = viewModel,
                    scope = scope,
                    books = books,
                    bookListAdapter = bookListAdapter,
                    screen = screen
                )
                true
            }

            R.id.action_sort_year -> {
                if (viewModel.isLoading.value == true) {
                    return@setOnMenuItemClickListener false
                }
                viewModel.setSortBy(SortBy.YEAR, screen)
                sortBooks(
                    viewModel = viewModel,
                    scope = scope,
                    books = books,
                    bookListAdapter = bookListAdapter,
                    screen = screen
                )
                true
            }

            R.id.action_sort_ascending -> {
                if (viewModel.isLoading.value == true) {
                    return@setOnMenuItemClickListener false
                }
                viewModel.setSortOrder(SortOrder.ASCENDING, screen)
                sortBooks(
                    viewModel = viewModel,
                    scope = scope,
                    books = books,
                    bookListAdapter = bookListAdapter,
                    screen = screen
                )
                true
            }

            R.id.action_sort_descending -> {
                if (viewModel.isLoading.value == true) {
                    return@setOnMenuItemClickListener false
                }
                viewModel.setSortOrder(SortOrder.DESCENDING, screen)
                sortBooks(
                    viewModel = viewModel,
                    scope = scope,
                    books = books,
                    bookListAdapter = bookListAdapter,
                    screen = screen
                )
                true
            }

            R.id.action_sort_insertion_sort -> {
                if (viewModel.isLoading.value == true) {
                    return@setOnMenuItemClickListener false
                }
                viewModel.setSortAlgorithm(
                    SortAlgorithm.INSERTION_SORT,
                    screen
                )
                true
            }

            R.id.action_sort_merge_sort -> {
                if (viewModel.isLoading.value == true) {
                    return@setOnMenuItemClickListener false
                }
                viewModel.setSortAlgorithm(SortAlgorithm.MERGE_SORT, screen)
                true
            }

            R.id.action_sort_quick_sort -> {
                if (viewModel.isLoading.value == true) {
                    return@setOnMenuItemClickListener false
                }
                viewModel.setSortAlgorithm(SortAlgorithm.QUICK_SORT, screen)
                true
            }

            else -> {
                false
            }
        }
    }
    popupMenu.show()
}

private fun sortBooks(
    viewModel: MainViewModel,
    scope: CoroutineScope,
    books: List<Book>,
    bookListAdapter: BookListAdapter,
    screen: MainViewModel.Screen,
) {

    val currentSortAlgorithm = if (screen == MainViewModel.Screen.BOOKS) {
        viewModel.booksState.value.sortAlgorithm
    } else {
        viewModel.libState.value.sortAlgorithm
    }
    val currentSortOrder = if (screen == MainViewModel.Screen.BOOKS) {
        viewModel.booksState.value.sortOrder
    } else {
        viewModel.libState.value.sortOrder
    }
    val currentSortBy = if (screen == MainViewModel.Screen.BOOKS) {
        viewModel.booksState.value.sortBy
    } else {
        viewModel.libState.value.sortBy
    }

    scope.launch {
        when (currentSortAlgorithm) {
            SortAlgorithm.INSERTION_SORT -> {
                InsertionSort.insertionSort(
                    books.toMutableList(),
                    currentSortBy,
                    currentSortOrder
                ) { sortedBooks, isSorting ->
                    bookListAdapter.submitList(sortedBooks.toMutableList())
                    viewModel.setIsLoading(isSorting)
                    if (!isSorting) {
                        viewModel.setBooks(sortedBooks, screen)
                    }
                }
            }

            SortAlgorithm.MERGE_SORT -> {
                MergeSort.mergeSort(
                    books.toMutableList(),
                    0,
                    books.size - 1,
                    currentSortBy,
                    currentSortOrder
                ) { sortedBooks, isSorting ->
                    bookListAdapter.submitList(sortedBooks.toMutableList())
                    viewModel.setIsLoading(isSorting)
                    if (!isSorting) {
                        viewModel.setBooks(sortedBooks, screen)
                    }
                }
            }

            SortAlgorithm.QUICK_SORT -> {
                QuickSort.quickSort(
                    books.toMutableList(),
                    0,
                    books.size - 1,
                    currentSortBy,
                    currentSortOrder
                ) { sortedBooks, isSorting ->
                    bookListAdapter.submitList(sortedBooks.toMutableList())
                    viewModel.setIsLoading(isSorting)
                    if (!isSorting) {
                        viewModel.setBooks(sortedBooks, screen)
                    }
                }
            }
        }
    }
}