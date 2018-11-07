package mx.com.marflo.marflolibrary.autocomplete_basico;

import mx.com.marflo.marflolibrary.R;
import mx.com.marflo.marflolibrary.autocomplete_adapter.AutocompleteRenderTemplate;
import mx.com.marflo.marflolibrary.common_interfaces.finderCommon;

/**
 * @author Alejandro Martínez Flores
 * @version : 1
 * @since 28/08/2018
 */
public class autocompleteBasicoRender extends AutocompleteRenderTemplate<autocompleteBasicoModel> {
    public autocompleteBasicoRender() {
        super(R.layout.card_autocomplete_basico);
    }

    @Override
    public void setInfo(autocompleteBasicoModel model, finderCommon finder) {
        finder.setText(R.id.tvCardAutocompleteBasico, model.getDescription());
    }
}
