package com.harissabil.librarian.core.data_structure.sorting

import android.util.Log
import com.harissabil.librarian.core.data_structure.sorting.enums.SortBy
import com.harissabil.librarian.core.data_structure.sorting.enums.SortOrder
import com.harissabil.librarian.data.model.Book
import kotlinx.coroutines.delay

object BubbleSort {
    // Method to perform bubble sort on the given array of books
    suspend fun bubbleSort(
        arr: MutableList<Book>,
        sortBy: SortBy,
        sortOrder: SortOrder,
        delayDuration: Long,
        callback: (sortedBooks: List<Book>, isSorting: Boolean) -> Unit,
    ) {
        val n = arr.size
        var swapped: Boolean

        for (i in 0 until n - 1) {
            swapped = false
            for (j in 0 until n - i - 1) {
                if (compare(arr[j], arr[j + 1], sortBy, sortOrder) > 0) {
                    // Swap arr[j] and arr[j+1]
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                    swapped = true
                    callback(arr, true)
                    delay(delayDuration)
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (!swapped)
                break
        }

        callback(arr, false)
        Log.d("BubbleSort", "Array sorted successfully!")
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