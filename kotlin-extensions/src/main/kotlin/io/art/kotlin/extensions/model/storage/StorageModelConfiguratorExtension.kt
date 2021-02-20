package io.art.kotlin.extensions.model.storage

import io.art.model.configurator.StorageModelConfigurator
import io.art.model.configurator.TarantoolStorageModelConfigurator
import kotlin.reflect.KClass

class StorageModelConfiguratorExtension(val delegate: StorageModelConfigurator) :StorageModelConfigurator() {
    inline fun <reified T, reified K> tarantool(space: String, noinline configurator: TarantoolStorageModelConfigurator.() -> Unit = {}) =
            tarantool(space, T::class, K::class, configurator)

    inline fun <reified T, reified K> tarantool(space: String, vararg configurators: TarantoolStorageModelConfigurator.() -> Unit) =
            tarantool(space, T::class, K::class, *configurators)





    fun tarantool(space:String, spaceModelClass: KClass<*>, primaryKeyClass: KClass<*>, configurator: TarantoolStorageModelConfigurator.() -> Unit = {}) =
            delegate.tarantool(space, spaceModelClass.java, primaryKeyClass.java, { tarantool -> tarantool.apply(configurator) })

    fun tarantool(space:String, spaceModelClass: KClass<*>, primaryKeyClass: KClass<*>, vararg configurators: TarantoolStorageModelConfigurator.() -> Unit) =
            configurators.forEach { configurator -> tarantool(space, spaceModelClass, primaryKeyClass, configurator) }


}