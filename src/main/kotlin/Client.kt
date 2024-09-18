package io.github.josephsimutis

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.terminal
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.mordant.terminal.ConversionResult
import com.github.ajalt.mordant.terminal.prompt
import java.net.Socket
import java.util.Scanner

class Client : CliktCommand() {
    val address by argument(help="The address of the server")
    val port by argument(help="The port of the server").int()

    override fun run() {
        val client = Socket(address, port)
        val receiveThread = Thread {
            val stream = client.getInputStream().bufferedReader()
            while(!client.isClosed) {
                terminal.println(stream.readLine())
            }
        }
        receiveThread.start()
        val stream = client.getOutputStream().bufferedWriter()
        val scanner = Scanner(System.`in`)
        while(!client.isClosed) {
            terminal.prompt("> ", promptSuffix = "") { message ->
                stream.write(message)
                ConversionResult.Valid(message)
            }
        }
    }
}