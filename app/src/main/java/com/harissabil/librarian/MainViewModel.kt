package com.harissabil.librarian

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harissabil.librarian.core.data_structure.sorting.enums.SortAlgorithm
import com.harissabil.librarian.core.data_structure.sorting.enums.SortBy
import com.harissabil.librarian.core.data_structure.sorting.enums.SortOrder
import com.harissabil.librarian.core.preferences.PreferencesRepository
import com.harissabil.librarian.data.model.Book
import com.harissabil.librarian.ui.books.BooksState
import com.harissabil.librarian.ui.library.LibraryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    var nextBookId = 11

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _sortingDelayDuration = MutableLiveData<Long>()
    val sortingDelayDuration: LiveData<Long> = _sortingDelayDuration

    fun setIsLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun getSortingDelayDuration() {
        _sortingDelayDuration.value = preferencesRepository.sortingDelayDuration
    }

    private val _booksState = MutableStateFlow(BooksState())
    val booksState: StateFlow<BooksState> = _booksState.asStateFlow()

    private val _libState = MutableStateFlow(LibraryState())
    val libState: StateFlow<LibraryState> = _libState.asStateFlow()

    fun setBooks(books: List<Book>, screen: Screen) {
        when (screen) {
            Screen.BOOKS -> _booksState.value = _booksState.value.copy(books = books)
            Screen.LIBRARY -> _libState.value = _libState.value.copy(books = books)
            Screen.ADD_BOOK -> {}
            Screen.HISTORY -> {}
        }
    }

    fun addBook(book: Book, screen: Screen) {
        when (screen) {
            Screen.BOOKS -> _booksState.value =
                _booksState.value.copy(books = _booksState.value.books.plus(book.also {
                    it.isBorrowed = false
                }))

            Screen.LIBRARY -> _libState.value =
                _libState.value.copy(books = _libState.value.books.plus(book.also {
                    it.isBorrowed = true
                }))

            Screen.ADD_BOOK -> {
                _booksState.value =
                    _booksState.value.copy(books = _booksState.value.books.plus(book))
                nextBookId++
            }

            Screen.HISTORY -> {
                //TODO: Implement history screen
            }
        }
    }

    fun borrowBook(book: Book) {
        addBook(book, Screen.LIBRARY)
        _booksState.value = _booksState.value.copy(books = _booksState.value.books.minus(book))
    }

    fun returnBook(book: Book) {
        addBook(book, Screen.BOOKS)
        _libState.value = _libState.value.copy(books = _libState.value.books.minus(book))
    }

    fun setSortAlgorithm(sortAlgorithm: SortAlgorithm, screen: Screen) {
        when (screen) {
            Screen.BOOKS -> _booksState.value =
                _booksState.value.copy(sortAlgorithm = sortAlgorithm)

            Screen.LIBRARY -> _libState.value = _libState.value.copy(sortAlgorithm = sortAlgorithm)
            Screen.ADD_BOOK -> {}
            Screen.HISTORY -> {}
        }
    }

    fun setSortOrder(sortOrder: SortOrder, screen: Screen) {
        when (screen) {
            Screen.BOOKS -> _booksState.value = _booksState.value.copy(sortOrder = sortOrder)
            Screen.LIBRARY -> _libState.value = _libState.value.copy(sortOrder = sortOrder)
            Screen.ADD_BOOK -> {}
            Screen.HISTORY -> {}
        }
    }

    fun setSortBy(sortBy: SortBy, screen: Screen) {
        when (screen) {
            Screen.BOOKS -> _booksState.value = _booksState.value.copy(sortBy = sortBy)
            Screen.LIBRARY -> _libState.value = _libState.value.copy(sortBy = sortBy)
            Screen.ADD_BOOK -> {}
            Screen.HISTORY -> {}
        }
    }

    init {
        getSortingDelayDuration()
    }

    enum class Screen {
        BOOKS, LIBRARY, ADD_BOOK, HISTORY
    }
}