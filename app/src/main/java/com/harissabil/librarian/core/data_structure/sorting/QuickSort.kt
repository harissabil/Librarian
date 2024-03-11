package com.harissabil.librarian.core.data_structure.sorting

import android.util.Log
import com.harissabil.librarian.core.data_structure.sorting.enums.SortBy
import com.harissabil.librarian.core.data_structure.sorting.enums.SortOrder
import com.harissabil.librarian.core.util.Constant.DELAY_DURATION
import com.harissabil.librarian.data.model.Book
import kotlinx.coroutines.delay

object QuickSort {

    // Method to perform quicksort on the given array
    suspend fun quickSort(
        arr: MutableList<Book>,
        low: Int,
        high: Int,
        sortBy: SortBy,
        sortOrder: SortOrder,
        callback: (sortedBooks: List<Book>, isSorting: Boolean) -> Unit,
    ) {
        if (low < high) {
            // Partition the array
            val pi = partition(arr, low, high, sortBy, sortOrder, callback)

            // Recursively sort elements before and after partition
            quickSort(arr, low, pi - 1, sortBy, sortOrder, callback)
            quickSort(arr, pi + 1, high, sortBy, sortOrder, callback)

            if (low == 0 && high == arr.size - 1) {
                callback(arr, false)
                Log.d("QuickSort", "Array sorted successfully!")
            }
        }
    }

    // Method to partition the array
    private suspend fun partition(
        arr: MutableList<Book>,
        low: Int,
        high: Int,
        sortBy: SortBy,
        sortOrder: SortOrder,
        callback: (sortedBooks: List<Book>, isSorting: Boolean) -> Unit,
    ): Int {

        val pivot = arr[high]
        var i = low - 1

        for (j in low until high) {
            if (compare(arr[j], pivot, sortBy, sortOrder) <= 0) {
                i++
                swap(arr, i, j)
                callback(arr, true)
                delay(DELAY_DURATION)
            }
        }

        swap(arr, i + 1, high)
        callback(arr, true)
        delay(DELAY_DURATION)
        return i + 1
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

    // Method to swap two elements in the list
    private fun swap(arr: MutableList<Book>, i: Int, j: Int) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }
}