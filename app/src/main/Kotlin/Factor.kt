package app.cashcool.cashcool.features.cashClub.factor.repository.models

import androidx.room.*
import app.cashcool.cashcool.core.database.PagingModel
import app.cashcool.cashcool.core.pushNotification.baseNotification
import app.cashcool.cashcool.features.payment.transaction.repository.model.Status
import com.google.gson.annotations.SerializedName
import mobilestudio.baseapllication.Base.DiffUtilProperty
import java.util.*

@Entity( indices = [
    Index("factorId"),
    Index("clubId")
])
data class Factor(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "factorId")
    var factorId: String = "",
    @SerializedName("created_at")
    @ColumnInfo(name="created_at")
    var createAt : Date?=null
    ,@SerializedName("updated_at")
    @ColumnInfo(name="updated_at")
    var updateAt : Date?=null,
    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String = "",
    @SerializedName("receiver")
    @ColumnInfo(name = "receiver")
    var receiver: Long = 0,
    @SerializedName("tax")
    @ColumnInfo(name = "tax")
    var tax: Float = 0f,
    @SerializedName("transaction")
    @ColumnInfo(name = "transaction")
    var transaction: Long=0,
    @SerializedName("status")
    @ColumnInfo(name = "status")
    var status: Status = Status.PENDING,
    @Ignore
    @SerializedName("items")
    var items: List<FactorItem>,
    var clubId:Int=0//todo rename it to clubOwnerID
) : DiffUtilProperty, baseNotification,PagingModel {

    constructor() : this("",null,null,"",0,0f,0,Status.PENDING, listOf<FactorItem>(),0)

    @SerializedName("total_amount")
    @ColumnInfo(name = "total_price")
    var totalPrice: Float?=0.0f
        set(value) {
            field=value?.div(10)
        }

    @SerializedName("discount")
    @ColumnInfo(name = "discount")
    var discount: Float = 0f
        set(value) {
            field=value?.div(10)
        }
    override fun asSame(): String? {
        return factorId
    }

    override fun getPagingUpdateAt(): Date? =createAt
}

