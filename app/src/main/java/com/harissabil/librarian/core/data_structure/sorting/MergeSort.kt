package com.harissabil.librarian.core.data_structure.sorting

import android.util.Log
import com.harissabil.librarian.core.data_structure.sorting.enums.SortBy
import com.harissabil.librarian.core.data_structure.sorting.enums.SortOrder
import com.harissabil.librarian.core.util.Constant.DELAY_DURATION
import com.harissabil.librarian.data.model.Book
import kotlinx.coroutines.delay

object MergeSort {

    // Method to recursively sort the array using merge sort
    suspend fun mergeSort(
        arr: MutableList<Book>,
        l: Int,
        r: Int,
        sortBy: SortBy,
        sortOrder: SortOrder,
        callback: (sortedBooks: List<Book>, isSorting: Boolean) -> Unit,
    ) {
        if (l < r) {
            val m = l + (r - l) / 2

            // Sort first and second halves
            mergeSort(arr, l, m, sortBy, sortOrder, callback)
            mergeSort(arr, m + 1, r, sortBy, sortOrder, callback)

            // Merge the sorted halves
            merge(arr, l, m, r, sortBy, sortOrder, callback)

            if (l == 0 && r == arr.size - 1) {
                callback(arr, false)
                Log.d("MergeSort", "Array sorted successfully!")
            }
        }
    }

    // Method to merge two sorted subarrays into one sorted array
    private suspend fun merge(
        arr: MutableList<Book>,
        l: Int,
        m: Int,
        r: Int,
        sortBy: SortBy,
        sortOrder: SortOrder,
        callback: (sortedBooks: List<Book>, isSorting: Boolean) -> Unit,
    ) {
        val n1 = m - l + 1
        val n2 = r - m

        // Create temporary arrays
        val L = MutableList(n1) { arr[l + it] }
        val R = MutableList(n2) { arr[m + 1 + it] }

        // Merge the temporary arrays back into arr[l..r]
        var i = 0
        var j = 0
        var k = l
        while (i < n1 && j < n2) {
            val comparisonResult = compare(L[i], R[j], sortBy, sortOrder)
            if (comparisonResult <= 0) {
                arr[k] = L[i]
                i++
            } else {
                arr[k] = R[j]
                j++
            }
            k++
            callback(arr, true)
            delay(DELAY_DURATION)
        }

        // Copy the remaining elements of L[], if any
        while (i < n1) {
            arr[k] = L[i]
            i++
            k++
            callback(arr, true)
            delay(DELAY_DURATION)
        }

        // Copy the remaining elements of R[], if any
        while (j < n2) {
            arr[k] = R[j]
            j++
            k++
            callback(arr, true)
            delay(DELAY_DURATION)
        }
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