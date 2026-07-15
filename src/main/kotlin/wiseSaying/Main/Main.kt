package com.wiseSaying.Main

import com.wiseSaying.App.App
import com.wiseSaying.Controller.QuoteController
import com.wiseSaying.Repository.QuoteRepository
import com.wiseSaying.Service.QuoteService

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val repository = QuoteRepository()
    val service = QuoteService(repository)
    val controller = QuoteController(service)
    val app = App(controller)
    app.run()
}
