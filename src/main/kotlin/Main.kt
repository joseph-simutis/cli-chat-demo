package io.github.josephsimutis

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands

fun main(args: Array<String>) = CliChat()
    .subcommands(
        Client(),
        Server()
    )
    .main(args)

class CliChat : CliktCommand() {
    override fun run() = Unit
}