package es.eriktorr.katas.command

sealed class Command(private val userName: String) {

    data class PostingCommand(val userName: String, val message: String): Command(userName)

    data class ReadingCommand(val userName: String): Command(userName)

    data class FollowingCommand(val userName: String, val followedUser: String): Command(userName)

    data class WallCommand(val userName: String): Command(userName)

}