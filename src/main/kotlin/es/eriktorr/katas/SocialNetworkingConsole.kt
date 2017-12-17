package es.eriktorr.katas

import es.eriktorr.katas.time.Clock
import es.eriktorr.katas.command.Command.PostingCommand
import es.eriktorr.katas.command.Command.ReadingCommand
import es.eriktorr.katas.command.CommandBuilder
import es.eriktorr.katas.timeline.TimeLineEntry
import es.eriktorr.katas.timeline.TimeLinePrinter
import es.eriktorr.katas.data.TimeLineRepository

class SocialNetworkingConsole(private val clock: Clock,
                              private val commandBuilder: CommandBuilder,
                              private val timeLineRepository: TimeLineRepository,
                              private val timeLinePrinter: TimeLinePrinter) {

    fun submit(request: String) {
        val command = commandBuilder.from(request)
        when (command) {
            is PostingCommand -> postToTimeLine(userName = command.userName, message = command.message)
            is ReadingCommand -> printTimeLineOf(command.userName)
        }
    }

    private fun postToTimeLine(userName: String, message: String) {
        timeLineRepository.save(clock.now(), userName = userName, message = message)
    }

    private fun printTimeLineOf(userName: String) {
        timeLineRepository.useEntries(userName, block = { entries -> entries.forEach { printTimeLineEntry(it) } })
    }

    private fun printTimeLineEntry(it: TimeLineEntry) {
        timeLinePrinter.print(it.message)
    }

}