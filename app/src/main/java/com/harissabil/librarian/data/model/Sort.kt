package com.harissabil.librarian.data.model

import com.harissabil.librarian.core.data_structure.sorting.enums.SortAlgorithm
import com.harissabil.librarian.core.data_structure.sorting.enums.SortBy
import com.harissabil.librarian.core.data_structure.sorting.enums.SortOrder

data class Sort(
    val sortAlgorithm: SortAlgorithm,
    val sortBy: SortBy,
    val sortOrder: SortOrder
)
