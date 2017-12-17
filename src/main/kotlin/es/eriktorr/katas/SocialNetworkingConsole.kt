package es.eriktorr.katas

import es.eriktorr.katas.command.Command.*
import es.eriktorr.katas.time.Clock
import es.eriktorr.katas.command.CommandBuilder
import es.eriktorr.katas.data.SubscriptionsRepository
import es.eriktorr.katas.timeline.Post
import es.eriktorr.katas.timeline.PostPrinter
import es.eriktorr.katas.data.PostsRepository
import es.eriktorr.katas.time.ElapsedTimeCalculator
import java.time.LocalDateTime

class SocialNetworkingConsole(private val clock: Clock,
                              private val commandBuilder: CommandBuilder,
                              private val postsRepository: PostsRepository,
                              private val subscriptionsRepository: SubscriptionsRepository,
                              private val elapsedTimeCalculator: ElapsedTimeCalculator,
                              private val postPrinter: PostPrinter) {

    fun submit(request: String) {
        val command = commandBuilder.from(request)
        when (command) {
            is PostMessageCommand -> saveNewPost(userName = command.userName, message = command.message)
            is ViewPostsCommand -> printPostsOf(command.userName)
            is FollowUserCommand -> followUser(userName = command.userName, followedUserName = command.followedUserName)
            is ViewAllPostsFromSubscriptionsCommand -> printAggregatedPostsFromSubscriptions(command.userName)
        }
    }

    private fun saveNewPost(userName: String, message: String) {
        postsRepository.save(Post(clock.now(), userName = userName, message = message))
    }

    private fun printPostsOf(userName: String) {
        val now = clock.now()
        postsRepository.usePosts(userName, block = { entries -> entries.forEach { printMessage(it, now) } })
    }

    private fun followUser(userName: String, followedUserName: String) {
        subscriptionsRepository.subscribe(userName = userName, followedUserName = followedUserName)
    }

    private fun printAggregatedPostsFromSubscriptions(userName: String) {
        val now = clock.now()
        val subscriptions = subscriptionsRepository.subscriptionsOf(userName)
        val wall = listOf(userName, *subscriptions.toTypedArray())
        postsRepository.usePosts(users = *wall.toTypedArray(), block = { entries -> entries.forEach { printMessageWithUsername(it, now) } })
    }

    private fun printMessage(it: Post, now: LocalDateTime) {
        postPrinter.print("${it.message} (${elapsedTime(it.timestamp, now)})")
    }

    private fun printMessageWithUsername(it: Post, now: LocalDateTime) {
        postPrinter.print("${it.userName} - ${it.message} (${elapsedTime(it.timestamp, now)})")
    }

    private fun elapsedTime(postingTime: LocalDateTime, now: LocalDateTime) = elapsedTimeCalculator.timeElapsedBetween(postingTime, now)

}