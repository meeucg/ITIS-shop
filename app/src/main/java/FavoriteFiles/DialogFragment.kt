package FavoriteFiles

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class CustomDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        return builder.setTitle("Диалоговое окно").setMessage("Для закрытия окна нажмите ОК")
            .create()
    }
}