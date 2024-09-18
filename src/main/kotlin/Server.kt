package io.github.josephsimutis

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.terminal
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.int
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class Server : CliktCommand() {
    val port by argument(help="The port of the server").int()
    lateinit var server: ServerSocket
    val clients = ArrayList<ClientHandler>()

    override fun run() {
        server = ServerSocket(port)
        terminal.println("Server started on port $port!")
        while (!server.isClosed) {
            val client = ClientHandler(server.accept(), this)
            clients += client
            Thread(client).start()
            terminal.println("Client connected!")
        }
        clients.forEach {
            it.socket.close()
        }
    }

    class ClientHandler(val socket: Socket, val server: Server) : Runnable {
        override fun run() {
            val stream = socket.getInputStream().bufferedReader()
            var open = true
            while (open) {
                val message = stream.readLine()
                if (message == null || message.trim() == "") open = false
                else {
                    for (receiver in server.clients) {
                        PrintWriter(receiver.socket.getOutputStream()).write(message)
                    }
                    server.terminal.println(message)
                }
            }
            server.terminal.println("Client disconnected!")
        }
    }
}