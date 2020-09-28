package com.example.madlevel2task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.rvQuestions.layoutManager = LinearLayoutManager(
            this@MainActivity,
            RecyclerView.VERTICAL,
            false
        )
        binding.rvQuestions.adapter = questionAdapter
        binding.rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWERS[i]))
        }
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewholder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            //            direction integer is 4 for left and 8 for right
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (questions[position].answer == direction) {
                    questions.removeAt(position)
                    Snackbar.make(
                        binding.rvQuestions[position],
                        "Congratulations, that's the correct answer!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    Snackbar.make(
                        binding.rvQuestions[position],
                        "That's the wrong answer, please try again!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                questionAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}