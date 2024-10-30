package edu.uw.ischool.hyoon719.quizdroid

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class TopicAdapter(val topics: List<String>, val context: Context) : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val select: Button = view.findViewById(R.id.selectButton)
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
        holder.select.text = topics[position]
        holder.select.setOnClickListener{
            val intent = Intent(context, TopicOverview::class.java).apply {
                putExtra("topic_name", topics[position])
            }
            context.startActivity(intent)
        }
    }
}