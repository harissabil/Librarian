package com.harissabil.librarian.core.data_structure.sorting

import android.util.Log
import com.harissabil.librarian.core.data_structure.sorting.enums.SortBy
import com.harissabil.librarian.core.data_structure.sorting.enums.SortOrder
import com.harissabil.librarian.core.util.Constant.DELAY_DURATION
import com.harissabil.librarian.data.model.Book
import kotlinx.coroutines.delay

object InsertionSort {

    // Method to perform insertion sort on the given array
    suspend fun insertionSort(
        arr: MutableList<Book>,
        sortBy: SortBy,
        sortOrder: SortOrder,
        callback: (sortedBooks: List<Book>, isSorting: Boolean) -> Unit,
    ) {
        for (i in 1 until arr.size) {
            val key = arr[i]
            var j = i - 1

            /* Move elements of arr[0..i-1], that are
            greater than key, to one position ahead
            of their current position */
            while (j >= 0 && compare(arr[j], key, sortBy, sortOrder) > 0) {
                arr[j + 1] = arr[j]
                j--
                callback(arr, true)
                delay(DELAY_DURATION)
            }
            arr[j + 1] = key
            callback(arr, true)
            delay(DELAY_DURATION)
        }

        callback(arr, false)
        Log.d("InsertionSort", "Array sorted successfully!")
    }

    // Method to compare two books based on the sorting criteria
    private fun compare(a: Book, b: Book, sortBy: SortBy, sortOrder: SortOrder): Int {
        return when (sortBy) {
            SortBy.ID -> if (sortOrder == SortOrder.ASCENDING) a.id.compareTo(b.id) else b.id.compareTo(
                a.id
            )

            SortBy.TITLE -> if (sortOrder == SortOrder.ASCENDING) a.title.compareTo(b.title) else b.title.compareTo(
                a.title
            )

            SortBy.AUTHOR -> if (sortOrder == SortOrder.ASCENDING) a.author.compareTo(b.author) else b.author.compareTo(
                a.author
            )

            SortBy.YEAR -> if (sortOrder == SortOrder.ASCENDING) a.year.compareTo(b.year) else b.year.compareTo(
                a.year
            )
        }
    }
}