package com.dat.core.model.ui

data class Digit(val singleDigit: Char, val fullNumber: Long, val place: Int) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Digit -> singleDigit == other.singleDigit
            else -> super.equals(other)
        }
    }



    operator fun compareTo(other: Digit): Int {
        return fullNumber.compareTo(other.fullNumber)
    }

    override fun hashCode(): Int {
        var result = singleDigit.hashCode()
        result = 31 * result + fullNumber.hashCode()
        result = 31 * result + place
        return result
    }
}
