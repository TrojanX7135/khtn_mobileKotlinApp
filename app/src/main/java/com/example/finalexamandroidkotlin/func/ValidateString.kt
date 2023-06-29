package com.example.finalexamandroidkotlin.func

object ValidateString {
    fun isNull(vararg arr: String): Boolean {
        for (i in 0 until arr.size) {
            if (arr[i].isEmpty()) return true
        }

        return false
    }

    fun isEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

    fun isPassword(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{11,}\$")
        return passwordRegex.matches(password)
    }
}