package com.thedevwolf.mvvmdemo.data.db.Entity
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "data")
class MovieEntity(var vote_count: Int?,
                  @PrimaryKey var id: Int?,
                  var video: Boolean?,
                  var vote_average: Double?,
                  var title: String?,
                  var popularity: Double?,
                  var poster_path: String?,
                  var original_language: String?,
                  var original_title: String?,
                  var backdrop_path: String?,
                  var adult: Boolean?,
                  var overview: String?,
                  var release_date: String?) : Parcelable {
    constructor(source: Parcel) : this(
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Boolean::class.java.classLoader) as Boolean?,
        source.readValue(Double::class.java.classLoader) as Double?,
        source.readString(),
        source.readValue(Double::class.java.classLoader) as Double?,
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readValue(Boolean::class.java.classLoader) as Boolean?,
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(vote_count)
        writeValue(id)
        writeValue(video)
        writeValue(vote_average)
        writeString(title)
        writeValue(popularity)
        writeString(poster_path)
        writeString(original_language)
        writeString(original_title)
        writeString(backdrop_path)
        writeValue(adult)
        writeString(overview)
        writeString(release_date)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieEntity> = object : Parcelable.Creator<MovieEntity> {
            override fun createFromParcel(source: Parcel): MovieEntity = MovieEntity(source)
            override fun newArray(size: Int): Array<MovieEntity?> = arrayOfNulls(size)
        }
    }
}