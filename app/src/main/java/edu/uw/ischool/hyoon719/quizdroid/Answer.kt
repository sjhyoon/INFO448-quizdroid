package edu.uw.ischool.hyoon719.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Answer : AppCompatActivity() {

    val mathAnswers: Map<Int, String> = mapOf(
        1 to "2",
        2 to "125",
        3 to "58",
        4 to "10",
        5 to "27"
    )

    val physicsAnswers: Map<Int, String> = mapOf(
        1 to "Newton's Second Law",
        2 to "time",
        3 to "m/s^2",
        4 to "kgm^2/hr",
        5 to "Speed"
    )

    val marvelAnswers: Map<Int, String> = mapOf(
        1 to "Captain America",
        2 to "Ironman",
        3 to "Loki",
        4 to "Batman",
        5 to "Dr. Strange"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_answer)
        val extras = intent.extras
        val choice = extras?.getString("choice")
        val topicName = extras?.getString("topic_name")
        val answerIndex = Integer.parseInt(extras?.getString("answer_index"))
        val yourAns: TextView = findViewById(R.id.yourAnswer)
        val correctAns: TextView = findViewById(R.id.correctAnswer)
        val correctCount: TextView = findViewById(R.id.correctCounts)
        val next: Button = findViewById(R.id.nextButton)
        var counts = 0

        var answerMap: Map<Int, String> = mapOf(1 to "")
        if(topicName == "Math") {
            answerMap = mathAnswers
        }else if(topicName == "Physics") {
            answerMap = physicsAnswers
        }else if(topicName == "Marvel Super Heroes") {
            answerMap = marvelAnswers
        }

        yourAns.text = "Your Answer: ${choice}"
        correctAns.text = "Correct Answer: ${answerMap[answerIndex]}"
        if (choice == answerMap[answerIndex]) {
            counts++
        }
        correctCount.text = "You have ${counts} out of ${answerIndex} correct"

        next.setOnClickListener{
            if(answerIndex == 5) {
                next.text = "Finish"
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                val intent = Intent(this, Questions::class.java)
                startActivity(intent)
            }

        }
    }
}