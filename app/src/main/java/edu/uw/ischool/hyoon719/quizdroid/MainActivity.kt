package edu.uw.ischool.hyoon719.quizdroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var quizTopics : RecyclerView
    val topics = listOf("Math", "Physics", "Marvel Super Heroes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewRoot = layoutInflater.inflate(R.layout.activity_main, null)
        setContentView(viewRoot)

        quizTopics = findViewById(R.id.quizTopics)
        quizTopics.layoutManager = LinearLayoutManager(this)
        quizTopics.adapter = TopicAdapter(topics, this)
    }
}
