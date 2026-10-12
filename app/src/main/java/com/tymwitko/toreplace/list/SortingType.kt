package com.tymwitko.toreplace.list

import com.tymwitko.toreplace.R

enum class SortingType {
  NEWEST, OLDEST, MOST_URGENT, LEAST_URGENT
}

fun SortingType.toStringResource() = when (this) {
  SortingType.NEWEST -> R.string.sorting_item_newest
  SortingType.OLDEST -> R.string.sorting_item_oldest
  SortingType.MOST_URGENT -> R.string.sorting_item_urgent
  SortingType.LEAST_URGENT -> R.string.sorting_item_not_urgent
}
