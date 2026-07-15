package com.wiseSaying.App

import com.wiseSaying.Controller.QuoteController

class App(private val controller: QuoteController) {
    fun run() {
        while(true) {
            print("명령) ")
            val input = readLine() ?: break

            val (cmd, paramString) = if ("?" in input) {
                val (c , p) = input.split("?", limit = 2)
                c to p
            }
            else {
                input to ""
            }

            // ex) 삭제 , / ? / , id=1
            // ex2) 목록 , / ? / keywordType=content&keyword=과거
            // ex3) 목록 , / ? / keywordType=author&keyword=작자

            when(cmd) {
                "종료" -> break
                "등록" -> controller.register()
                "목록" -> {
                    val params = parsingParams(paramString)
                    val type = params["keywordType"]
                    val word = params["keyword"]
                    val page = params["page"]?.toIntOrNull() ?: 1
                    controller.readList(type, word, page)
                }
                "삭제" -> controller.delete(parsingId(paramString))
                "수정" -> controller.modify(parsingId(paramString))
            }
        }
        return
    }

    private fun parsingId(str: String): Int {
        return str.split("=")[1].toInt()
    }

    private fun parsingParams(str: String): Map<String,String> { //
        if(str.isBlank()) return emptyMap()
        return str.split("&")
            .mapNotNull { pair ->
                val parts = pair.split("=", limit = 2)
                if(parts.size == 2) parts[0] to parts[1] else null
            }
            .toMap()
    }
}