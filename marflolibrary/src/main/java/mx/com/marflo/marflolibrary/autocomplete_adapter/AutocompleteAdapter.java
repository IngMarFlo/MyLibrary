package mx.com.marflo.marflolibrary.autocomplete_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;

/**
* Autor:        Ing Alejandro Martínez Flores
* Fecha:        22/12/2016
* Descripción:
* Utilización:
*/

public class AutocompleteAdapter extends ArrayAdapter<autocompletesModels> {

    private AutocompleteRenderTemplate<autocompletesModels> render;
    private ArrayList<autocompletesModels>      data;
    public final ArrayList<autocompletesModels> filtro;

    public AutocompleteAdapter(@NonNull Context context, ArrayList<autocompletesModels> data, AutocompleteRenderTemplate render) {
        super(context, render.getResource(), data);
        this.render = render;
        this.data   = data;
        this.filtro = new ArrayList<>();
    }

    public autocompletesModels getModel(int pos){
        return filtro.get(pos);
    }

    public autocompletesModels getModelById(int id){
        for (autocompletesModels m : data){
            if (m.getId() == id){
                return m;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return filtro.size();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new AutocompelteFilter(this, data);
    }

    /*@Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return render.getDropDownView(getModel(position), convertView, parent);
    }*/

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return render.getView(getModel(position), convertView, parent);
    }
}