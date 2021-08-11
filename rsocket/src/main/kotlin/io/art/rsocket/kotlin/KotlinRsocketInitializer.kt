package io.art.rsocket.kotlin

import io.art.communicator.configurator.CommunicatorConfigurator
import io.art.rsocket.module.RsocketCommunicatorConfigurator
import io.art.rsocket.module.RsocketInitializer
import io.art.rsocket.module.RsocketServerConfigurator
import io.art.server.configurator.ServerConfigurator

fun <T : ServerConfigurator> RsocketInitializer.server(configurator: RsocketServerConfigurator.() -> T) = apply {
    server { delegate -> configurator(delegate) }
}

fun <T : CommunicatorConfigurator> RsocketInitializer.communicator(configurator: RsocketCommunicatorConfigurator.() -> T) = apply {
    communicator { delegate -> configurator(delegate) }
}
