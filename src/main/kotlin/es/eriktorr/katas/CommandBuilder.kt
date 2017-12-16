package es.eriktorr.katas

import es.eriktorr.katas.Command.*

class CommandBuilder {

    companion object {
        private val POSTING_REGEX = "(\\w+)([ ]+->[ ]+)(.*)".toRegex()
    }

    fun from(request: String): Command {
        return when {
            POSTING_REGEX.matches(request) -> PostingCommand("", "")
            else -> throw IllegalArgumentException("unsupported request")
        }
    }

}