package app.cashcool.cashcool.features.cashClub.repository.model

import androidx.annotation.NonNull
import androidx.room.*
import app.cashcool.cashcool.core.database.PagingModel
import app.cashcool.cashcool.core.pushNotification.baseNotification
import app.cashcool.cashcool.features.cashClub.factor.repository.models.Factor
import app.cashcool.cashcool.features.components.textdrawable.ColorGenerator
import app.cashcool.cashcool.features.components.textdrawable.TextDrawable
import app.cashcool.cashcool.features.contact.model.Contact
import com.google.gson.annotations.SerializedName
import mobilestudio.baseapllication.Base.DiffUtilProperty
import java.util.*

@Entity(indices = [
Index(value = ["ownerId"],unique = true)
])
data class Club(
    @PrimaryKey
    @SerializedName("id")
    var id:String=""
    ,
    @NonNull
    @SerializedName("owner_id")
    var ownerId:Long=0,
    @SerializedName("title")
    var name:String?=""
    ,
    @SerializedName("cashtag")
    var cashtag:String?=""
    ,
    @SerializedName("profile_picture")
    var profilePic:String?=""
    ,
    @SerializedName("header_picture")
    var headerPic:String?=""
    ,
    @SerializedName("description")
    var description:String=""
    ,
    @SerializedName("tag")
    var tag: List<Tag?>?= emptyList()
    ,
    @SerializedName("address")
    var address:String?=""
    ,
    @SerializedName("created_at")
    @ColumnInfo(name="created_at")
    var createAt :Date?=null
    ,
    @SerializedName("updated_at")
    @ColumnInfo(name="updated_at")
    var updateAt :Date?=null
    ,
    @SerializedName("location")
    @Embedded
    var Location :Coordinates=Coordinates()
    ,
    var context :String?="",
    @Ignore
    @SerializedName("factors")
    var factors: List<Factor> = emptyList()
    ) : DiffUtilProperty, baseNotification,PagingModel {
    override fun getPagingUpdateAt(): Date? =updateAt

    override fun asSame(): String? {
        return id
    }

    fun createDefaultAvatar(borderSize: Int = 0, borderColor: String = ""): TextDrawable? {

            val avatarColor = colorGenerator.getColor(this.cashtag)
            val avatarText = name.takeIf { !it.isNullOrEmpty() }?.substring(0, 1)?.capitalize()
                ?: cashtag.takeIf { !it.isNullOrEmpty() }?.substring(0, 1)?.capitalize()

        return TextDrawable.builder().beginConfig().withBorder(borderSize)
                .withBorderColor(borderColor).endConfig()
                .buildRound(avatarText?: "~", avatarColor)
    }
    companion object {
        private val colorGenerator = ColorGenerator.MATERIAL
    }
}

data class Coordinates (
    var latitude :Float= 0.0f,
    var longitude :Float= 0.0f
)

data class Tag(
    @SerializedName("name")
    var name: String?
)