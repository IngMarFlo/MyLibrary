package mx.com.marflo.marflolibrary.autocomplete_adapter;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @version : 1
 * @author Ing Alejandro Martínez Flores
 * @since 09/07/2018
 */
public class AutocompelteFilter extends Filter {

    private List<autocompletesModels>   data, filter;
    private AutocompleteAdapter         adapter;

    AutocompelteFilter(AutocompleteAdapter adapter, List<autocompletesModels> data){
        this.adapter= adapter;
        this.data   = data;
        this.filter = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filter.clear();
        final FilterResults results = new FilterResults();
        if (constraint == null || constraint.length() == 0){
            filter.addAll(data);
        }else {
            final String filterPatern = constraint.toString().toLowerCase().trim();

            //Logica de filtrado
            for (final autocompletesModels model : data){
                if (model.getDescription().toLowerCase().contains(filterPatern)){
                    filter.add(model);
                }
            }
        }
        results.values  = filter;
        results.count   = filter.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.filtro.clear();
        adapter.filtro.addAll((List)results.values);
        adapter.notifyDataSetChanged();
    }
}
