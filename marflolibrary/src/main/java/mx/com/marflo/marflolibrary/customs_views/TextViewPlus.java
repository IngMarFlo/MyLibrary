package mx.com.marflo.marflolibrary.customs_views;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @autor Ing Alejandro Martínez Flores
 * @since 09/07/2018
 * @version 1
 */
public class TextViewPlus extends AppCompatTextView implements customView{

    private customPropertiesMannager mannager;

    public TextViewPlus(Context context, AttributeSet attrs) {
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

    /**
     * Método despreciado para la validación del formulario por el tipo de elemento
     * @param ob
     */
    @Deprecated
    public void setObligatorio(boolean ob) {
        mannager.setObligatorio(ob);
    }
}
