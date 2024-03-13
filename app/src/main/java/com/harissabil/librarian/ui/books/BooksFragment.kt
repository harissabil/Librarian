package com.harissabil.librarian.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.harissabil.librarian.MainActivity
import com.harissabil.librarian.MainViewModel
import com.harissabil.librarian.MainViewModel.Screen
import com.harissabil.librarian.R
import com.harissabil.librarian.core.adapter.BookListAdapter
import com.harissabil.librarian.core.adapter.SortAdapter
import com.harissabil.librarian.core.adapter.SortAdapter.OnSortClickListener
import com.harissabil.librarian.data.model.Sort
import com.harissabil.librarian.databinding.FragmentBooksBinding
import com.harissabil.librarian.ui.util.popupMenu
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BooksFragment : Fragment(), OnSortClickListener {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    private lateinit var bookListAdapter: BookListAdapter
    private lateinit var sortAdapter: SortAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        lifecycleScope.launch {
            viewModel.booksState.collect { state ->
                if (viewModel.isLoading.value == false) {
                    bookListAdapter.submitList(state.books)
                    sortAdapter.submitList(
                        listOf(
                            Sort(
                                state.sortAlgorithm,
                                state.sortBy,
                                state.sortOrder
                            )
                        )
                    )
                }
                if (state.books.isEmpty()) {
                    binding.ivEmptyBooks.visibility = View.VISIBLE
                } else {
                    binding.ivEmptyBooks.visibility = View.GONE
                }
            }
        }
    }

    private fun setupRecyclerView() {
        bookListAdapter = BookListAdapter {
            if (viewModel.isLoading.value == false) {
                viewModel.borrowBook(it)
            }
        }
        sortAdapter = SortAdapter(this@BooksFragment)

        binding.rvBooks.setHasFixedSize(false)
        binding.rvBooks.setLayoutManager(LinearLayoutManager(requireContext()))
        binding.rvBooks.adapter = ConcatAdapter(sortAdapter, bookListAdapter)

        binding.rvBooks.setOnScrollChangeListener { _: View?, _: Int, scrollY: Int, _: Int, oldScrollY: Int ->
            (requireActivity() as? MainActivity)?.onScrollChanged(scrollY, oldScrollY)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSortingDelayDuration()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onSortAlgorithmClick(view: View) {
        popupMenu(
            requireContext(),
            view,
            R.menu.menu_sort_algorithm,
            viewModel,
            viewModel.booksState.value.books,
            lifecycleScope,
            bookListAdapter,
            Screen.BOOKS,
            viewModel.sortingDelayDuration.value!!
        )
    }

    override fun onSortByClick(view: View) {
        popupMenu(
            requireContext(),
            view,
            R.menu.menu_sort_category,
            viewModel,
            viewModel.booksState.value.books,
            lifecycleScope,
            bookListAdapter,
            Screen.BOOKS,
            viewModel.sortingDelayDuration.value!!
        )
    }

    override fun onSortOrderClick(view: View) {
        popupMenu(
            requireContext(),
            view,
            R.menu.menu_sort_order,
            viewModel,
            viewModel.booksState.value.books,
            lifecycleScope,
            bookListAdapter,
            Screen.BOOKS,
            viewModel.sortingDelayDuration.value!!
        )
    }
}