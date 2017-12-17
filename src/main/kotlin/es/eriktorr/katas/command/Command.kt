package es.eriktorr.katas.command

sealed class Command(open val userName: String) {

    data class PostMessageCommand(override val userName: String, val message: String): Command(userName)

    data class ViewTimeLineCommand(override val userName: String): Command(userName)

    data class FollowUserCommand(override val userName: String, val followedUserName: String): Command(userName)

    data class ViewAllMessagesFromSubscriptionsCommand(override val userName: String): Command(userName)

}