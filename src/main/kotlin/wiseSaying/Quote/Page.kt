package com.wiseSaying.Quote

data class Page (
    val quotes: List<Quote>,
    val currentPage: Int,
    val totalPages: Int
)

