package mx.com.marflo.marflolibrary.customs_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import mx.com.marflo.marflolibrary.R;

/**
 * Clase para devolver las propiedades
 *
 * @author   Ing Alejandro Martínez Flores
 * @since   09/07/2018
 * @version : 1
 */
public class customPropertiesMannager{

    private Context c;
    private String field, invalidMessage;
    private boolean obligatorio;

    customPropertiesMannager(Context c, String field, String invalidMessage, boolean obligatorio) {
        this.c          = c;
        this.field          = field;
        this.invalidMessage = invalidMessage;
        this.obligatorio    = obligatorio;
    }

    customPropertiesMannager(Context c, AttributeSet attrs){
        this.c = c;
        TypedArray array= c.getTheme().obtainStyledAttributes(attrs, R.styleable.Customs, 0, 0);

        field           = array.getString(R.styleable.Customs_field);
        invalidMessage  = array.getString(R.styleable.Customs_invalid_message);
        obligatorio     = array.getBoolean(R.styleable.Customs_obligatorio, false);
    }

    public String getField(){
        if (obligatorio && field == null) {
            throw new RuntimeException("A mandatory view was detected without the 'field' property");
        }
        return field;
    }

    public String getInvalidMessage(){
        return invalidMessage;
    }

    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    public boolean isObligatorio(){
        return obligatorio;
    }
}
