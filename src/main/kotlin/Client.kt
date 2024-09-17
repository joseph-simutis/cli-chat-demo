package io.github.josephsimutis

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.int
import java.net.Socket

class Client : CliktCommand() {
    val address by argument(help="The address of the server")
    val port by argument(help="The port of the server").int()

    override fun run() {
        val client = Socket(address, port)
        client.getInputStream()
    }
}