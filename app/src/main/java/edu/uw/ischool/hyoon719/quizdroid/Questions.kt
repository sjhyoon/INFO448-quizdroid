package edu.uw.ischool.hyoon719.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.properties.Delegates

class Questions : AppCompatActivity() {

    val mathQuestions: Map<Int, List<String>> = mapOf(
        1 to listOf("What is 1 + 1 equal to?", "2", "3", "4", "11"),
        2 to listOf("What is 2 + 123 equal to?", "1223", "2123", "125", "122"),
        3 to listOf("What is 23 + 35 equal to?", "48", "52", "56", "58"),
        4 to listOf("What is 2 * 5 equal to?", "7", "10", "12", "25"),
        5 to listOf("What is 2 + 5 * 5 equal to?", "25", "27", "35", "50")
    )

    val physicsQuestions: Map<Int, List<String>> = mapOf(
        1 to listOf("'F = ma' Which law does this formula stand for?", "Newton's First Law", "Newton's Second Law", "Conservation of Momentum", "Conservation of Energy"),
        2 to listOf("F(Force) is inversely proportional to:", "mass", "acceleration", "distance", "time"),
        3 to listOf("What is the correct unit of acceleration?", "s/m^2", "s/m", "m/s", "m/s^2"),
        4 to listOf("What is the incorrect unit of Force?", "N", "kgm/s^2", "gcm/hr^2", "kgm^2/hr"),
        5 to listOf("What one is not classified as vector?", "Displacement", "Acceleration", "Speed", "Force")
    )

    val marvelQuestions: Map<Int, List<String>> = mapOf(
        1 to listOf("Who was the leader of Avengers?", "Captain America", "Ironman", "Thor", "Spiderman"),
        2 to listOf("Which hero snapped and sacrificed to beat Thanos?", "Captain America", "Ironman", "Hulk", "Thor"),
        3 to listOf("Who is the brother of Thor?", "Odin", "Clint(HawkEye)", "Jane", "Loki"),
        4 to listOf("Who is NOT in marvel universe?", "Spiderman", "Thor", "Wanda", "Batman"),
        5 to listOf("Who is the owner of Timestone?", "Wanda", "Hulk", "Clint(HawkEye)", "Dr. Strange")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)

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
        val choiceOne: RadioButton = findViewById(R.id.choiceOne)
        val choiceTwo: RadioButton = findViewById(R.id.choiceTwo)
        val choiceThree: RadioButton = findViewById(R.id.choiceThree)
        val choiceFour: RadioButton = findViewById(R.id.choiceFour)
        val submit: Button = findViewById(R.id.submitButton)
        submit.setEnabled(false)

        var questions: Map<Int, List<String>> = mapOf(1 to listOf("", ""))
        if (topicName == "Math") {
            questions = mathQuestions
        } else if (topicName == "Physics") {
            questions = physicsQuestions
        } else if (topicName == "Marvel Super Heroes") {
            questions = marvelQuestions
        }

        questionView.text = questions[questionNum]?.get(0)
        choiceOne.text = questions[questionNum]?.get(1)
        choiceTwo.text = questions[questionNum]?.get(2)
        choiceThree.text = questions[questionNum]?.get(3)
        choiceFour.text = questions[questionNum]?.get(4)

        var choice: String = ""
        choiceOne.setOnCheckedChangeListener { buttonView, isChecked ->
            choice = choiceOne.text.toString()
            submit.setEnabled(true)
        }
        choiceTwo.setOnCheckedChangeListener { buttonView, isChecked ->
            choice = choiceTwo.text.toString()
            submit.setEnabled(true)
        }
        choiceThree.setOnCheckedChangeListener { buttonView, isChecked ->
            choice = choiceThree.text.toString()
            submit.setEnabled(true)
        }
        choiceFour.setOnCheckedChangeListener { buttonView, isChecked ->
            choice = choiceFour.text.toString()
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