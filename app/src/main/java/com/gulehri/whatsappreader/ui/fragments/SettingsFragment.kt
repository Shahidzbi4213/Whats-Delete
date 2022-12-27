package com.gulehri.whatsappreader.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.gulehri.whatsappreader.R
import com.gulehri.whatsappreader.data.viewmodels.NotificationViewModel
import com.gulehri.whatsappreader.utils.Extensions.setBarTitle
import com.gulehri.whatsappreader.utils.Extensions.setMode
import com.gulehri.whatsappreader.utils.Extensions.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {


    private val viewModel by viewModels<NotificationViewModel>()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().setBarTitle(getString(R.string.action_settings))
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            "dark" -> {
                val isNightMode = preference.sharedPreferences?.getBoolean("dark", false) ?: false
                isNightMode.setMode()
            }
            "delete" -> {
                viewModel.deleteAll()
                "Database cleared".showMessage(requireView())
            }
        }
        return true
    }
}