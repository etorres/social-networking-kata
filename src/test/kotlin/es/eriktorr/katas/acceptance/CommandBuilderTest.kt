package es.eriktorr.katas.acceptance

import es.eriktorr.katas.Command.*
import es.eriktorr.katas.CommandBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class CommandBuilderTest {

    companion object {
        private val CHARLIE = "Charlie"
        private val MESSAGE = "I'm in New York today! Anyone wants to have a coffee?"
    }

    private val commandBuilder = CommandBuilder()

    @TestFactory
    fun `create command from request`() = listOf(
            "$CHARLIE -> $MESSAGE" to PostingCommand(CHARLIE, MESSAGE))
            .map { (request, command) ->
                DynamicTest.dynamicTest("commandBuilder.from($request) => $command") {
                    assertThat(commandBuilder.from(request)).isEqualTo(command)
                }
            }

}