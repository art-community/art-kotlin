package io.art.kotlin.extensions.model.communicator

import io.art.model.configurator.CommunicatorModelConfigurator
import io.art.model.configurator.RsocketCommunicatorModelConfigurator
import kotlin.reflect.KClass

class CommunicatorModelConfiguratorExtension(val delegate: CommunicatorModelConfigurator) : CommunicatorModelConfigurator() {
    inline fun <reified T> rsocket(configurator: RsocketCommunicatorModelConfiguratorExtension.() -> Unit = {}) = rsocket(T::class)
            .apply(configurator)

    fun rsocket(communicator: Any, configurator: RsocketCommunicatorModelConfiguratorExtension.() -> Unit = {}) = rsocket(communicator::class)
            .apply(configurator)

    fun rsocket(communicator: KClass<*>, configurator: RsocketCommunicatorModelConfiguratorExtension.() -> Unit = {}) = rsocket(communicator)
            .apply(configurator)

    fun rsocket(communicator: KClass<*>): RsocketCommunicatorModelConfiguratorExtension {
        lateinit var extension: RsocketCommunicatorModelConfiguratorExtension
        delegate.rsocket(communicator.java, { configurator ->
            configurator.apply {
                extension = RsocketCommunicatorModelConfiguratorExtension(configurator)
            }
        })
        return extension
    }

}

class RsocketCommunicatorModelConfiguratorExtension(private val delegate: RsocketCommunicatorModelConfigurator) {
    inline fun <reified T> to() = to(T::class)

    infix fun to(targetService: Any) = to(targetService::class).let { this }

    infix fun to(targetService: KClass<*>) = to(targetService.java).let { this }

    infix fun to(targetService: Class<*>) = to(targetService.simpleName).let { this }

    infix fun to(targetServiceId: String) = delegate.to(targetServiceId).let { this }

    infix fun id(id: String) = delegate.id(id).let { this }
}
