package edu.uw.ischool.hyoon719.quizdroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class RetryDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_retry_dialog, container, false)

        viewRoot.findViewById<Button>(R.id.retryButton).setOnClickListener {
            dismiss()
        }

        viewRoot.findViewById<Button>(R.id.quitButton).setOnClickListener{
            System.exit(0)
        }

        return viewRoot
    }

    companion object {
        const val TAG = "RetryDialog"
    }
}