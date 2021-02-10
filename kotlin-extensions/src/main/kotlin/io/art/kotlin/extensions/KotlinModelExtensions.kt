package io.art.kotlin.extensions

import io.art.model.configurator.*
import java.lang.reflect.Type
import kotlin.reflect.KClass

inline fun <reified T> module(configurator: ModuleModelConfiguratorExtension.() -> ModuleModelConfiguratorExtension) =
        configurator(ModuleModelConfiguratorExtension(T::class.java.simpleName)).delegate

inline fun Any.module(configurator: ModuleModelConfiguratorExtension.() -> ModuleModelConfiguratorExtension) =
        configurator(ModuleModelConfiguratorExtension(this::class.java.simpleName)).delegate

fun module(mainClass: KClass<*>, configurator: ModuleModelConfiguratorExtension.() -> ModuleModelConfiguratorExtension) =
        configurator(ModuleModelConfiguratorExtension(mainClass.simpleName!!)).delegate

fun module(mainClass: String, configurator: ModuleModelConfiguratorExtension.() -> ModuleModelConfiguratorExtension) =
        configurator(ModuleModelConfiguratorExtension(mainClass)).delegate

class ModuleModelConfiguratorExtension(id: String, val delegate: ModuleModelConfigurator = ModuleModelConfigurator(id)) {
    fun value(configurator: ValueModelConfiguratorExtension.() -> Unit) = delegate
            .value { value -> ValueModelConfiguratorExtension(value).apply(configurator).delegate }
            .let { this }

    fun serve(configurator: ServerModelConfiguratorExtension.() -> Unit) = delegate
            .serve { value -> ServerModelConfiguratorExtension(value).apply(configurator).delegate }
            .let { this }

    fun communicate(configurator: CommunicatorModelConfiguratorExtension.() -> Unit) = delegate
            .communicate { value -> CommunicatorModelConfiguratorExtension(value).apply(configurator).delegate }
            .let { this }

    fun configure(configurator: ConfiguratorModelConfiguratorExtension.() -> Unit) = delegate
            .configure { value -> ConfiguratorModelConfiguratorExtension(value).apply(configurator).delegate }
            .let { this }

    fun onLoad(action: () -> Unit) = delegate.onLoad(action).let { this }

    fun onUnload(action: () -> Unit) = delegate.onUnload(action).let { this }

    fun beforeReload(action: () -> Unit) = delegate.beforeReload(action).let { this }

    fun afterReload(action: () -> Unit) = delegate.afterReload(action).let { this }
}

class ValueModelConfiguratorExtension(val delegate: ValueModelConfigurator) {
    fun model(`object`: Any) = model(`object`::class)

    fun model(type: KClass<*>) = model(type.java)

    fun model(type: Type) = delegate.mapping(type).let { this }
}

class ServerModelConfiguratorExtension(val delegate: ServerModelConfigurator) {
    fun rsocket(service: Any) = rsocket(service::class)

    fun rsocket(service: KClass<*>) = delegate.rsocket(service.java).let { this }
}

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

class ConfiguratorModelConfiguratorExtension(val delegate: ConfiguratorModelConfigurator) {
    inline fun <reified T> configuration() = delegate.configuration(T::class.java)!!.let { this }

    fun configuration(model: KClass<*>) = delegate.configuration(model.java)!!.let { this }

    fun configuration(section: String, model: KClass<*>) = delegate.configuration(section, model.java).let { this }
}
