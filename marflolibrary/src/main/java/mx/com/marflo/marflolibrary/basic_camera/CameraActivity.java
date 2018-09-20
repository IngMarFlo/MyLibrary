package mx.com.marflo.marflolibrary.basic_camera;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import mx.com.marflo.marflolibrary.PersonalDialog;
import mx.com.marflo.marflolibrary.R;

public class CameraActivity extends AppCompatActivity {

    public static final String DESTINY  = "destiny";
    public static final String RESULT   = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        if (getIntent().getExtras() == null){
            new PersonalDialog().showDialog(
                    this,
                    getResources().getString(R.string.camera_basic_no_data_title),
                    getResources().getString(R.string.camera_basic_no_data_message),
                    PersonalDialog.ICON.WARNING,
                    new PersonalDialog.Callback() {
                        @Override
                        public void onPositiveClick(AlertDialog alertDialog) {
                            alertDialog.dismiss();
                            finish();
                        }

                        @Override
                        public void onNeutralClick(AlertDialog alertDialog) {

                        }

                        @Override
                        public void onNegativeClick(AlertDialog alertDialog) {

                        }
                    });
            return;
        }

        if(savedInstanceState == null){
            Fragment fr = CameraBasicFragment.newInstance();
            fr.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fr)
                    .commit();
        }
    }
}
