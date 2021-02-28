package io.art.kotlin.extensions.model.module

import io.art.kotlin.extensions.model.communicator.CommunicatorModelConfiguratorExtension
import io.art.kotlin.extensions.model.configurator.ConfiguratorModelConfiguratorExtension
import io.art.kotlin.extensions.model.server.ServerModelConfiguratorExtension
import io.art.kotlin.extensions.model.storage.StorageModelConfiguratorExtension
import io.art.kotlin.extensions.model.value.ValueModelConfiguratorExtension
import io.art.model.configurator.ModuleModelConfigurator

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

    fun store(configurator: StorageModelConfiguratorExtension.() -> Unit) = delegate
            .store { value -> StorageModelConfiguratorExtension(value).apply(configurator) }
            .let { this }

    fun configure(configurator: ConfiguratorModelConfiguratorExtension.() -> Unit) = delegate
            .configure { value -> ConfiguratorModelConfiguratorExtension(value).apply(configurator).delegate }
            .let { this }

    fun onLoad(action: () -> Unit) = delegate.onLoad(action).let { this }

    fun onUnload(action: () -> Unit) = delegate.onUnload(action).let { this }

    fun beforeReload(action: () -> Unit) = delegate.beforeReload(action).let { this }

    fun afterReload(action: () -> Unit) = delegate.afterReload(action).let { this }
}
