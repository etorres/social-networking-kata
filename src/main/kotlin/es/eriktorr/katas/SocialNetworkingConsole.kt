package es.eriktorr.katas

import es.eriktorr.katas.command.Command.*
import es.eriktorr.katas.time.Clock
import es.eriktorr.katas.command.CommandBuilder
import es.eriktorr.katas.data.SubscriptionsRepository
import es.eriktorr.katas.timeline.TimeLineEntry
import es.eriktorr.katas.timeline.TimeLinePrinter
import es.eriktorr.katas.data.TimeLineRepository
import es.eriktorr.katas.time.ElapsedTimeCalculator
import java.time.LocalDateTime

class SocialNetworkingConsole(private val clock: Clock,
                              private val commandBuilder: CommandBuilder,
                              private val timeLineRepository: TimeLineRepository,
                              private val elapsedTimeCalculator: ElapsedTimeCalculator,
                              private val timeLinePrinter: TimeLinePrinter,
                              private val subscriptionsRepository: SubscriptionsRepository) {

    fun submit(request: String) {
        val command = commandBuilder.from(request)
        when (command) {
            is PostingCommand -> postToTimeLine(userName = command.userName, message = command.message)
            is ReadingCommand -> printTimeLineOf(command.userName)
            is FollowingCommand -> followUser(userName = command.userName, followedUserName = command.followedUserName)
            is WallCommand -> printAggregatedSubscriptions(command.userName)
        }
    }

    private fun postToTimeLine(userName: String, message: String) {
        timeLineRepository.save(clock.now(), userName = userName, message = message)
    }

    private fun printTimeLineOf(userName: String) {
        val now = clock.now()
        timeLineRepository.useEntries(userName, block = { entries -> entries.forEach { printMessage(it, now) } })
    }

    private fun followUser(userName: String, followedUserName: String) {
        subscriptionsRepository.subscribe(userName = userName, followedUserName = followedUserName)
    }

    private fun printAggregatedSubscriptions(userName: String) {
        val now = clock.now()
        val subscriptions = subscriptionsRepository.subscriptionsOf(userName)
        val wall = listOf(userName, *subscriptions.toTypedArray())
        timeLineRepository.useEntries(users = *wall.toTypedArray(), block = { entries -> entries.forEach { printMessageWithUsername(it, now) } })
    }

    private fun printMessage(it: TimeLineEntry, now: LocalDateTime) {
        timeLinePrinter.print("${it.message} (${elapsedTime(it.timestamp, now)})")
    }

    private fun printMessageWithUsername(it: TimeLineEntry, now: LocalDateTime) {
        timeLinePrinter.print("${it.userName} - ${it.message} (${elapsedTime(it.timestamp, now)})")
    }

    private fun elapsedTime(postingTime: LocalDateTime, now: LocalDateTime) = elapsedTimeCalculator.timeElapsedBetween(postingTime, now)

}