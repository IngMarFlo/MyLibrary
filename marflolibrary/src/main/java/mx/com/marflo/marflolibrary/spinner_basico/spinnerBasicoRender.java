package mx.com.marflo.marflolibrary.spinner_basico;

import mx.com.marflo.marflolibrary.R;
import mx.com.marflo.marflolibrary.common_interfaces.finderCommon;
import mx.com.marflo.marflolibrary.spinner_adapter.SpinnerRenderTemplate;

/**
 * @version : 1
 * @author Ing Alejandro Martínez Flores
 * @since 09/08/2018
 */
public class spinnerBasicoRender extends SpinnerRenderTemplate<spinnerBasicoModel> {
    spinnerBasicoRender() {
        super(R.layout.card_spinner_basico);
    }

    @Override
    public void setViewInfo(spinnerBasicoModel model, finderCommon finder) {
        finder.setText(R.id.tvCardSpinnerBasico, model.getDescripcion());
    }

    @Override
    public void setDropDownViewInfo(spinnerBasicoModel model, finderCommon finder) {
        finder.setText(R.id.tvCardSpinnerBasico, model.getDescripcion());
    }
}
