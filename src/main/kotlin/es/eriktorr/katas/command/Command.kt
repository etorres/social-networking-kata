package es.eriktorr.katas.command

sealed class Command(open val userName: String) {

    data class PostMessageCommand(override val userName: String, val message: String): Command(userName)

    data class ViewUserPostsCommand(override val userName: String): Command(userName)

    data class FollowUserCommand(override val userName: String, val subscriptionUserName: String): Command(userName)

    data class ViewAllPostsFromSubscriptionsCommand(override val userName: String): Command(userName)

}