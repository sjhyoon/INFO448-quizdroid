package edu.uw.ischool.hyoon719.quizdroid

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.Writer
import java.net.HttpURLConnection
import java.net.URL


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
    val sharedPref = context.getSharedPreferences("url_text", Context.MODE_PRIVATE)
    val url = sharedPref.getString("url", "http://tednewardsandbox.site44.com/questions.json")
    var readTopics : List<Topic> = emptyList()

    init {
        Toast.makeText(context, url, Toast.LENGTH_SHORT).show()
        try {
            val urlObj = URL(url)
            val httpCon = urlObj.openConnection() as HttpURLConnection
            httpCon.requestMethod = "GET"
            httpCon.connect()
            val responseCode = httpCon.responseCode

            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = InputStreamReader(httpCon.inputStream)
                val json = reader.readText()
                readTopics = Gson().fromJson(json, object : TypeToken<List<Topic>>() {}.type)
                Toast.makeText(context, "URL syncs successfully!", Toast.LENGTH_SHORT).show()

                val dir = ContextWrapper(context).getDir("raw", Context.MODE_PRIVATE)
                val jsonFormat = String.format(json, "questions")
                val writer: Writer = BufferedWriter(FileWriter(File(dir, "questions.json")))
                writer.write(jsonFormat)
                writer.close()
            } else {
                val inputStream = context.resources.openRawResource(R.raw.questions)
                val reader = InputStreamReader(inputStream)
                val json = reader.readText()
                readTopics = Gson().fromJson(json, object : TypeToken<List<Topic>>() {}.type)
            }
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



