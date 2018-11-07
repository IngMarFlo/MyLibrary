package mx.com.marflo.mylibrary.impl_rcv;

import android.os.Parcel;
import android.os.Parcelable;

import mx.com.marflo.marflolibrary.common_class.dataModels;

/**
 * Alejandro Martinez Flores on 06/11/2018.
 */
public class RcvModel extends dataModels {
	private String item;

	RcvModel(String item) {
		this.item = item;
	}

	String getItem() {
		return item;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.item);
	}

	private RcvModel(Parcel in) {
		this.item = in.readString();
	}

	public static final Parcelable.Creator<RcvModel> CREATOR = new Parcelable.Creator<RcvModel>() {
		@Override
		public RcvModel createFromParcel(Parcel source) {
			return new RcvModel(source);
		}

		@Override
		public RcvModel[] newArray(int size) {
			return new RcvModel[size];
		}
	};
}
