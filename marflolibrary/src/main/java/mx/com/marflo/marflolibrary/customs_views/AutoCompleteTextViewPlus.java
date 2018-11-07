package mx.com.marflo.marflolibrary.customs_views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import mx.com.marflo.marflolibrary.autocomplete_adapter.AutocompleteAdapter;
import mx.com.marflo.marflolibrary.autocomplete_adapter.autocompletesModels;

/**
 * Clase para personalizar el AutoCompleteTextView
 * @author        Ing Alejandro Martínez Flores
 * @since        09/07/2018
 * @version      1
 */
public class AutoCompleteTextViewPlus extends android.support.v7.widget.AppCompatAutoCompleteTextView
        implements customView, AdapterView.OnItemClickListener{

    private AutoCompleteTextViewPlus edt;
    private customPropertiesMannager mannager;
    private autocompletesModels model;
    private TextWatcher textWatcher;
    private Callback callback;

    public AutoCompleteTextViewPlus(Context context, AttributeSet attrs) {
        super(context, attrs);

        mannager= new customPropertiesMannager(context, attrs);
        edt     = this;

        if (mannager.getField() != null){
            edt.setTag(mannager.getField());
        }

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                model = null;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        edt.setOnItemClickListener(this);
        edt.addTextChangedListener(textWatcher);
    }

    public void setCallback(Callback callback){
        this.callback = callback;
    }

    public autocompletesModels getSelectedModel(){
        return model;
    }

    public boolean isValid(){
        return model != null;
    }

    public void setTextById(int id){
        AutocompleteAdapter ad = (AutocompleteAdapter) getAdapter();
        autocompletesModels m = ad.getModelById(id);
        if (m != null){
            edt.setText(m.getDescription());
            edt.setSelection(m.getDescription().length());
            model = m;
        }
    }

    public void setTextById(String referenceId){
		AutocompleteAdapter ad = (AutocompleteAdapter) getAdapter();
		autocompletesModels m = ad.getModelById(referenceId);
		if (m != null){
			edt.setText(m.getDescription());
			edt.setSelection(m.getDescription().length());
			model = m;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        edt.removeTextChangedListener(textWatcher);

        if (parent.getAdapter() instanceof AutocompleteAdapter){

            AutocompleteAdapter ad = (AutocompleteAdapter) parent.getAdapter();
            model = ad.getModel(position);

            edt.setText(model.getDescription());
            edt.setSelection(edt.getText().toString().length());

            if (callback != null){
                callback.onItemClick(model, position);
            }

        }else{
            throw new RuntimeException("Invalid adapter, use AutocompleteAdapter");
        }
        edt.addTextChangedListener(textWatcher);

    }

    public interface Callback{
        void onItemClick(autocompletesModels m, int pos);
    }
}
