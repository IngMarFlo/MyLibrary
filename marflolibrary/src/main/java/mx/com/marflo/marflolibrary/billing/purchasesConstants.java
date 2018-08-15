package mx.com.marflo.marflolibrary.billing;

import android.content.Context;

import mx.com.marflo.marflolibrary.R;

/**
 * Alejandro Martinez Flores on 19/07/2018.
 */
public class purchasesConstants {

	static class PURCHASES_TYPES{
		public static final String INAPP= "inapp";
		public static final String SUBS	= "subs";
	}

	public static String getResponseCodeText(Context c, int code){
		switch (code){
			case RESPONSES_CODES.RESULT_OK:
				return ""; //No se regresa nada ya que no se ejecutará este método cuando el response code sea cero
			case RESPONSES_CODES.USER_CANCELED:
				return c.getResources().getString(R.string.BILLING_RESPONSE_RESULT_USER_CANCELED);
			case RESPONSES_CODES.SERVICE_UNAVAILABLE:
				return c.getResources().getString(R.string.BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE);
			case RESPONSES_CODES.BILLING_UNAVAILABLE:
				return c.getResources().getString(R.string.BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE);
			case RESPONSES_CODES.ITEM_UNAVAILABLE:
				return c.getResources().getString(R.string.BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE);
			case RESPONSES_CODES.DEVELOPER_ERROR:
				return c.getResources().getString(R.string.BILLING_RESPONSE_RESULT_DEVELOPER_ERROR);
			case RESPONSES_CODES.ERROR:
				return c.getResources().getString(R.string.BILLING_RESPONSE_RESULT_ERROR);
			case RESPONSES_CODES.ITEM_ALREADY_OWNED:
				return c.getResources().getString(R.string.BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED);
			case RESPONSES_CODES.ITEM_NOT_OWNED:
				return c.getResources().getString(R.string.BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED);
			default:
				return c.getResources().getString(R.string.BILLING_RESPONSE_UNDEFINED);
		}
	}

	public static class RESPONSES_CODES{
		public static final int RESULT_OK = 0;
		public static final int USER_CANCELED = 1;
		public static final int SERVICE_UNAVAILABLE = 2;
		public static final int BILLING_UNAVAILABLE = 3;
		public static final int ITEM_UNAVAILABLE = 4;
		public static final int DEVELOPER_ERROR = 5;
		public static final int ERROR = 6;
		public static final int ITEM_ALREADY_OWNED = 7;
		public static final int ITEM_NOT_OWNED = 8;
	}

	public static class GET_BUY_INTENT{
		public static final String RESPONSE_CODE = "RESPONSE_CODE";
		public static final String INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
		public static final String INAPP_DATA_SIGNATURE = "INAPP_DATA_SIGNATURE";
	}
}
