package mx.com.marflo.marflolibrary.download_files;

import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import mx.com.marflo.marflolibrary.R;
import mx.com.marflo.marflolibrary.progress_bar_view.ProgressBarMannager;

/**
 * @author Alejandro Martínez Flores
 * @version : 1
 * @since 28/08/2018
 */
class DownloadFile extends AsyncTask<String, Long, File>{

    private DownloadFileCallback callback;
    private File dest;
    private Exception e;
    private ProgressBarMannager pbMannager;

    DownloadFile(DownloadFileCallback callback, Context c, boolean show, File dest) {
        this.callback   = callback;
        this.dest       = dest;

        if (show){
            pbMannager = new ProgressBarMannager(c,
                    R.string.downloader_pb_title,
                    R.string.downloader_tv_init_title);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (pbMannager != null){
            pbMannager.init();
        }
    }

    @Override
    protected File doInBackground(String... strings) {
        String URL = strings[0];

        File file = null;

        try {

            String name = getFileName(URL);

            java.net.URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.connect();

            InputStream in = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int len;
            long total = conn.getContentLength();
            long download = 0;

            if (!dest.exists()){
                dest.mkdir();
            }
            file = new File(dest, name);
            FileOutputStream f = new FileOutputStream(file);
            while ((len = in.read(buffer))>0){
                download += len;
                onProgressUpdate(total, download);
                f.write(buffer,0,len);
            }
            f.close();

        } catch (final IOException e) {
            this.e = e;
        }
        return file;
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
        if (pbMannager != null){
            pbMannager.setProgres(values[0], values[1]);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if (pbMannager != null){
            pbMannager.close();
        }

        if (file != null){
            callback.onFinish(file);
        }

        if (e != null){
            callback.onException(e);
        }
    }

    static String getFileName(String url){
        String[] aURL = url.split("/");
        return aURL[aURL.length - 1];
    }
}
