package mx.com.marflo.mylibrary.impl_rcv;

import mx.com.marflo.marflolibrary.rcv.ViewHolderTemplate;
import mx.com.marflo.marflolibrary.rcv.finder;
import mx.com.marflo.mylibrary.R;

/**
 * Alejandro Martinez Flores on 06/11/2018.
 */
public class RcvRender extends ViewHolderTemplate<RcvModel> {

	RcvRender() {
		super(RcvModel.class, R.layout.card_implement_rcv);
	}

	@Override
	protected void setInfo(RcvModel model, finder finder) {
		finder.setText(R.id.tvCardImplementRcv, model.getItem());
	}
}
