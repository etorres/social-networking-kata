package es.eriktorr.katas.command

import es.eriktorr.katas.command.Command.*

class CommandBuilder {

    companion object {
        private val POSTING_REGEX = Regex("""^(\w+)([ ]+->[ ]+)(.*)$""")
        private val WALL_REGEX = Regex("""^(\w+)([ ]+wall)$""")
        private val FOLLOWING_REGEX = Regex("""^(\w+)([ ]+follows[ ]+)(\w+)$""")
        private val READING_REGEX = Regex("""^(\w+)$""")
    }

    fun from(request: String): Command {
        return when {
            request.contains(" -> ") -> postMessageCommandOf(request)
            request.endsWith(" wall") -> viewAllMessagesFromSubscriptionsCommandOf(request)
            request.contains(" follows ") -> followUserCommandOf(request)
            READING_REGEX.matches(request) -> viewTimeLineCommandOf(request)
            else -> throw IllegalArgumentException("unsupported request")
        }
    }

    private fun postMessageCommandOf(request: String): PostMessageCommand {
        val groups = POSTING_REGEX.matchEntire(request)!!.groups
        return PostMessageCommand(groups[1]!!.value, groups[3]!!.value)
    }

    private fun followUserCommandOf(request: String): FollowUserCommand {
        val groups = FOLLOWING_REGEX.matchEntire(request)!!.groups
        return FollowUserCommand(groups[1]!!.value, groups[3]!!.value)
    }

    private fun viewAllMessagesFromSubscriptionsCommandOf(request: String): ViewAllMessagesFromSubscriptionsCommand {
        val groups = WALL_REGEX.matchEntire(request)!!.groups
        return ViewAllMessagesFromSubscriptionsCommand(groups[1]!!.value)
    }

    private fun viewTimeLineCommandOf(request: String): ViewTimeLineCommand = ViewTimeLineCommand(request)

}