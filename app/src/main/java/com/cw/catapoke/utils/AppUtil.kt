package com.cw.catapoke.utils

import com.cw.catapoke.utils.Constants.BASE_URL_IMAGE
import java.lang.Exception

object AppUtil {

    fun getId(path: String?):Int {
        var id = -1
        try {
            path?.let { url ->
                val urlParams = url.filter { it.isDigit() }
                id = urlParams.substring(1, urlParams.length).toInt()
            }
        } catch (Ex: Exception) { }
        return id
    }

    fun getImageUrl(id: Int) : String {
        return "$BASE_URL_IMAGE$id.png"
    }

    fun isPositiveCaptureRateDiff(currentRate:Int, evolutionRate: Int) : Pair<Int, Boolean> {
        val diff = currentRate - evolutionRate
        return Pair(diff, diff >= 0)
    }

    enum class EvolutionOrder(val position:Int) {
        First(1),
        //Second(2)
    }
}