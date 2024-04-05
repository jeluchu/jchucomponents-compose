package com.jeluchu.jchucomponents.ktx.utils

object IbanUtils {
    private const val REG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private const val STRING_DOUBLE_ZERO = "00"
    private const val STRING_ZERO = "0"
    private const val INT_NINETY_EIGHT = 98
    private const val INT_NINETY_SEVEN = 97

    fun getIban(account: String): String{
        val auxAccount = account.uppercase().trim()
        var isBanAux = "ES$STRING_DOUBLE_ZERO$auxAccount"
        val letter1 = isBanAux.substring(0, 1)
        val letter2 = isBanAux.substring(1, 2)
        val num1 = getNumIban(letter1) //14
        val num2 = getNumIban(letter2) //28
        isBanAux = num1.toString() + num2.toString() + isBanAux.substring(2)
        isBanAux = isBanAux.substring(6) + isBanAux.substring(0, 6)
        val digControl = INT_NINETY_EIGHT - mod97(isBanAux)
        return if (digControl < 10) STRING_ZERO + digControl.toString() else digControl.toString()
    }

    private fun mod97(digit: String): Int{
        var m = 0
        for (element in digit) {
            m = (m * 10 + element.toString().toInt()) % INT_NINETY_SEVEN
        }
        return m
    }

    private fun getNumIban(letter: String) = REG.indexOf(letter) + 10
}
