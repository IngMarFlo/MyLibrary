package mx.com.marflo.marflolibrary.billing;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import mx.com.marflo.marflolibrary.HandleException;
import mx.com.marflo.marflolibrary.PersonalDialog;
import mx.com.marflo.marflolibrary.R;
import mx.com.marflo.marflolibrary.utils;

import static android.app.Activity.RESULT_OK;

/**
 * Clase que se encarga de realizar la compra de productos en la Play Store
 * @version 1
 * @author  Ing Alejandro Martínez Flores
 * @since   15/08/2018
 */
public class purchasesMannager {

    public static final int REQUEST = 1020;

    private IInAppBillingService mService;
    private ServiceConnection connection;
    private Context c;
    private String key;

    public purchasesMannager(Context c){
        this.c = c;
    }

    public void init(){
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = IInAppBillingService.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
            }
        };

        Intent iService = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        iService.setPackage("com.android.vending");
        c.bindService(iService, connection, Context.BIND_AUTO_CREATE);
    }
    public void destroy(){
        if (mService != null){
            c.unbindService(connection);
        }
    }
    public void startPurchase(PlayItems item){

        try {
            key = random();
            Bundle buyIntentBundle = mService.getBuyIntent(3, c.getPackageName(),
                    item.getSku(), purchasesConstants.PURCHASES_TYPES.INAPP, key);

            int rc = buyIntentBundle.getInt(purchasesConstants.GET_BUY_INTENT.RESPONSE_CODE);

            if (rc == 0){
                PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
                if (pendingIntent != null) {
                    ((Activity) c).startIntentSenderForResult(pendingIntent.getIntentSender(),
                            REQUEST, new Intent(), 0, 0, 0);
                }
            }else{
                Toast.makeText(c,purchasesConstants.getResponseCodeText(c, rc), Toast.LENGTH_LONG).show();
            }

        } catch ( RemoteException |IntentSender.SendIntentException e) {
            new HandleException(c).show(e);
        }
    }

    public void checkResult(int resultCode, Intent data, CheckCallback callback, PurchaseCallback pcallback){

        if (resultCode == RESULT_OK) {
            try {
                int     responseCode = data.getIntExtra("RESPONSE_CODE", 0);
                String  purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");

                JSONObject jo = new JSONObject(purchaseData);
                String gKey = jo.getString("developerPayload");

                if (gKey.equals(key)) {

                    pcallback.Info(
                            jo.getString("orderId"),
                            jo.getString("productId"),
                            jo.getString("purchaseState"),
                            jo.getString("purchaseTime"),
                            jo.getString("purchaseToken"),
                            "", responseCode
                    );

                    String token= jo.getString("purchaseToken");
                    callback.onCheckFinish(token);
                    Toast.makeText(c, c.getResources().getString(R.string.COMPRA_REALIZADA), Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(c, c.getResources().getString(R.string.COMPRA_NO_RECONOCIDA), Toast.LENGTH_LONG).show();
                }
            }
            catch (JSONException e) {
                Toast.makeText(c, c.getResources().getString(R.string.COMPRA_FALLO_CREACION_JSON), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }else{
            Toast.makeText(c,  c.getResources().getString(R.string.COMPRA_CANCELADA), Toast.LENGTH_LONG).show();
        }
    }

    public void consume(String token){
        try {
            int rc = mService.consumePurchase(3, c.getPackageName(), token);
            if (rc != 0){

                String message = purchasesConstants.getResponseCodeText(c, rc);
                Toast.makeText(c,message, Toast.LENGTH_LONG).show();

            }
        } catch (RemoteException e) {
            new HandleException(c).show(e);
        }
    }

    public void checkPurchases(PlayItems type, CheckCallback callback, PurchaseCallback pcallback) {

        try {
            Bundle ownedItems = mService.getPurchases(3, c.getPackageName(), purchasesConstants.PURCHASES_TYPES.INAPP, null);

            int rc = ownedItems.getInt("RESPONSE_CODE");
            if (rc == 0){

                ArrayList<String> ipdl = ownedItems.getStringArrayList("INAPP_PURCHASE_DATA_LIST");

                boolean comprado = false;
                if (ipdl != null) {

                    for (String t : ipdl) {

                        JSONObject jo = new JSONObject(t);
                        String token  = jo.getString("purchaseToken");
                        String productId = jo.getString("productId");
                        if (productId.equals(type.getSku())){
                            comprado = true;

                            Calendar cal = Calendar.getInstance();

                            pcallback.Info(
                                    jo.getString("orderId"),
                                    jo.getString("productId"),
                                    jo.getString("purchaseState"),
                                    jo.getString("purchaseTime"),
                                    jo.getString("purchaseToken"),
                                    utils.getSimpleDateFormat().format(cal.getTime()),
                                    rc
                            );
                            callback.onCheckFinish(token);
                            break;
                        }

                    }

                }

                if (!comprado) {
                    callback.onCheckFinish(null);
                }

            }else{
                String message = purchasesConstants.getResponseCodeText(c, rc);
                Toast.makeText(c,message, Toast.LENGTH_LONG).show();
            }
        } catch (RemoteException |JSONException e) {
            new HandleException(c).show(e);
        }
    }

    public void copyFile(String token, File src, final File dst) {
        FileChannel inChannel;
        FileChannel outChannel;
        try {
            inChannel = new FileInputStream(src).getChannel();
            outChannel = new FileOutputStream(dst).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);

            inChannel.close();
            outChannel.close();

            consume(token);

            String title    = c.getResources().getString(R.string.dialog_pdf_viewer_copy_finish_title);
            String message  = c.getResources().getString(R.string.dialog_pdf_viewer_copy_finish_message)+"\n"+dst.getPath();

            PersonalDialog dialog = new PersonalDialog();
            dialog.setShowNo(c.getResources().getString(R.string.dialog_pdf_viewer_copy_finish_view));
            dialog.showDialog(c, title, message, PersonalDialog.ICON.CORRECT, new PersonalDialog.Callback() {
                @Override
                public void onPositiveClick(AlertDialog dialog) {
                    dialog.dismiss();
                }

                @Override
                public void onNegativeClick(AlertDialog dialog) {
                    dialog.dismiss();
                    utils.visualizarArchivoConChooser(c, dst, 0);
                }

                @Override
                public void onNeutralClick(AlertDialog dialog) {
                    dialog.dismiss();
                }
            });

        } catch (IOException e) {
            new HandleException(c).show(e);
        }
    }

    public interface CheckCallback{
        void onCheckFinish(String token);
    }

    public interface PurchaseCallback{
        void Info(String orderId, String productId, String purchaseState, String purchaseTime, String purchaseToken, String consumeDate, int responseCode);
    }

    private String random() {
        Random generator = new Random();
        String[] abc = getAbc();

        StringBuilder randomStringBuilder = new StringBuilder();

        for (int i = 0; i < 50; i++){
            int pos = generator.nextInt(abc.length-1);
            String letter = abc[pos];
            if (i%2 == 0 && pos < 25){
                letter = letter.toUpperCase();
            }

            randomStringBuilder.append(letter);
        }

        return randomStringBuilder.toString();
    }
    private String[] getAbc(){
        return new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    }
}
