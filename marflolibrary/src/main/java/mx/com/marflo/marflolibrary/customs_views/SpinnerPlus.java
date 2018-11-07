package mx.com.marflo.marflolibrary.customs_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import mx.com.marflo.marflolibrary.R;
import mx.com.marflo.marflolibrary.spinner_adapter.SpinnerAdapter;
import mx.com.marflo.marflolibrary.spinner_adapter.spinnersModels;

/**
 * @version 1
 * @author   Ing Alejandro Martínez Flores
 * @since   09/07/2018
 */
public class SpinnerPlus extends android.support.v7.widget.AppCompatSpinner implements customView, AdapterView.OnItemSelectedListener{
    private customPropertiesMannager mannager;
    private spinnersModels model;
    private Callback callback;

    public SpinnerPlus(Context context, AttributeSet attrs) {
        super(context, attrs);

        mannager = new customPropertiesMannager(context, attrs);

        if (mannager.getField() != null){
            this.setTag(mannager.getField());
        }

        this.setOnItemSelectedListener(this);
    }

    public SpinnerPlus(Context c, String field, String invalidMesage, boolean obligatorio){
        super(c);

        mannager = new customPropertiesMannager(c, field, invalidMesage, obligatorio);
        if (field != null){
            this.setTag(field);
        }
        this.setOnItemSelectedListener(this);
    }

    public void setCallback(Callback callback){
        this.callback = callback;
    }

    public void setSelectId(int id){
        SpinnerAdapter ad = (SpinnerAdapter) getAdapter();
        if (ad != null) {
            int pos = ad.getPositionById(id);
            this.setSelection(pos);
        }
    }

    public void setSelectId(String idRef){
        SpinnerAdapter ad = (SpinnerAdapter) getAdapter();
        if (ad != null) {
            int pos = ad.getPositionByReference(idRef);
            this.setSelection(pos);
        }
    }

    public boolean hasObligatoryItem(){
        SpinnerAdapter ad = (SpinnerAdapter) this.getAdapter();
        return ad.hasObligatoryItem();
    }

    public spinnersModels getModel(){
        return model;
    }

    public int getSelectedId(){
        if (model == null){
            return -1;
        }else{
            return model.getId();
        }
    }

    public String getSelectedIdRefence(){
        if (model == null){
            return null;
        }else{
            return model.getIdRef();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() instanceof SpinnerAdapter){
            SpinnerAdapter ad = (SpinnerAdapter) parent.getAdapter();
            model = ad.getModel(position);

            if (isObligatorio()) {
                if (model.isSelectionValid()) {
                    unsetError();
                } else {
                    setError();
                }
            }

            if (callback != null){
                callback.onItemSelected(model, position);
            }

        }else{
            throw new RuntimeException("Adapter invlaid, use SpinnerAdapter");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface Callback{
        void onItemSelected(spinnersModels m, int pos);
    }

    private void setError(){
        this.setBackground(getResources().getDrawable(R.drawable.spinner_background_error));
    }

    private void unsetError(){
        this.setBackground(getResources().getDrawable(R.drawable.spinner_background));
    }
}
