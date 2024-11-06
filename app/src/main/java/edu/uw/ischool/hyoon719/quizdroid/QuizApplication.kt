package edu.uw.ischool.hyoon719.quizdroid

import android.app.Application
import android.util.Log

class QuizApplication : Application() {

    lateinit var topicRepository: TopicRepository
    val TAG = QuizApplication::class.java.canonicalName

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Application created.")

        topicRepository = InMemoryTopicRepository()
    }

}

interface TopicRepository {
    fun getTopics(): List<String>
    fun getTopic(title: String): Topic
}
data class Question(
    val questionText: String,
    val answers: List<String>,
    val answerIndex: Int
)
data class Topic(
    val title: String,
    val shortDescription: String,
    val longDescription: String,
    val questions: List<Question>
)
class InMemoryTopicRepository : TopicRepository {
    private val topics = mutableListOf<Topic>()
    private val mathQuestions = mutableListOf<Question>()
    private val physicsQuestions = mutableListOf<Question>()
    private val marvelQuestions = mutableListOf<Question>()

    init {
        mathQuestions.add(Question("What is 1 + 1 equal to?", listOf("2", "3", "4", "11"), 0))
        mathQuestions.add(Question("What is 2 + 123 equal to?", listOf("1223", "2123", "125", "122"), 2))
        mathQuestions.add(Question("What is 23 + 35 equal to?", listOf("48", "52", "56", "58"), 3))
        mathQuestions.add(Question("What is 2 * 5 equal to?", listOf("7", "10", "12", "25"), 1))
        mathQuestions.add(Question("What is 2 + 5 * 5 equal to?", listOf("25", "27", "35", "50"), 1))

        physicsQuestions.add(Question("'F = ma' Which law does this formula stand for?", listOf("Newton's First Law", "Newton's Second Law", "Conservation of Momentum", "Conservation of Energy"), 1))
        physicsQuestions.add(Question("F(Force) is inversely proportional to:", listOf("mass", "acceleration", "distance", "time"), 3))
        physicsQuestions.add(Question("What is the correct unit of acceleration?", listOf("s/m^2", "s/m", "m/s", "m/s^2"), 3))
        physicsQuestions.add(Question("What is the incorrect unit of Force?", listOf("N", "kgm/s^2", "gcm/hr^2", "kgm^2/hr"),3))
        physicsQuestions.add(Question("What one is not classified as vector?", listOf("Displacement", "Acceleration", "Speed", "Force"), 2))

        marvelQuestions.add(Question("Who was the leader of Avengers?", listOf("Captain America", "Ironman", "Thor", "Spiderman"), 0))
        marvelQuestions.add(Question("Which hero snapped and sacrificed to beat Thanos?", listOf("Captain America", "Ironman", "Hulk", "Thor"), 1))
        marvelQuestions.add(Question("Who is the brother of Thor?", listOf("Odin", "Clint(HawkEye)", "Jane", "Loki"), 3))
        marvelQuestions.add(Question("Who is NOT in marvel universe?", listOf("Spiderman", "Thor", "Wanda", "Batman"), 3))
        marvelQuestions.add(Question("Who is the owner of Timestone?", listOf("Wanda", "Hulk", "Clint(HawkEye)", "Dr. Strange"), 3))

        topics.add(Topic("Math", "Basic math", "This will cover basic additions and multiplications", mathQuestions))
        topics.add(Topic("Physics", "Basic physics", "This will cover basic knowledge of engineering physics including acceleration, force, etc.", physicsQuestions))
        topics.add(Topic("Marvel Super Heroes", "About marvel studio", "This will cover Ironman, Spiderman, and all other super heroes in Marvel studio to check if you are a big fan of Marvel!", marvelQuestions))
    }
    override fun getTopics(): List<String> {
        val topicList = mutableListOf<String>()
        for (topic in topics) {
            topicList.add(topic.title)
        }
        return topicList
    }
    override fun getTopic(title: String): Topic {
        var result = Topic("", "", "", mathQuestions)
        for (topic in topics) {
            if (topic.title == title) {
                result = topic
            }
        }
        return result
    }
}

