package edu.uw.ischool.hyoon719.quizdroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.net.HttpURLConnection
import java.net.URL

class UpdateDownloadReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val url = context.getSharedPreferences("url", Context.MODE_PRIVATE).getString("url_text", "")
        val urlObj = URL(url)
        val httpCon = urlObj.openConnection() as HttpURLConnection
        httpCon.requestMethod = "GET"
        httpCon.connect()
        val responseCode = httpCon.responseCode

        if (responseCode != HttpURLConnection.HTTP_OK) {
            val dialogIntent = Intent(context, MainActivity::class.java)
            dialogIntent.putExtra("showRetryDialog", true)
            dialogIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(dialogIntent)
        }
    }
}