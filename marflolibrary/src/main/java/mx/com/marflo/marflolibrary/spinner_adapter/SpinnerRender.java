package mx.com.marflo.marflolibrary.spinner_adapter;

import mx.com.marflo.marflolibrary.common_class.commonRender;

/**
 * @author :        Ing Alejandro Martínez Flores
 * @since :        06/07/2018
 * @version : 1
 */
abstract class SpinnerRender<M extends spinnersModels> extends commonRender<M> {
    SpinnerRender(int resource){
        super(resource);
    }
}
