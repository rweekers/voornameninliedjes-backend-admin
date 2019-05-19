package nl.orangeflamingo.voornameninliedjesbackendadmin.generic

class Utils {
    companion object {
        const val INVALID_CREDENTIALS = "Gebruikersnaam en/of wachtwoord onjuist"
    }
}

data class ResponseError(val message: String)