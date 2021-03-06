package org.xdty.callerinfo.settings.dialog

import android.content.Context
import android.content.SharedPreferences
import android.text.InputType
import android.view.View
import android.widget.EditText
import org.xdty.callerinfo.R

class EditDialog(context: Context, sharedPreferences: SharedPreferences) : SettingsDialog(context, sharedPreferences) {

    private lateinit var editText: EditText

    override fun bindViews() {
        val layout = View.inflate(context, R.layout.dialog_edit, null)
        builder.setView(layout)
        editText = layout.findViewById(R.id.text)
        if (defaultText > 0) {
            editText.setText(sharedPrefs.getString(key, context.getString(defaultText)))
        } else {
            editText.setText(sharedPrefs.getString(key, ""))
        }
        editText.setInputType(InputType.TYPE_CLASS_TEXT)
        if (hint > 0) {
            editText.setHint(hint)
        }
    }

    override fun onConfirm() {
        var value = editText.text.toString()

        if (value.isEmpty() && defaultText != 0) {
            value = context.getString(defaultText)
        }

        val editor = sharedPrefs.edit()
        editor.putString(key, value)
        editor.apply()

        super.onConfirm(value)
    }
}