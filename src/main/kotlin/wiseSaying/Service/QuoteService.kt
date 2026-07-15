package com.wiseSaying.Service

import com.wiseSaying.Quote.Quote
import com.wiseSaying.Repository.QuoteRepository

//역할 : 순수 비지니스 로직
//스캐너 사용금지, 출력 금지

class QuoteService(private val repository: QuoteRepository) {
    fun write(content: String, author: String): Quote {
        return repository.save(content,author)
    }

    fun existsById(id: Int): Boolean {
        return repository.existsById(id)
    }

    fun findById(id: Int): Quote {
        return repository.findById(id)
    }

    fun findAll(): List<Quote> {
        return repository.findAllByReverse()
    }

    fun deleteById(id: Int): Quote {
        return repository.delete(id)
    }

    fun modify(id: Int, content: String , author: String): Quote {
        return repository.update(id, content, author)
    }
}