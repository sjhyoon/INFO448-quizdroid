package edu.uw.ischool.hyoon719.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.properties.Delegates

class Questions : AppCompatActivity() {

    lateinit var topicRepository : TopicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)

        topicRepository = (applicationContext as QuizApplication).topicRepository

        val extras = intent.extras
        val questionNum = extras?.getInt("QUESTION_INDEX", 1)
        var topicName = extras?.getString("TOPIC_NAME")
        if (topicName === null) {
            topicName = intent.getStringExtra("TOPIC_NAME")
        }
        var counts = extras?.getInt("COUNTS")
        if (counts === null) {
            counts = 0
        }
        val questionView: TextView = findViewById(R.id.questionText)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val choiceOne: RadioButton = findViewById(R.id.choiceOne)
        val choiceTwo: RadioButton = findViewById(R.id.choiceTwo)
        val choiceThree: RadioButton = findViewById(R.id.choiceThree)
        val choiceFour: RadioButton = findViewById(R.id.choiceFour)
        val submit: Button = findViewById(R.id.submitButton)
        submit.setEnabled(false)

        val question: Question = topicRepository.getTopic(topicName.toString()).questions[questionNum?.minus(1)!!]

        questionView.text = question.text
        choiceOne.text = question.answers[0]
        choiceTwo.text = question.answers[1]
        choiceThree.text = question.answers[2]
        choiceFour.text = question.answers[3]

       var choice : String = ""
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            choice = group.findViewById<RadioButton>(checkedId).text.toString()
            submit.setEnabled(true)
        }

        submit.setOnClickListener {
            val intent = Intent(this, Answer::class.java)
            val extras = Bundle().apply {
                putString("CHOICE", choice)
                putString("TOPIC_NAME", topicName)
                putInt("QUESTION_INDEX", questionNum!!)
                putInt("COUNTS", counts)
            }
            intent.putExtras(extras)
            startActivity(intent)
        }
    }
}