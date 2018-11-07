package mx.com.marflo.marflolibrary.rcv.swipe_and_drag;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * @author Alejandro Martínez Flores
 * @version : 1
 * @since 07/11/2018.
 */
public class SwipeDragRecyclerView extends ItemTouchHelper.Callback {

	private SwipeDragCallback callback;

	/**
	 * Objeto para indicar si la opción de mantener presionado el elemento esta habilitado
	 */
	private boolean enabledVerticalMove;

	/**
	 * Objeto para indicar si la opción de deslizamiento esta habilitada
	 */
	private int swipeFlags;

	/**
	 * @param callback Clase abstracta para ejecutar acciones debido al movimiento o deslizamiento de un elemento
	 * @param enabledVerticalMove Indicador para habilitar el deslizamiento vertical del elemento
	 * @param swipeFlags Banderas para el deslizamiento del elemento {@link ItemTouchHelper#LEFT} {@link ItemTouchHelper#RIGHT}
	 */
	public SwipeDragRecyclerView(boolean enabledVerticalMove, int swipeFlags, SwipeDragCallback callback){
		this.callback	= callback;
		this.enabledVerticalMove 	= enabledVerticalMove;
		this.swipeFlags	= swipeFlags;
	}

	@Override
	public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
		/*
			Con la sobreescritura de éste método se especifica las direcciones para el arrastre y el deslizamiento
			soportados
		 */
		int dragFlags = (enabledVerticalMove) ? ItemTouchHelper.UP | ItemTouchHelper.DOWN : 0;
		return makeMovementFlags(dragFlags, swipeFlags);
	}

	@Override
	public boolean isLongPressDragEnabled() {
		return enabledVerticalMove;
	}

	@Override
	public boolean isItemViewSwipeEnabled() {
		return swipeFlags > 0;
	}

	@Override
	public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
		if (callback != null) {
			callback.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
			return true;
		}
		return false;
	}

	@Override
	public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
		if (callback != null) {
			callback.onItemSwipe(viewHolder.getAdapterPosition(), direction);
		}
	}

	/*@Override
	public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
		if (actionState == ACTION_STATE_SWIPE) {
			if (buttonState != ButtonState.GONE){

				if (buttonState == ButtonState.LEFT) dX = Math.max(dX, buttonWidth);
				if (buttonState == ButtonState.RIGHT) dX = Math.max(dX, -buttonWidth);

				super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

			}else {
				setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
			}
		}

		if (buttonState == ButtonState.GONE){
			super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
		}

		currentItemViewHolder = viewHolder;

	}*/

	/*@SuppressLint("ClickableViewAccessibility")
	private void setTouchListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY,
								  final int actionState, final boolean isCurrentlyActive) {
		recyclerView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
				if(swipeBack && (showDeleteButton || showEditButton)){

					if (dX < -buttonWidth){
						buttonState = ButtonState.RIGHT;
					} else if(dX > buttonWidth){
						buttonState = ButtonState.LEFT;
					}

					if (buttonState != ButtonState.GONE){
						setTouchDownListener(c, recyclerView, viewHolder, dY, actionState, isCurrentlyActive);
						setItemsClickable(recyclerView, false);
					}
				}
				return false;
			}
		});
	}*/

	/*@SuppressLint("ClickableViewAccessibility")
	private void setTouchDownListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dY,
									  final int actionState, final boolean isCurrentlyActive){
		recyclerView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					setTouchUpListener(c, recyclerView, viewHolder, dY, actionState, isCurrentlyActive);
				}
				return false;
			}
		});
	}*/

	/*@SuppressLint("ClickableViewAccessibility")
	private void setTouchUpListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dY,
									final int actionState, final boolean isCurrentlyActive) {
		recyclerView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					SwipeDragRecyclerView.super.onChildDraw(c, recyclerView, viewHolder, 0F, dY, actionState, isCurrentlyActive);

					recyclerView.setOnTouchListener(new View.OnTouchListener() {
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							return false;
						}
					});

					setItemsClickable(recyclerView, true);

					swipeBack   = false;
					buttonState = ButtonState.GONE;
					currentItemViewHolder = null;


				}
				return false;
			}
		});
	}*/

	/*private void setItemsClickable(RecyclerView recyclerView,
								   boolean isClickable) {
		for (int i = 0; i < recyclerView.getChildCount(); ++i) {
			recyclerView.getChildAt(i).setClickable(isClickable);
		}
	}*/

	/*private void drawButtons(Canvas c, RecyclerView.ViewHolder viewHolder) {
		float buttonWidthWithoutPadding = buttonWidth - 20;
		float corners = 16;

		View itemView = viewHolder.itemView;
		Paint p = new Paint();

		buttonInstance = null;

		if (buttonState == ButtonState.LEFT) {

			RectF leftButton = new RectF(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + buttonWidthWithoutPadding, itemView.getBottom());
			p.setColor(Color.BLUE);
			c.drawRoundRect(leftButton, corners, corners, p);
			drawText("EDIT", c, leftButton, p);

			buttonInstance = leftButton;
		}

		if (buttonState == ButtonState.RIGHT) {

			RectF rightButton = new RectF(itemView.getRight() - buttonWidthWithoutPadding, itemView.getTop(), itemView.getRight(), itemView.getBottom());
			p.setColor(Color.RED);
			c.drawRoundRect(rightButton, corners, corners, p);
			drawText("DELETE", c, rightButton, p);

			buttonInstance = rightButton;
		}
	}

	private void drawText(String text, Canvas c, RectF button, Paint p) {
		float textSize = 60;
		p.setColor(Color.WHITE);
		p.setAntiAlias(true);
		p.setTextSize(textSize);

		float textWidth = p.measureText(text);
		c.drawText(text, button.centerX()-(textWidth/2), button.centerY()+(textSize/2), p);
	}

	public void onDraw(Canvas c) {
		if (currentItemViewHolder != null) {
			drawButtons(c, currentItemViewHolder);
		}
	}*/
}
