package shehab.task.com.marvelcharacters.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Provides a method to show a Snackbar.
 */
object SnackbarUtils {

    fun showSnackbar(v: View?, snackbarText: String?) {
        if (v == null || snackbarText == null) {
            return
        }
        Snackbar.make(v, snackbarText, Snackbar.LENGTH_LONG).show()
    }
}
