package app.cashcool.cashcool.features.cashClub.factor.repository.models


import app.cashcool.cashcool.features.cashClub.repository.model.Club
import app.cashcool.cashcool.features.contact.model.BaseContact

data class ClubWithFactor (
    var club: Club = Club(),
    var factor: Factor= Factor()
):BaseContact(){
    override fun getLable(): String? {
        return club.cashtag
    }

    override fun getPhoneNum(): String? {
        return ""
    }
}
