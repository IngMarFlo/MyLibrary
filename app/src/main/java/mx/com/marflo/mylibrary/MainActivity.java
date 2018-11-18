package mx.com.marflo.mylibrary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

import mx.com.marflo.marflolibrary.FilesUtils;
import mx.com.marflo.marflolibrary.basic_camera.CameraActivity;
import mx.com.marflo.marflolibrary.getCamera;
import mx.com.marflo.marflolibrary.modules_download_manager.MODULES;
import mx.com.marflo.marflolibrary.modules_download_manager.modulesDownloadMannager;
import mx.com.marflo.marflolibrary.permisos_android.AndroidRuntimePermits;
import mx.com.marflo.marflolibrary.permisos_android.androidPermits;
import mx.com.marflo.mylibrary.impl_rcv.ImplementRcv;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		new getCamera().tomarFoto(this, "", 0);
    }

    public void onClickMain(View v){
    	Intent i = null;
    	modulesDownloadMannager mdm = null;
    	switch (v.getId()){
			case R.id.btnActivityMain_customViews:
				break;

			case R.id.btnActivityMain_rcvImpl:
				i = new Intent(this, ImplementRcv.class);
				break;

			case R.id.btnActivityMain_dynamicFeature:
				mdm = new modulesDownloadMannager(MODS.TEST, this);
				break;
		}

		if (i != null){
			startActivity(i);
		}
		if (mdm != null){
			mdm.download();
		}
	}

	private enum MODS implements MODULES {
		TEST("dynamic_feature","mx.com.marflo.dynamic_feature.MainActivity");

		private String name, activity;

		MODS(String name, String activity){
			this.name = name;
			this.activity = activity;
		}

		@Override
		public String getModuleName() {
			return name;
		}

		@Override
		public String getActivityName() {
			return activity;
		}
	}
}
