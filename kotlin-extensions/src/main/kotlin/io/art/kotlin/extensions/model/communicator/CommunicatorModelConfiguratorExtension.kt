package io.art.kotlin.extensions.model.communicator

import io.art.model.configurator.CommunicatorModelConfigurator
import io.art.model.configurator.RsocketCommunicatorModelConfigurator
import kotlin.reflect.KClass

class CommunicatorModelConfiguratorExtension(val delegate: CommunicatorModelConfigurator) {
    fun rsocket(communicator: KClass<*>): RsocketCommunicatorModelConfiguratorExtension {
        lateinit var extension: RsocketCommunicatorModelConfiguratorExtension
        delegate.rsocket(communicator.java, { configurator ->
            configurator.apply {
                extension = RsocketCommunicatorModelConfiguratorExtension(configurator)
            }
        })
        return extension
    }

    fun rsocket(communicatorClass: KClass<*>, configurator: RsocketCommunicatorModelConfiguratorExtension.() -> Unit = {}) = delegate
            .rsocket(communicatorClass.java, { communicator -> RsocketCommunicatorModelConfiguratorExtension(communicator).apply(configurator).delegate })
            .let { this }

    class RsocketCommunicatorModelConfiguratorExtension(val delegate: RsocketCommunicatorModelConfigurator) {
        infix fun to(targetService: Any) = to(targetService::class)

        infix fun to(targetService: KClass<*>) = to(targetService.java)

        infix fun to(targetService: Class<*>) = to(targetService.simpleName)

        infix fun to(targetServiceId: String) = delegate.to(targetServiceId)
    }
}
