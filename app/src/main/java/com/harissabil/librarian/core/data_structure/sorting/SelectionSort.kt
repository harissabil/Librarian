package com.harissabil.librarian.core.data_structure.sorting

import android.util.Log
import com.harissabil.librarian.core.data_structure.sorting.enums.SortBy
import com.harissabil.librarian.core.data_structure.sorting.enums.SortOrder
import com.harissabil.librarian.data.model.Book
import kotlinx.coroutines.delay

object SelectionSort {

    // Method to perform selection sort on the given array
    suspend fun selectionSort(
        arr: MutableList<Book>,
        sortBy: SortBy,
        sortOrder: SortOrder,
        delayDuration: Long,
        callback: (sortedBooks: List<Book>, isSorting: Boolean) -> Unit,
    ) {
        val n = arr.size

        // One by one move boundary of unsorted subarray
        for (i in 0 until n - 1) {
            // Find the minimum element in the unsorted array
            var minIdx = i
            for (j in i + 1 until n) {
                if (compare(arr[j], arr[minIdx], sortBy, sortOrder) < 0) {
                    minIdx = j
                }
            }

            // Swap the found minimum element with the first element
            val temp = arr[minIdx]
            arr[minIdx] = arr[i]
            arr[i] = temp

            callback(arr, true)
            delay(delayDuration)
        }

        callback(arr, false)
        Log.d("SelectionSort", "Array sorted successfully!")
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