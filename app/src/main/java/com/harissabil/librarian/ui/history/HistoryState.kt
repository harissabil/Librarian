package com.harissabil.librarian.ui.history

import androidx.core.util.Pair
import com.harissabil.librarian.data.model.Book
import java.util.LinkedList

data class HistoryState(
    val books: LinkedList<Pair<Long, Book>> = LinkedList(),
)