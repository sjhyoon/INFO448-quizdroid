package edu.uw.ischool.hyoon719.quizdroid

import android.app.Application
import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class QuizApplication : Application() {

    lateinit var topicRepository: TopicRepository
    val TAG = QuizApplication::class.java.canonicalName

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Application created.")

        topicRepository = InMemoryTopicRepository(this)
    }

}

interface TopicRepository {
    fun getTopics(): List<Topic>
    fun getTopic(title: String): Topic
}
data class Question(
    val text: String,
    val answers: List<String>,
    val answer: Int
)
data class Topic(
    val title: String,
    val desc: String,
    val longDesc: String,
    val questions: List<Question>
)
class InMemoryTopicRepository(context: Context) : TopicRepository {

    var readTopics : List<Topic> = emptyList()

    init {
        try {
            val inputStream = context.resources.openRawResource(R.raw.questions)
            val reader = InputStreamReader(inputStream)
            val json = reader.readText()
            readTopics = Gson().fromJson(json, object : TypeToken<List<Topic>>() {}.type)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    override fun getTopics(): List<Topic> {
        return readTopics
    }
    override fun getTopic(title: String): Topic {
        return readTopics.find{ it.title == title } ?: Topic("", "", "", emptyList())
    }
}



