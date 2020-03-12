package app.cashcool.cashcool.features.cashClub.factor.repository.models

import androidx.room.*
import com.google.gson.annotations.SerializedName
import mobilestudio.baseapllication.Base.DiffUtilProperty

@Entity(
    indices = [
        Index(value = ["factor_id","item_id"],unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = Factor::class,
            parentColumns = ["factorId"],
            childColumns = ["factor_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Item::class,
            parentColumns = ["itemId"],
            childColumns = ["item_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.RESTRICT
        )]
)

data class  FactorItem(
    @PrimaryKey (autoGenerate = true)
    var id: Long=0,
    var factor_id: String? = "",
    var item_id: String? = "",
    @SerializedName("quantity")
    var quantity: Float?=0.0f
): DiffUtilProperty {
    @SerializedName("financial_item")
    @Ignore
    var financialItem: Item?= null

    @SerializedName("total_item_amount")
    var price: Float?=0.0f
        set(value) {
            field=value?.div(10)
        }

    @SerializedName("discount")
    var discount: Float?=0.0f
        set(value) {
            field=value?.div(10)
        }
    override fun asSame(): String? {
        return id.toString()
    }
}