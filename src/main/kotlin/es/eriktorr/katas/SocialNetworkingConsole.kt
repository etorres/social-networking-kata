package es.eriktorr.katas

import es.eriktorr.katas.core.Clock
import es.eriktorr.katas.core.Command.*
import es.eriktorr.katas.core.CommandBuilder
import es.eriktorr.katas.core.TimeLinePrinter

class SocialNetworkingConsole(private val clock: Clock,
                              private val commandBuilder: CommandBuilder,
                              private val timeLinePrinter: TimeLinePrinter) {

    fun submit(request: String) {
        val command = commandBuilder.from(request)
        when (command) {
            is PostingCommand -> postToTimeLine(userName = command.userName, message = command.message)
        }
    }

    private fun postToTimeLine(userName: String, message: String) {
        TODO("not implemented")
    }

}