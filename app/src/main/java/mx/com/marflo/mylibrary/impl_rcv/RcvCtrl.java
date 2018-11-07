package mx.com.marflo.mylibrary.impl_rcv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcel;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

import mx.com.marflo.marflolibrary.common_class.dataModels;
import mx.com.marflo.marflolibrary.rcv.RecyclerViewAdapter;
import mx.com.marflo.marflolibrary.rcv.ViewHolderCallback;
import mx.com.marflo.marflolibrary.rcv.ViewHolderTemplate;
import mx.com.marflo.marflolibrary.rcv.finder;
import mx.com.marflo.marflolibrary.rcv.swipe_and_drag.SwipeDragCallback;
import mx.com.marflo.marflolibrary.rcv.swipe_and_drag.SwipeDragRecyclerView;
import mx.com.marflo.mylibrary.R;

/**
 * Controlador para la actividad {@link ImplementRcv}
 * @author : Alejandro Martínez Flores
 * @since :	06/11/2018
 * @version : 1
 */
class RcvCtrl {
	private RcvPresenter presenter;
	private RecyclerViewAdapter adapter;
	private Context context;
	private int count = 1;

	RcvCtrl(Context c, RcvPresenter presenter) {
		this.presenter = presenter;
		this.context = c;
	}

	void init(){
		adapter = new RecyclerViewAdapter();

		adapter.registerRender(new RcvRender(new ViewHolderCallback<RcvModel>() {
			@Override
			public void onItemClick(RcvModel model) {
				presenter.showToastOnItemClick(model.getItem());
			}

			@Override
			public void onItemLongClick(RcvModel model) {
				presenter.showToastOnItemClick("Long click on "+model.getItem());
			}
		}));
		adapter.registerRender(new LastItemRender());

		presenter.setAdapter(adapter);
	}

	void putData(){
		ArrayList<dataModels> data = new ArrayList<>();
		String item = context.getResources().getString(R.string.implement_rcv_item);
		for (int i = 0; i < 5; i ++){
			data.add(new RcvModel(item+" "+String.valueOf(count)));
			count++;
		}
		
		adapter.setItems(data);
		adapter.addItem(new LastItemModel());

		presenter.stopRefresh();
	}

	void setSwipeDrag(RecyclerView rcv){
		SwipeDragRecyclerView swipeDrag = new SwipeDragRecyclerView(
				true,
				ItemTouchHelper.RIGHT,
				new SwipeDragCallback() {
					@Override
					public void onItemMove(int fromPosition, int toPosition) {
						adapter.onItemMove(fromPosition, toPosition);
					}

					@Override
					public void onItemSwipe(int position, int direction) {
						if (direction == ItemTouchHelper.LEFT) {
							adapter.onItemDismiss(position);
						}else{
							adapter.notifyDataSetChanged();
						}
					}
				});
		ItemTouchHelper helper = new ItemTouchHelper(swipeDrag);
		helper.attachToRecyclerView(rcv);
	}

	private class LastItemRender extends ViewHolderTemplate<LastItemModel>{

		LastItemRender() {
			super(LastItemModel.class, R.layout.card_implement_rcv_last_item);
		}

		@Override
		protected void setInfo(LastItemModel model, finder finder) {
			finder.setText(R.id.tvCardImplementRcvLastItem, model.getLastItem(finder.getRootView().getContext()));
		}
	}

	@SuppressLint("ParcelCreator")
	private class LastItemModel extends dataModels{

		String getLastItem(Context c){
			return c.getResources().getString(R.string.tvImplementRcv);
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel parcel, int i) {

		}
	}
}
