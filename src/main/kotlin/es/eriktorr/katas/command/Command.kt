package es.eriktorr.katas.command

sealed class Command(open val userName: String) {

    data class PostingCommand(override val userName: String, val message: String): Command(userName)

    data class ReadingCommand(override val userName: String): Command(userName)

    data class FollowingCommand(override val userName: String, val followedUserName: String): Command(userName)

    data class WallCommand(override val userName: String): Command(userName)

}