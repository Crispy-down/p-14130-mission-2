package com.wiseSaying.Controller

import com.wiseSaying.Service.QuoteService

//역할 : 고객의 명령을 입력받고 적절을 응답을 표현
//이 단계에서는 스캐너 사용가능
//이 단계에서는 출력 사용가능

class QuoteController(private val service: QuoteService) {
    fun register() { // 등록
        print("명언 : ")
        var quote = readLine() ?: return
        print("작가 : ")
        var author = readLine() ?: return

        var target = service.write(quote, author)

        println("${target.id}번 명언이 등록되었습니다.")

    }

    fun readList() { // 목록
        println("번호 / 작가 / 명언")
        println("------------------")

        service.findAll().forEach { quote ->
            println("${quote.id} / ${quote.author} / ${quote.content}")
        }
    }

    fun delete(id: Int){ // 삭제
        if(service.existsById(id)) { // 정상적 삭제
            service.deleteById(id)
            println("${id}번 명언이 삭제되었습니다.")
        }
        else { // 삭제가 안된 경우
            println("${id}번 명언이 존재하지 않습니다.")
        }
    }

    fun modify(id: Int){
        if(service.existsById(id)) {
            val target = service.findById(id)

            println("명언(기존) : ${target.content}")
            print("명언 : ")
            val modifyContent = readLine() ?: return
            println("작가(기존) : ${target.author}")
            print("작가 : ")
            val modifyAuthor = readLine() ?: return

            service.modify(id, modifyContent, modifyAuthor)
        }
        else {
            println("${id}번 명언이 존재하지 않습니다.")
        }

    }
}