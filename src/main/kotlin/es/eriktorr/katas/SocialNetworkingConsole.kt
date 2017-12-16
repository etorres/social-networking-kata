package es.eriktorr.katas

import es.eriktorr.katas.core.Clock
import es.eriktorr.katas.core.Command.*
import es.eriktorr.katas.core.CommandBuilder
import es.eriktorr.katas.core.TimeLineEntry
import es.eriktorr.katas.core.TimeLinePrinter
import es.eriktorr.katas.data.TimeLineRepository

class SocialNetworkingConsole(private val clock: Clock,
                              private val commandBuilder: CommandBuilder,
                              private val timeLineRepository: TimeLineRepository,
                              private val timeLinePrinter: TimeLinePrinter) {

    fun submit(request: String) {
        val command = commandBuilder.from(request)
        when (command) {
            is PostingCommand -> postToTimeLine(userName = command.userName, message = command.message)
            is ReadingCommand -> printTimeLineOf(userName = command.userName)
        }
    }

    private fun postToTimeLine(userName: String, message: String) {
        timeLineRepository.save(clock.now(), userName = userName, message = message)
    }

    private fun printTimeLineOf(userName: String) {
        timeLineRepository.filterBy(userName).forEach { printTimeLineEntry(it) }
    }

    private fun printTimeLineEntry(it: TimeLineEntry) {
        timeLinePrinter.print(it.message)
    }

}