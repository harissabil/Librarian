package com.harissabil.librarian.ui.books

import com.harissabil.librarian.core.data_structure.sorting.enums.SortAlgorithm
import com.harissabil.librarian.core.data_structure.sorting.enums.SortBy
import com.harissabil.librarian.core.data_structure.sorting.enums.SortOrder
import com.harissabil.librarian.data.DataSource
import com.harissabil.librarian.data.model.Book

data class BooksState(
    val sortAlgorithm: SortAlgorithm = SortAlgorithm.SELECTION_SORT,
    val sortOrder: SortOrder = SortOrder.ASCENDING,
    val sortBy: SortBy = SortBy.ID,
    val books: List<Book> = DataSource.provideBooks()
)
