package mx.com.marflo.marflolibrary.download_files;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 28/08/2018
 */
class DownloadFile extends AsyncTask<String, Integer, File>{

    private DownloadFileCallback callback;
    private Downloader.Callback dC;
    private File dest;
    private Exception e;

    DownloadFile(DownloadFileCallback callback, File dest, Downloader.Callback dC) {
        this.callback   = callback;
        this.dest       = dest;
        this.dC         = dC;
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
            int total = conn.getContentLength();
            long download = 0;

            if (!dest.exists()){
                dest.mkdir();
            }
            file = new File(dest, name);
            FileOutputStream f = new FileOutputStream(file);
            while ((len = in.read(buffer))>0){
                download += len;
                dC.onProgress(total, download);
                f.write(buffer,0,len);
            }
            f.close();

        } catch (final IOException e) {
            this.e = e;
        }
        return file;
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
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
