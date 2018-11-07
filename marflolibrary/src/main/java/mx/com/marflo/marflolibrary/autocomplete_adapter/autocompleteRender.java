package mx.com.marflo.marflolibrary.autocomplete_adapter;


import mx.com.marflo.marflolibrary.common_class.commonRender;

/**
 * @version : 1
 * @author Ing Alejandro Martínez Flores
 * @since 09/07/2018
 */
abstract class autocompleteRender<M extends autocompletesModels> extends commonRender<M> {
    autocompleteRender(int resource) {
        super(resource);
    }
}
