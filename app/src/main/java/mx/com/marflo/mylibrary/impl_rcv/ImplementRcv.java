package mx.com.marflo.mylibrary.impl_rcv;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import mx.com.marflo.marflolibrary.rcv.RecyclerViewAdapter;
import mx.com.marflo.mylibrary.R;

public class ImplementRcv extends AppCompatActivity
		implements RcvPresenter, SwipeRefreshLayout.OnRefreshListener {

	private RcvCtrl ctrl;

	private RecyclerView rcv;
	private TextView tv;
	private SwipeRefreshLayout swipe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_implement_rcv);

		swipe = findViewById(R.id.swipeImplementRcv);
		swipe.setOnRefreshListener(this);

		rcv = findViewById(R.id.rcvImplementRcv);
		//rcv.setHasFixedSize(true);
		rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

		tv = findViewById(R.id.tvImplementRcv);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (ctrl == null){
			ctrl = new RcvCtrl(this,this);
		}
		ctrl.init();
	}

	@Override
	public void setAdapter(RecyclerViewAdapter adapter) {
		rcv.setAdapter(adapter);
		ctrl.setSwipeDrag(rcv);
	}

	@Override
	public void stopRefresh() {
		swipe.setRefreshing(false);
		tv.setVisibility(View.GONE);
	}

	@Override
	public void showToastOnItemClick(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onRefresh() {
		swipe.setRefreshing(true);
		ctrl.putData();
	}
}
