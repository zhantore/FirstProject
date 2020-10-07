package kz.firstproject

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class PrefFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_edit_screen, rootKey)
    }
}