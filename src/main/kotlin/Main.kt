package com

data class Quote(
    val id : Int,
    var content: String,
    var author: String
)

var lastId = 0
val quotes = mutableListOf<Quote>()
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    println("== 명언 앱 ==")

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
            "등록" -> registerQuote()
            "목록" -> getListQuote()
            "삭제" -> deleteQuote(parsingId(paramString))
            "수정" -> modifyQuote(parsingId(paramString))
        }
    }
}

fun parsingId(str: String): Int {
    return str.split("=")[1].toInt()
}

fun registerQuote() {
    print("명언 : ")
    var quote = readLine() ?: return
    print("작가 : ")
    var author = readLine() ?: return

    lastId++
    quotes.add(Quote(lastId, quote, author))
    println("${lastId}번 명언이 등록되었습니다.")
}

fun getListQuote() {
    println("번호 / 작가 / 명언")
    println("------------------")
    quotes.sortedByDescending { it.id }.forEach { quote ->
        println("${quote.id} / ${quote.author} / ${quote.content}")
    }
}

fun deleteQuote(id: Int) {
    val target = quotes.find { it.id == id }

    if(target != null) {
        quotes.remove(target)
        println("${id}번 명언이 삭제되었습니다.")
    }
    else {
        println("${id}번 명언이 존재하지 않습니다.")
    }
}

fun modifyQuote(id: Int) {
    val target = quotes.find { it.id == id}

    if(target != null) {
        println("명언(기존) : ${target.content}")
        print("명언 : ")
        val modifyContent = readLine() ?: return
        println("작가(기존) : ${target.author}")
        print("작가 : ")
        val modifyAuthor = readLine() ?: return

        target.author = modifyAuthor
        target.content = modifyContent
    }
    else {
        println("${id}번 명언은 존재하지 않습니다.")
    }
}