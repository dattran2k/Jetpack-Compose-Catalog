package com.dat.jetpackcomposecatalog.data.model

class NumberShow(val first: Int, val second: Int, val third: Int) {


    fun plus(): NumberShow {
        var f = first
        var s = second
        var t = third
        t++
        if (t > 9) {
            t = 0
            s++
        }
        if (s > 9) {
            s = 0
            f++
        }
        return NumberShow(f, s, t)
    }

    fun minus(): NumberShow {
        var f = first
        var s = second
        var t = third
        t--
        if (t < 0) {
            t = 9
            s--
        }
        if (s < 0) {
            s = 9
            f--
        }
        return NumberShow(f, s, t)
    }
}