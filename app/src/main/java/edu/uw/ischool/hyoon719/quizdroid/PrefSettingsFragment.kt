package edu.uw.ischool.hyoon719.quizdroid

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.preference.EditTextPreference
import android.text.InputType
import androidx.preference.PreferenceFragmentCompat
import java.util.concurrent.TimeUnit

class PrefSettingsFragment : PreferenceFragmentCompat() {

    private lateinit var alarmManager: AlarmManager

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val urlPreference: EditTextPreference? = findPreference("url_text")
        val defaultUrl = "http://tednewardsandbox.site44.com/questions.json"
        urlPreference?.text = defaultUrl

        val periodPreference: EditTextPreference? = findPreference("new_measure_period")
        val defaultPeriod = 1
        periodPreference?.text = defaultPeriod.toString()
        periodPreference?.setOnBindEditTextListener { editText ->
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        }

        urlPreference?.setOnPreferenceChangeListener { _, newValue ->
            val sharedPreferences = context?.getSharedPreferences("url", Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.putString("url_text", urlPreference.text)
            editor?.commit()
            requireActivity().let { updateDownload(it) }
            true
        }
        periodPreference?.setOnPreferenceChangeListener { _, newValue ->
            val sharedPreferences = context?.getSharedPreferences("period", Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.putInt("new_measure_period", periodPreference.text.toString().toInt())
            editor?.commit()
            requireActivity().let { updateDownload(it) }
            true
        }
    }

    private fun updateDownload(context : Context) {
        if(context != null) {
            alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val period = context.getSharedPreferences("period", Context.MODE_PRIVATE).getInt("new_measure_period", 1)
            val periodMillis = TimeUnit.MINUTES.toMillis(period.toLong())

            val intent = Intent(context, UpdateDownloadReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                periodMillis,
                pendingIntent
            )
        }
    }
}

