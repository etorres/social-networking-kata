package es.eriktorr.katas.core

import es.eriktorr.katas.core.Command.*
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
            "${CHARLIE} -> ${MESSAGE}" to PostingCommand(CHARLIE, MESSAGE),
            "${CHARLIE} follows ${ALICE}" to FollowingCommand(CHARLIE, ALICE),
            "${CHARLIE} wall" to WallCommand(CHARLIE),
            ALICE to ReadingCommand(ALICE))
            .map { (request, command) ->
                DynamicTest.dynamicTest("from($request) => $command") {
                    assertThat(commandBuilder.from(request)).isEqualTo(command)
                }
            }

}