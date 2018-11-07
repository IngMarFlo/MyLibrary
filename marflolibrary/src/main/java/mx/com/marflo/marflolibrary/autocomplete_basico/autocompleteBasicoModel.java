package mx.com.marflo.marflolibrary.autocomplete_basico;

import android.os.Parcel;

import mx.com.marflo.marflolibrary.autocomplete_adapter.autocompletesModels;

/**
 * @author Alejandro Martínez Flores
 * @version : 1
 * @since 28/08/2018
 */
public class autocompleteBasicoModel extends autocompletesModels {
    private int id;
    private String referenceId, descripcion;
    private autocompletesModels data;

    public autocompleteBasicoModel(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public autocompleteBasicoModel(String referenceId, String descripcion) {
        this.referenceId = referenceId;
        this.descripcion = descripcion;
    }

    public void setAditionalData(autocompletesModels data) {
        this.data = data;
    }

    public autocompletesModels getAditionalData() {
        return data;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getReferenceId() {
        return referenceId;
    }

    @Override
    public String getDescription() {
        return descripcion;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.referenceId);
        dest.writeString(this.descripcion);
        dest.writeParcelable(this.data, flags);
    }

    private autocompleteBasicoModel(Parcel in) {
        this.id = in.readInt();
        this.referenceId = in.readString();
        this.descripcion = in.readString();
        this.data = in.readParcelable(autocompletesModels.class.getClassLoader());
    }

    public static final Creator<autocompleteBasicoModel> CREATOR = new Creator<autocompleteBasicoModel>() {
        @Override
        public autocompleteBasicoModel createFromParcel(Parcel source) {
            return new autocompleteBasicoModel(source);
        }

        @Override
        public autocompleteBasicoModel[] newArray(int size) {
            return new autocompleteBasicoModel[size];
        }
    };
}
