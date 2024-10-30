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
        val choice = extras?.getString("CHOICE")
        val topicName = extras?.getString("TOPIC_NAME")
        var questionNum = extras?.getInt("QUESTION_INDEX")
        var counts = extras?.getInt("COUNTS")
        val yourAns: TextView = findViewById(R.id.yourAnswer)
        val correctAns: TextView = findViewById(R.id.correctAnswer)
        val correctCount: TextView = findViewById(R.id.correctCounts)
        val next: Button = findViewById(R.id.nextButton)

        var answerMap: Map<Int, String> = mapOf(1 to "")
        if(topicName == "Math") {
            answerMap = mathAnswers
        }else if(topicName == "Physics") {
            answerMap = physicsAnswers
        }else if(topicName == "Marvel Super Heroes") {
            answerMap = marvelAnswers
        }

        yourAns.text = "Your Answer: ${choice}"
        correctAns.text = "Correct Answer: ${answerMap[questionNum]}"
        if (choice == answerMap[questionNum]) {
            counts = counts!! + 1
        }
        correctCount.text = "You have ${counts} out of ${questionNum} correct"

        if(questionNum == 5) {
            next.text = "Finish"
        }

        next.setOnClickListener{
            if(questionNum == 5) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                questionNum = questionNum!! + 1
                val intent = Intent(this, Questions::class.java)
                val extras = Bundle().apply {
                    putString("TOPIC_NAME", topicName)
                    putInt("QUESTION_INDEX", questionNum!!)
                    putInt("COUNTS", counts!!)
                }
                intent.putExtras(extras)
                startActivity(intent)
            }

        }
    }
}