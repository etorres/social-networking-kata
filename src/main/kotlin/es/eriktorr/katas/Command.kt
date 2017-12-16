package es.eriktorr.katas

sealed class Command(private val userName: String) {

    data class PostingCommand(private val userName: String, private val message: String): Command(userName)

    data class ReadingCommand(private val userName: String): Command(userName)

    data class FollowingCommand(private val userName: String, private val followedUser: String): Command(userName)

    data class WallCommand(private val userName: String): Command(userName)

}