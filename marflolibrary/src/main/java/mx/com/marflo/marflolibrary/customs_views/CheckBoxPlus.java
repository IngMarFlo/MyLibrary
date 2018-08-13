package mx.com.marflo.marflolibrary.customs_views;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/**
 * @version 1
 * @author Ing Alejandro Martínez Flores
 * @since 09/07/2018
 */
public class CheckBoxPlus extends AppCompatCheckBox implements customView{

    private customPropertiesMannager mannager;

    public CheckBoxPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        mannager = new customPropertiesMannager(context, attrs);

        if (mannager.getField() != null){
            this.setTag(mannager.getField());
        }
    }

    public void setCallback(final Callback callback) {
        this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                callback.onCheckChange(isChecked);
            }
        });
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
     */
    @Deprecated
    public void setObligatorio(boolean ob) {
        mannager.setObligatorio(ob);
    }

    public interface Callback{
        void onCheckChange(boolean isChecked);
    }
}
