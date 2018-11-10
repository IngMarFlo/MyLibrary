package mx.com.marflo.marflolibrary.customs_views;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import mx.com.marflo.marflolibrary.FilesUtils;
import mx.com.marflo.marflolibrary.R;

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 10/11/2018.
 */
public class ImageViewPlus extends android.support.v7.widget.AppCompatImageView implements customView{
	private customPropertiesMannager mannager;
	private Context context;
	private String path;

	public ImageViewPlus(Context c, String field, String invalidMesage, boolean obligatorio){
		super(c);
		this.context = c;
		mannager = new customPropertiesMannager(c, field, invalidMesage, obligatorio);
		if (field != null){
			this.setTag(field);
		}
	}

	public ImageViewPlus(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		mannager = new customPropertiesMannager(context, attrs);

		if (mannager.getField() != null){
			this.setTag(mannager.getField());
		}
	}

	public void clear(){
		path = null;
		setImageDrawable(context.getResources().getDrawable(R.drawable.ic_galeria));
	}

	public void setImagePath(Uri uri){
		setImagePath(FilesUtils.getPath(context, uri));
	}

	public void setImagePath(File file){
		setImagePath(file.getPath());
	}

	public void setImagePath(String filePath){
		this.path = filePath;
		if (isValidPath()){
			if (isServerFile()){
				//Se considera un archivo externo...
				Glide.with(context)
						.load(path)
						.asBitmap()
						.centerCrop().
						listener(new RequestListener<String, Bitmap>() {
							@Override
							public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
								//Toast.makeText(c, "No pudo cargarse la imagen", Toast.LENGTH_SHORT).show();
								return false;
							}

							@Override
							public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target,
														   boolean isFromMemoryCache, boolean isFirstResource) {
								setImageBitmap(resource);
								return true;
							}
						}).into(this);
			}else{
				Glide.with(context)
						.load(new File(path))
						.asBitmap()
						.centerCrop()
						.listener(new RequestListener<File, Bitmap>() {
							@Override
							public boolean onException(Exception e, File model, Target<Bitmap> target, boolean isFirstResource) {
								return false;
							}

							@Override
							public boolean onResourceReady(Bitmap resource, File model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
								setImageBitmap(resource);
								return true;
							}
						}).into(this);
			}
		}
	}

	public String getPath(){
		return  path;
	}

	public boolean isValidPath(){
		return path != null && !path.isEmpty() && !path.equals(" ");
	}

	public boolean isServerFile(){
		return path.contains("http");
	}

	@Override
	public String getField() {
		return mannager.getField();
	}

	@Override
	public String getInvalidMessage() {
		return mannager.getInvalidMessage();
	}

	@Override
	public boolean isObligatorio() {
		return mannager.isObligatorio();
	}

	@Override
	public void setObligatorio(boolean ob) {
		mannager.setObligatorio(ob);
	}
}
