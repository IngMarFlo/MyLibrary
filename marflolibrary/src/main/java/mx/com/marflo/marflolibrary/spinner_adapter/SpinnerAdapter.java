package mx.com.marflo.marflolibrary.spinner_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * @author :        Ing Alejandro Martínez Flores
 * @since :        06/07/2018
 * @version : 1
 */
public class SpinnerAdapter extends ArrayAdapter<spinnersModels>{

    private SpinnerRender   render;
    private ArrayList<spinnersModels>       data;

    public SpinnerAdapter(@NonNull Context context, ArrayList<spinnersModels> data, SpinnerRender render) {
        super(context, render.getResource(), data);
        this.render = render;
        this.data   = data;
    }

    public void setData(ArrayList<spinnersModels> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public spinnersModels getItem(int position) {
        return super.getItem(position);
    }

    public int getPositionById(int id){
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).getId() == id){
                return i;
            }
        }
        return 0;
    }
    public int getPositionByReference(String ref){
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).getIdRef().equals(ref)){
                return i;
            }
        }
        return 0;
    }

    public boolean hasObligatoryItem(){
    	if (data.get(0).getId() == 0){
			return data.get(0).getIdRef() != null && data.get(0).getIdRef().equals("-1");
		}else {
			return data.get(0).getId() == -1;
		}
    }

    public spinnersModels getModel(int pos){
        return data.get(pos);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return render.getDropDownView(getModel(position), convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return render.getView(getModel(position), convertView, parent);
    }
}
