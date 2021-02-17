package models

import java.io.Serializable

data class CountryInfo (
    val name: String? = null,
    val capital: String? = null,
    val region: String? = null,
    val subregion: String? = null,
    val population: Int? = null
) : Serializable {
}
