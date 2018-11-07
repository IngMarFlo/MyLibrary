package mx.com.marflo.marflolibrary.spinner_basico;

import android.os.Parcel;
import android.os.Parcelable;

import mx.com.marflo.marflolibrary.spinner_adapter.spinnersModels;


/**
 * @version : 1
 * @author Ing Alejandro Martínez Flores
 * @since 09/08/2018
 */
public class spinnerBasicoModel extends spinnersModels {
    private int     id;
    private String  idReference, descripcion;
    private spinnersModels data;

    public spinnerBasicoModel(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public spinnerBasicoModel(String idReference, String descripcion) {
        this.idReference = idReference;
        this.descripcion = descripcion;
    }

    public void setAditionalData(spinnersModels sm){
        data = sm;
    }

    public spinnersModels getAditionalData(){
        return data;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.descripcion);
        dest.writeParcelable(this.data, flags);
    }

    private spinnerBasicoModel(Parcel in) {
        this.id = in.readInt();
        this.descripcion = in.readString();
        this.data = in.readParcelable(spinnersModels.class.getClassLoader());
    }

    public static final Parcelable.Creator<spinnerBasicoModel> CREATOR = new Parcelable.Creator<spinnerBasicoModel>() {
        @Override
        public spinnerBasicoModel createFromParcel(Parcel source) {
            return new spinnerBasicoModel(source);
        }

        @Override
        public spinnerBasicoModel[] newArray(int size) {
            return new spinnerBasicoModel[size];
        }
    };

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getIdRef() {
        return idReference;
    }
}

