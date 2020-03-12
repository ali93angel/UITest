package app.cashcool.cashcool.features.cashClub.factor.repository.models

import androidx.room.Embedded
import androidx.room.Relation

data class FactorWithItem (

@Embedded
var factorItem: FactorItem = FactorItem(),
@Relation(
    entity = Factor::class,
    entityColumn = "factorId",
    parentColumn = "factor_id"
)
var factor: Factor ?= Factor(),
@Relation(
    entity = Item::class,
    entityColumn = "itemId",
    parentColumn = "item_id"
)
var item: Item? = Item("","","","")
)