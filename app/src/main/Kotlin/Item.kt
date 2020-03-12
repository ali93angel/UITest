package app.cashcool.cashcool.features.cashClub.factor.repository.models

import androidx.room.*
import com.google.gson.annotations.SerializedName
import mobilestudio.baseapllication.Base.DiffUtilProperty

@Entity()
data class Item(
    @PrimaryKey()
    @SerializedName("id")
    var itemId: String = "",
    @SerializedName("code")
    val code: String?="",
    @SerializedName("description")
    val description: String?="",
    @SerializedName("name")
    val name: String?="",
    @SerializedName("unit")
    var unit: String? = "عدد"
    /*,
    @SerializedName("pictures")
    var pictures: ArrayList<String>?*/
):DiffUtilProperty{

    @ColumnInfo(name="price")
    @SerializedName("price")
    var price: Float?=0.0f
        set(value) {
            field=value?.div(10)
        }

    override fun asSame(): String? {
       return itemId
    }
}


