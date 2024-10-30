package edu.uw.ischool.hyoon719.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView


class TopicOverview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_topic_overview)

        val topicName = intent.getStringExtra("TOPIC_NAME")
        val topicView: TextView = findViewById(R.id.topicName)
        val descriptionView: TextView = findViewById(R.id.topicDescription)
        val beginBtn: Button = findViewById(R.id.beginButton)

        topicView.text = topicName
        if (topicName != null) {
            if(topicName == "Math") {
                descriptionView.text = "This will cover basic additions and multiplications"
            } else if(topicName == "Physics") {
                descriptionView.text = "This will cover basic knowledge of engineering physics including acceleration, force, etc."
            } else if(topicName == "Marvel Super Heroes")
                descriptionView.text = "This will cover Ironman, Spiderman, and all other super heroes in Marvel studio to check if you are a big fan of Marvel!"
        }

        beginBtn.setOnClickListener{
            val intent = Intent(this, Questions::class.java).apply {
                putExtra("TOPIC_NAME", topicName)}
            startActivity(intent)
        }
    }
}