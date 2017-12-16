package es.eriktorr.katas

class SocialNetworkingConsole(val clock: Clock, val commandBuilder: CommandBuilder, val timeLinePrinter: TimeLinePrinter) {

    fun submit(request: String) {
        val command = commandBuilder.from(request)
    }

}