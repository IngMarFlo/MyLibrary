package mx.com.marflo.marflolibrary.autocomplete_basico;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;

import mx.com.marflo.marflolibrary.autocomplete_adapter.AutocompleteAdapter;
import mx.com.marflo.marflolibrary.autocomplete_adapter.autocompletesModels;
import mx.com.marflo.marflolibrary.customs_views.AutoCompleteTextViewPlus;

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 28/08/2018
 */
public class AutocompleteBasico extends AutoCompleteTextViewPlus {

    ArrayList<autocompletesModels> data;

    public AutocompleteBasico(Context context, AttributeSet attrs) {
        super(context, attrs);
        data = new ArrayList<>();
    }

    public void clearData(){
        data.clear();
    }

    public void addItem(autocompleteBasicoModel m){
        data.add(m);
    }

    public void setData(ArrayList<autocompleteBasicoModel> data) {
        clearData();
        this.data.addAll(data);
    }

    public void setAdapter(){
        AutocompleteAdapter ad = new AutocompleteAdapter(this.getContext(), data, new autocompleteBasicoRender());
        this.setAdapter(ad);
    }
}
