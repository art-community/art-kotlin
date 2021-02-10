package io.art.kotlin.extensions.model.server

import io.art.model.configurator.HttpServiceModelConfigurator
import io.art.model.configurator.RsocketServiceModelConfigurator
import io.art.model.configurator.ServerModelConfigurator
import kotlin.reflect.KClass

class ServerModelConfiguratorExtension(val delegate: ServerModelConfigurator) : ServerModelConfigurator() {
    inline fun <reified T> rsocket(noinline configurator: RsocketServiceModelConfigurator.() -> Unit = {}) = rsocket(T::class, configurator)

    inline fun <reified T> rsocket(vararg configurators: RsocketServiceModelConfigurator.() -> Unit) = rsocket(T::class, *configurators)

    fun rsocket(service: Any, configurator: RsocketServiceModelConfigurator.() -> Unit = {}) = rsocket(service::class, configurator)

    fun rsocket(service: Any, vararg configurators: RsocketServiceModelConfigurator.() -> Unit) = rsocket(service::class, *configurators)

    fun rsocket(service: KClass<*>, configurator: RsocketServiceModelConfigurator.() -> Unit = {}) = delegate.rsocket(service.java, { rsocket -> rsocket.apply(configurator) })

    fun rsocket(service: KClass<*>, vararg configurators: RsocketServiceModelConfigurator.() -> Unit) = configurators.forEach { configurator -> rsocket(service, configurator) }

    inline fun <reified T> http(noinline configurator: HttpServiceModelConfigurator.() -> Unit = {}) = http(T::class, configurator)

    inline fun <reified T> http(vararg configurators: HttpServiceModelConfigurator.() -> Unit) = http(T::class, *configurators)

    fun http(service: Any, configurator: HttpServiceModelConfigurator.() -> Unit = {}) = http(service::class, configurator)

    fun http(service: Any, vararg configurators: HttpServiceModelConfigurator.() -> Unit) = http(service::class, *configurators)

    fun http(service: KClass<*>, configurator: HttpServiceModelConfigurator.() -> Unit = {}) = delegate.http(service.java, { http -> http.apply(configurator) })

    fun http(service: KClass<*>, vararg configurators: HttpServiceModelConfigurator.() -> Unit) = configurators.forEach { configurator -> http(service, configurator) }
}
