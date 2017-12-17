package es.eriktorr.katas.command

import es.eriktorr.katas.command.Command.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class CommandBuilderTest {

    companion object {
        private val CHARLIE = "Charlie"
        private val ALICE = "Alice"
        private val MESSAGE = "I'm in New York today! Anyone wants to have a coffee?"
    }

    private val commandBuilder = CommandBuilder()

    @TestFactory
    fun `create command from request`() = listOf(
            "$CHARLIE -> $MESSAGE" to PostMessageCommand(CHARLIE, MESSAGE),
            "$CHARLIE follows $ALICE" to FollowUserCommand(CHARLIE, ALICE),
            "$CHARLIE wall" to ViewAllPostsFromSubscriptionsCommand(CHARLIE),
            ALICE to ViewPostsCommand(ALICE))
            .map { (request, command) ->
                DynamicTest.dynamicTest("from($request) => $command") {
                    assertThat(commandBuilder.from(request)).isEqualTo(command)
                }
            }

}