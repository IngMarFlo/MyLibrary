package mx.com.marflo.marflolibrary.customs_views;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Clase para personalizar el EditText
 * @author        Ing Alejandro Martínez Flores
 * @since        09/07/2018
 * @version      1
 */
public class EditTextPlus extends android.support.v7.widget.AppCompatEditText implements customView {

    private customPropertiesMannager mannager;

    public EditTextPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        mannager = new customPropertiesMannager(context, attrs);

        if (mannager.getField() != null){
            this.setTag(mannager.getField());
        }
    }

    @Override
    public String getField() {
        return mannager.getField();
    }

    @Override
    public String getInvalidMessage() {
        return mannager.getInvalidMessage();
    }

    @Override
    public boolean isObligatorio() {
        return mannager.isObligatorio();
    }

    @Override
    public void setObligatorio(boolean ob) {
        mannager.setObligatorio(ob);
    }
}
