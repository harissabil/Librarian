package com.harissabil.librarian.core.data_structure.sorting

import android.util.Log
import com.harissabil.librarian.core.data_structure.sorting.enums.SortBy
import com.harissabil.librarian.core.data_structure.sorting.enums.SortOrder
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
        delayDuration: Long,
        callback: (sortedBooks: List<Book>, isSorting: Boolean) -> Unit,
    ) {
        if (l < r) {
            val m = l + (r - l) / 2

            // Sort first and second halves
            mergeSort(arr, l, m, sortBy, sortOrder, delayDuration, callback)
            mergeSort(arr, m + 1, r, sortBy, sortOrder, delayDuration, callback)

            // Merge the sorted halves
            merge(arr, l, m, r, sortBy, sortOrder, delayDuration, callback)

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
        delayDuration: Long,
        callback: (sortedBooks: List<Book>, isSorting: Boolean) -> Unit,
    ) {
        var callCallback1 = false
        var callCallback2 = false

        // Find sizes of two subarrays to be merged
        val n1 = m - l + 1
        val n2 = r - m

        // Create temp arrays and copy data into them
        val L = arr.subList(l, l + n1).toMutableList()
        val R = arr.subList(m + 1, m + 1 + n2).toMutableList()

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        var i = 0
        var j = 0

        // Initial index of merged subarray array
        var k = l
        while (i < n1 && j < n2) {
            if (compare(L[i], R[j], sortBy, sortOrder) <= 0) {
                arr[k++] = L[i++]
            } else {
                arr[k++] = R[j++]
            }
//            callback(arr, true)
//            delay(delayDuration)
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k++] = L[i++]
//            callback(arr, true)
//            delay(delayDuration)
            callCallback1 = true
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k++] = R[j++]
//            callback(arr, true)
//            delay(delayDuration)
            callCallback2 = true
        }

        if (callCallback1 || callCallback2) {
            Log.d("MergeSort", "Copying remaining elements of L[] and R[]")
            callback(arr, true)
            delay(delayDuration)
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