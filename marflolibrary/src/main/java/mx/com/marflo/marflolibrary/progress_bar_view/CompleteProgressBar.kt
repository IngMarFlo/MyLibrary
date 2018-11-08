package mx.com.marflo.marflolibrary.progress_bar_view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import mx.com.marflo.marflolibrary.R;
import kotlinx.android.synthetic.main.complete_progress_bar.view.*

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 08/11/2018.
 */
class CompleteProgressBar constructor(val parent: FrameLayout){
    var progres : ProgressBar? = null
    var tv      : TextView? = null
    var lyt     : LinearLayout? = null

    fun init(){

        if(parent.lytCompleteProgress == null){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.complete_progress_bar, parent, false)
            parent.addView(view)
        }

        progres = parent.pgbCompleteProgress
        tv      = parent.tvCompleteProgress
        lyt     = parent.lytCompleteProgress
    }

    fun setMessage(text : String){
        tv!!.text = text
    }

    fun show(window: Window){
        lyt!!.visibility = View.VISIBLE
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun hide(window: Window){
        lyt!!.visibility = View.GONE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun isShowing() : Boolean{
        return lyt!!.visibility == View.VISIBLE
    }

}
