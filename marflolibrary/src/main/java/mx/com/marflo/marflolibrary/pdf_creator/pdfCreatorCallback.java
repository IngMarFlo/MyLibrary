package mx.com.marflo.marflolibrary.pdf_creator;

import java.io.File;

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 28/08/2018
 */
public interface pdfCreatorCallback {
    void onFinish(File f);
    void onException(Exception e);
}
