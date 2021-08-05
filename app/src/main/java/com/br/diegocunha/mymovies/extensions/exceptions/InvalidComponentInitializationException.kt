package com.br.diegocunha.mymovies.extensions.exceptions

class InvalidComponentInitializationException(vararg params: String) :
    Exception("Invalid initialization exception. This component requires parameter(s)") {

    private val requiredParams: String

    init {
        var str = ""
        params.forEach {
            str += "$it "
        }
        requiredParams = str
    }

    override val message: String?
        get() = "${super.message} $requiredParams"
}
