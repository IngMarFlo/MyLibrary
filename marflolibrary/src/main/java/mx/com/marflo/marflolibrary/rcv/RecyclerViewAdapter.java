package mx.com.marflo.marflolibrary.rcv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import mx.com.marflo.marflolibrary.common_class.dataModels;

/**
 * @author :        Ing Alejandro Martínez Flores
 * @since :        02/05/2018
 * @version : 1
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<VH>{

    private final ArrayList<Render>       renders = new ArrayList<>();
    private final ArrayList<Type>         types   = new ArrayList<>();
    private final ArrayList<dataModels>   data    = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Render r =  renders.get(viewType);
        if (r!=null){
            return r.createViewHolder(parent);
        }else{
            throw new RuntimeException("Render not registered. Type : "+viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        dataModels item = getItem(position);

        Render r = getRender(item.getClass());
        if (r!=null){
            r.bind(item, holder);
        }else{
            throw new RuntimeException("View holder not registered : "+holder);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getTypeIndex(getItem(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private Render getRender(Type type){
        int index = getTypeIndex(type);
        return renders.get(index);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    private dataModels getItem(int p){
        return data.get(p);
    }

    private int getTypeIndex(dataModels model){
        return getTypeIndex(model.getClass());
    }

    private int getTypeIndex(Type type){
        int index = types.indexOf(type);
        if (index==-1){
            throw new RuntimeException("View render not registered : "+type);
        }
        return index;
    }

    public void setItems(ArrayList<dataModels> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
    public void replace(int position, dataModels m){
        data.set(position, m);
        notifyDataSetChanged();
    }
    public void addItem(dataModels m){
        data.add(m);
        notifyDataSetChanged();
    }
    public void deleteItem(dataModels m){
        data.remove(m);
        notifyDataSetChanged();
    }
    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }
    public ArrayList<dataModels> getData(){
        return data;
    }

    public void registerRender(Render r){
        Type type = r.getType();

        if (!types.contains(type)){
            types.add(type);
            renders.add(r);
        }else{
            throw new RuntimeException("Render "+type+" already exist");
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition){
            for (int i = fromPosition; i < toPosition; i++){
                Collections.swap(data, i, i+1);
            }
        }else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public void onItemDismiss(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }
}
