package mx.com.marflo.marflolibrary.customs_views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * @version : 1
 * @author Ing Alejandro Martínez Flores
 * @since 09/07/2018
 */
public class SwitchPlus extends Switch implements customView{

    private customPropertiesMannager mannager;

    public SwitchPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        mannager = new customPropertiesMannager(context, attrs);

        if (mannager.getField() != null){
            this.setTag(mannager.getField());
        }
    }

    public void setCallback(final Callback callback){
        this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                callback.onCheckedChange(isChecked);
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
        void onCheckedChange(boolean isChecked);
    }
}
