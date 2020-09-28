package com.example.madlevel2task2

import androidx.recyclerview.widget.ItemTouchHelper

class Question (
    var text : String,
    var answer : Int
) {
    companion object{

        private const val correct = ItemTouchHelper.RIGHT
        private const val incorrect = ItemTouchHelper.LEFT

        var QUESTIONS = arrayOf(
            "A val and var are the same thing",
            "Mobile Application Development grants 8 ECTS",
            "In Kotlin when replaces the switch operator in Java"
        )

        var ANSWERS = arrayOf(
            incorrect,
            correct,
            correct
        )
    }
}