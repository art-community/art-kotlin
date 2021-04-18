package io.art.kotlin.extensions.model.storage

import io.art.model.configurator.StorageModelConfigurator

class StorageModelConfiguratorExtension(private val delegate: StorageModelConfigurator) :StorageModelConfigurator() {


    fun tarantool(cluster: String?, configurator: TarantoolStorageConfiguratorExtension.() -> Unit): StorageModelConfigurator =
            delegate.tarantool(cluster, { value -> TarantoolStorageConfiguratorExtension(value).apply(configurator) }) ?:
            delegate.tarantool ({ value -> TarantoolStorageConfiguratorExtension(value).apply(configurator) })

}