package mx.com.marflo.marflolibrary.spinner_basico;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;

import mx.com.marflo.marflolibrary.customs_views.SpinnerPlus;
import mx.com.marflo.marflolibrary.spinner_adapter.SpinnerAdapter;
import mx.com.marflo.marflolibrary.spinner_adapter.spinnersModels;

/**
 * @version : 1
 * @author Ing Alejandro Martínez Flores
 * @since 09/08/2018
 */
public class SpinnerBasico extends SpinnerPlus {

    private ArrayList<spinnersModels> data;

    public SpinnerBasico(Context context, AttributeSet attrs) {
        super(context, attrs);
        data = new ArrayList<>();
    }

    public SpinnerBasico(Context c, String field, String invalidMesage, boolean obligatorio){
        super(c, field, invalidMesage, obligatorio);
        data = new ArrayList<>();
    }

    public ArrayList<spinnersModels> getData() {
        return data;
    }

    public void clearData(){
        data.clear();
    }

    public void addItem(spinnerBasicoModel sm){
        data.add(sm);
    }

    public void setData(ArrayList<spinnerBasicoModel> data) {
        clearData();
        this.data.addAll(data);
    }

    public void setAdapter(){
        SpinnerAdapter ad = new SpinnerAdapter(this.getContext(), data, new spinnerBasicoRender());
        this.setAdapter(ad);
    }
}
