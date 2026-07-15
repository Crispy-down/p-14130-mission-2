package com.wiseSaying.Repository

//역할 : 데이터의 조회/수정/삭제/생성을 담당
//스캐너 사용금지, 출력 금지

import com.wiseSaying.Quote.Quote

class QuoteRepository {
    private val quotes = mutableListOf<Quote>()
    private var lastId = 0

    fun existsById(id: Int): Boolean {
        return quotes.any {it.id == id}
    }

    fun findById(id: Int): Quote? {
        return quotes.find {it.id == id}
    }

    //C
    fun save(content: String,author: String): Quote { // 명언 저장
        lastId++
        val quote = Quote(lastId, content, author)
        quotes.add(quote)
        return quote
    }

    //R
    fun findAllByReverse(): List<Quote> { // 역순 출력
        return quotes.sortedByDescending {it.id}
    }

    //U
    fun update(id: Int, content: String, author: String): Quote? {
        val target = findById(id) ?: return null
        target.content = content
        target.author = author
        return target
    }

    //D
    fun delete(id: Int): Quote? {
        val target = findById(id) ?: return null
        quotes.remove(target)
        return target
    }

}


