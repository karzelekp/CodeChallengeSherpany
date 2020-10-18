package pl.karzelek.codechallengesherpany.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes resId: Int) = showToast(getString(resId))
fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}