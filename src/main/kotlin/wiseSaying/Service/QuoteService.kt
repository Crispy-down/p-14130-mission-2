package com.wiseSaying.Service

import com.wiseSaying.Quote.Page
import com.wiseSaying.Quote.Quote
import com.wiseSaying.Repository.QuoteRepository

//역할 : 순수 비지니스 로직
//스캐너 사용금지, 출력 금지

class QuoteService(private val repository: QuoteRepository) {
    fun write(content: String, author: String): Quote {
        return repository.save(content,author)
    }

    fun existsById(id: Int): Boolean { // 존재 여부
        return repository.existsById(id)
    }

    fun findById(id: Int): Quote? { // 존재하는 데이터
        return repository.findById(id)
    }

    fun findAllByKeyword(type: String, word: String): List<Quote> { // 검색한 데이터
        return repository.findAllByKeyword(type,word)
    }

    fun findAll(): List<Quote> { // 목록 역순 출력
        return repository.findAllByReverse()
    }

    fun findList(type: String?, word: String?, page: Int): Page {
        val pageSize = 5

        val allMatching = if (type != null && word != null) {
            repository.findAllByKeyword(type,word)
        } else {
            repository.findAllByReverse()
        }

        val totalPages = if(allMatching.isEmpty()) 1 else ((allMatching.size -1) / pageSize) + 1
        // totalPages 계산 = (size -1) / pageSize + 1 => 나머지가 있어도 올림처리 되는 정수 연산
        val safePage = page.coerceIn(1, totalPages)
        // 잘못된 페이지 번호(0, 음수, 범위초과)가 들어와도 안전하게 범위 안으로 보정

        val fromIndex = (safePage - 1) * pageSize // 이 페이지가 시작하는 위치
        // 페이지는 1부터 시작, 리스트 인덱스는 0부터 시작 -> -1
        // 1 페이지 = 인덱스 0부터 시작
        // 2 페이지 = 5부터 시작
        // 3 페이지 = 10부터 시작
        val toIndex = minOf(fromIndex + pageSize, allMatching.size)
        // 이 페이지가 끝나는 위치, 안전하게 보정
        // 리스트 크기를 안넘도록 상한을 걸어줌
        // ex) 데이터 8개, 2페이지 요청시 10, 8 min값 상한 8(리스트 크기 안넘도록 걸어줌)
        val content = if (fromIndex >= allMatching.size) emptyList() else allMatching.subList(fromIndex, toIndex).toList()
        // 실제로 이 페이지에 보여줄 데이터를 잘라내기
        // subList(fromIndex, toIndex) => fromIndex ~ toIndex 직전까지 잘라서 리스트 반환


        return Page(content, safePage, totalPages)
    }

    fun deleteById(id: Int): Quote? {
        return repository.delete(id)
    }

    fun modify(id: Int, content: String , author: String): Quote? {
        return repository.update(id, content, author)
    }


}