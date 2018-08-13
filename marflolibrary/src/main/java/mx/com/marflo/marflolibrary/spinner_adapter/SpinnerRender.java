package mx.com.marflo.marflolibrary.spinner_adapter;

import mx.com.marflo.marflolibrary.common_class.commonRender;

/**
 * Autor:        Ing Alejandro Martínez Flores
 * Fecha:        06/07/2018
 * Descripción:
 */
abstract class SpinnerRender<M extends spinnersModels> extends commonRender<M> {
    SpinnerRender(int resource){
        super(resource);
    }
}
