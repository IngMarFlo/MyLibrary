package mx.com.marflo.marflolibrary.rcv.swipe_and_drag;

/**
 * @author Alejandro Martínez Flores
 * @version : 1
 * @since 07/11/2018.
 */
public abstract class SwipeDragCallback {
	public void onItemMove(int fromPosition, int toPosition){};
	public void onItemSwipe(int position,int direction){};
}
