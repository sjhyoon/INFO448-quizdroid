package edu.uw.ischool.hyoon719.quizdroid

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TopicAdapter(val topics: List<Topic>, val context: Context) : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val select: Button = view.findViewById(R.id.selectButton)
        val briefDiscription: TextView = view.findViewById(R.id.shortText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).
            inflate(R.layout.list_topics, parent, false))
    }
    override fun getItemCount(): Int {
        return topics.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.select.text = topics[position].title
        holder.briefDiscription.text = topics[position].desc
        holder.select.setOnClickListener{
            val intent = Intent(context, TopicOverview::class.java).apply {
                putExtra("TOPIC_NAME", topics[position].title)
            }
            context.startActivity(intent)
        }
    }
}