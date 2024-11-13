package edu.uw.ischool.hyoon719.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Answer : AppCompatActivity() {

    lateinit var topicRepository : TopicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_answer)

        topicRepository = (applicationContext as QuizApplication).topicRepository

        val extras = intent.extras
        val choice = extras?.getString("CHOICE")
        val topicName = extras?.getString("TOPIC_NAME")
        var questionNum = extras?.getInt("QUESTION_INDEX")
        var counts = extras?.getInt("COUNTS")
        val yourAns: TextView = findViewById(R.id.yourAnswer)
        val correctAns: TextView = findViewById(R.id.correctAnswer)
        val correctCount: TextView = findViewById(R.id.correctCounts)
        val next: Button = findViewById(R.id.nextButton)

        val question: Question = topicRepository.getTopic(topicName.toString()).questions[questionNum?.minus(1)!!]
        val qLength: Int = topicRepository.getTopic(topicName.toString()).questions.size
        yourAns.text = "Your Answer: ${choice}"
        correctAns.text = "Correct Answer: ${question.answers[question.answer]}"
        if (choice == question.answers[question.answer]) {
            counts = counts!! + 1
        }
        correctCount.text = "You have ${counts} out of ${questionNum} correct"

        if(questionNum == qLength) {
            next.text = "Finish"
        }

        next.setOnClickListener{
            if(questionNum == qLength) {
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