package mx.com.marflo.marflolibrary.customs_views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

/**
 * @version 1
 * @autor Ing Alejandro Martínez Flores
 * @since 09/07/2018
 */
public class RadioGroupPlus extends RadioGroup implements customView{

    private customPropertiesMannager mannager;

    public RadioGroupPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        mannager = new customPropertiesMannager(context, attrs);

        if (mannager.getField() != null){
            this.setTag(mannager.getField());
        }

        this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1){
                    unsetError();
                }
            }
        });
    }

    public void setCallback(final Callback callback){
        this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                callback.onOptionClick(checkedId);
            }
        });
    }

    public void setError(){
        for (int i = 0; i < getChildCount(); i++){
            (getRadioButton(i)).setError("Seleccione una opción");
        }
    }

    public boolean isSelected(){
        return getCheckedRadioButtonId() != -1;
    }

    public String getValueOfCheckedRadioButton(){
        if (isSelected()){
            int checkId = getCheckedRadioButtonId();
            RadioButtonPlus rdb = (RadioButtonPlus) findViewById(checkId);
            return rdb.getField();
        }else {
            return "undefined";
        }
    }

    private void unsetError(){
        for (int i = 0; i < getChildCount(); i++){
            (getRadioButton(i)).setError(null);
        }
    }

    private RadioButtonPlus getRadioButton(int pos){
        return (RadioButtonPlus) getChildAt(pos);
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

    public interface Callback{
        void onOptionClick(int idOptionClick);
    }
}
