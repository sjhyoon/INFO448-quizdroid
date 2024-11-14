package edu.uw.ischool.hyoon719.quizdroid

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var quizTopics : RecyclerView
    lateinit var topicRepository : TopicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewRoot = layoutInflater.inflate(R.layout.activity_main, null)
        setContentView(viewRoot)

        topicRepository = (applicationContext as QuizApplication).topicRepository

        val config : Button = findViewById(R.id.configButton)
        config.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings_container, PrefSettingsFragment())
                .commit()
        }

        if (intent?.getBooleanExtra("showRetryDialog", false) == true) {
            RetryDialogFragment().show(supportFragmentManager, RetryDialogFragment.TAG)
        }

        quizTopics = findViewById(R.id.quizTopics)
        quizTopics.layoutManager = LinearLayoutManager(this)
        quizTopics.adapter = TopicAdapter(topicRepository.getTopics(), this)
    }
}
