package es.eriktorr.katas.core

import es.eriktorr.katas.core.Command.*

class CommandBuilder {

    companion object {
        private val POSTING_REGEX = Regex("""^(\w+)([ ]+->[ ]+)(.*)$""")
        private val WALL_REGEX = Regex("""^(\w+)([ ]+wall)$""")
        private val FOLLOWING_REGEX = Regex("""^(\w+)([ ]+follows[ ]+)(\w+)$""")
        private val READING_REGEX = Regex("""^(\w+)$""")
    }

    fun from(request: String): Command {
        return when {
            request.contains(" -> ") -> posting(request)
            request.endsWith(" wall") -> wall(request)
            request.contains(" follows ") -> following(request)
            READING_REGEX.matches(request) -> reading(request)
            else -> throw IllegalArgumentException("unsupported request")
        }
    }

    private fun posting(request: String): PostingCommand {
        val groups = POSTING_REGEX.matchEntire(request)!!.groups
        return PostingCommand(groups[1]!!.value, groups[3]!!.value)
    }

    private fun following(request: String): FollowingCommand {
        val groups = FOLLOWING_REGEX.matchEntire(request)!!.groups
        return FollowingCommand(groups[1]!!.value, groups[3]!!.value)
    }

    private fun wall(request: String): WallCommand {
        val groups = WALL_REGEX.matchEntire(request)!!.groups
        return WallCommand(groups[1]!!.value)
    }

    private fun reading(request: String): ReadingCommand = ReadingCommand(request)

}