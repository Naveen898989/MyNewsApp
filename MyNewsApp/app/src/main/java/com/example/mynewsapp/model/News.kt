package com.example.mynewsapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class News() : Parcelable {

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("author")
    var author: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("url")
    lateinit var url: String

    @SerializedName("urlToImage")
    lateinit var urlToImage: String

    @SerializedName("publishedAt")
    lateinit var publishedAt: String

    @SerializedName("content")
    var content: String? = null

    @SerializedName("source")
    var source: Source? = null

    class Source() : Parcelable {

        @SerializedName("id")
        var id: String? = null

        @SerializedName("name")
        var name: String? = null

        constructor(parcel: Parcel) : this() {
            id = parcel.readString()
            name = parcel.readString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(name)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Source> {
            override fun createFromParcel(parcel: Parcel): Source {
                return Source(parcel)
            }

            override fun newArray(size: Int): Array<Source?> {
                return arrayOfNulls(size)
            }
        }
    }

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()!!
        author = parcel.readString()
        description = parcel.readString()
        url = parcel.readString()!!
        urlToImage = parcel.readString()!!
        publishedAt = parcel.readString()!!
        content = parcel.readString()
        source = parcel.readParcelable(Source::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(content)
        parcel.writeParcelable(source, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }

}