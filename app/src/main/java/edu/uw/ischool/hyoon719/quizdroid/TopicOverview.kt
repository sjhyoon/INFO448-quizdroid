package edu.uw.ischool.hyoon719.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView


class TopicOverview : AppCompatActivity() {

    lateinit var topicRepository : TopicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_topic_overview)

        topicRepository = (applicationContext as QuizApplication).topicRepository

        val topicName = intent.getStringExtra("TOPIC_NAME")
        val topicView: TextView = findViewById(R.id.topicName)
        val descriptionView: TextView = findViewById(R.id.longText)
        val beginBtn: Button = findViewById(R.id.beginButton)
        val qNumView: TextView = findViewById(R.id.qNumText)

        topicView.text = topicName
        val topic = topicRepository.getTopic(topicName.toString())
        descriptionView.text = topic.longDesc
        qNumView.text = "Number of Questions: ${topic.questions.size}"
        beginBtn.setOnClickListener{
            val intent = Intent(this, Questions::class.java).apply {
                putExtra("TOPIC_NAME", topicName)}
            startActivity(intent)
        }
    }
}