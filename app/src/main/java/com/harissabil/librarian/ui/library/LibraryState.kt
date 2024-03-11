package com.harissabil.librarian.ui.library

import com.harissabil.librarian.core.data_structure.sorting.enums.SortAlgorithm
import com.harissabil.librarian.core.data_structure.sorting.enums.SortBy
import com.harissabil.librarian.core.data_structure.sorting.enums.SortOrder
import com.harissabil.librarian.data.model.Book

data class LibraryState(
    val sortAlgorithm: SortAlgorithm = SortAlgorithm.INSERTION_SORT,
    val sortOrder: SortOrder = SortOrder.ASCENDING,
    val sortBy: SortBy = SortBy.ID,
    val books: List<Book> = listOf()
)
