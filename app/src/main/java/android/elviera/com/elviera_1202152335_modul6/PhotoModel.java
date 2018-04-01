package android.elviera.com.elviera_1202152335_modul6;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;
//model data yang mereferenkan item photo
@IgnoreExtraProperties
public class PhotoModel implements Parcelable {

    //deklarari class variable
    private String user, title, caption, id;
    private String imgUrl;
    private int like;

    public PhotoModel() {
    }
    public PhotoModel(String user, String title, String caption, String imgUrl, int like) {
        this.user = user;
        this.title = title;
        this.caption = caption;
        this.imgUrl = imgUrl;
        this.like = like;
        //this.comments = new ArrayList<>();
    }
    //untuk mengambil item data secara keseuruhan
    public static final Creator<PhotoModel> CREATOR = new Creator<PhotoModel>() {
        @Override
        public PhotoModel createFromParcel(Parcel in) {
            return new PhotoModel(in);
        }

        @Override
        public PhotoModel[] newArray(int size) {
            return new PhotoModel[size];
        }
    };
    //setter getter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getDisplayName() {
        String email = this.user;
        return email.substring(0, email.lastIndexOf("@"));
    }

    //untuk mengambil item data secara keseuruhan
    public int getCommentsCount() {
        return 0;
    }

    public PhotoModel(Parcel in) {
        this.id = in.readString();
        this.user = in.readString();
        this.title = in.readString();
        this.caption = in.readString();
        this.imgUrl = in.readString();
        this.like = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }
    //untuk mengambil item data secara keseuruhan
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.user);
        parcel.writeString(this.title);
        parcel.writeString(this.caption);
        parcel.writeString(this.imgUrl);
        parcel.writeInt(this.like);
    }

}
