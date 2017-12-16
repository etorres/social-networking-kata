package es.eriktorr.katas

class SocialNetworkingConsole(val clock: Clock, val commandParser: CommandParser, val timeLinePrinter: TimeLinePrinter) {

    fun submit(request: String) {
        val command = commandParser.from(request)
    }

}