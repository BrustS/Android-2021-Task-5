package by.brust.task_5_cats.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    @SerializedName("url")
    val url: String?
) : Parcelable
