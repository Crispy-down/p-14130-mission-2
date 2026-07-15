package com.wiseSaying.App

import com.wiseSaying.Controller.QuoteController

class App(private val controller: QuoteController) {
    fun run() {
        while(true) {
            print("명령) ")
            val input = readLine() ?: break

            val (cmd, paramString) = if ("?" in input) { // ex) 삭제 , / ? / , id=1
                val (c , p) = input.split("?", limit = 2)
                c to p
            }
            else {
                input to ""
            }

            when(cmd) {
                "종료" -> break
                "등록" -> controller.register()
                "목록" -> controller.readList()
                "삭제" -> controller.delete(parsingId(paramString))
                "수정" -> controller.modify(parsingId(paramString))
            }
        }
        return
    }

    private fun parsingId(str: String): Int {
        return str.split("=")[1].toInt()
    }
}