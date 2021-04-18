package io.art.kotlin.extensions.model.storage

import io.art.model.configurator.TarantoolSpaceModelConfigurator
import io.art.model.configurator.TarantoolStorageModelConfigurator

class TarantoolStorageConfiguratorExtension(private val delegate: TarantoolStorageModelConfigurator) :TarantoolStorageModelConfigurator() {
    inline fun <reified T, reified K> space(space: String, noinline configurator: TarantoolSpaceModelConfigurator<T, K>.() -> Unit = {}) =
            space(space, T::class.java, K::class.java, configurator)

    inline fun <reified T, reified K> space(space: String, vararg configurators: TarantoolSpaceModelConfigurator<T, K>.() -> Unit) =
            space(space, T::class.java, K::class.java, *configurators)


    fun <T, K> space(space: String, spaceModelClass: Class<T>, primaryKeyClass: Class<K>, configurator: TarantoolSpaceModelConfigurator<T, K>.() -> Unit = {}): TarantoolStorageModelConfigurator =
            delegate.space(space, spaceModelClass, primaryKeyClass, { spaceConfig -> spaceConfig.apply(configurator) })

    fun <T, K> space(space: String, spaceModelClass: Class<T>, primaryKeyClass: Class<K>, vararg configurators: TarantoolSpaceModelConfigurator<T, K>.() -> Unit) =
            configurators.forEach { configurator -> space(space, spaceModelClass, primaryKeyClass, configurator) }

}