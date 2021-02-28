package io.art.kotlin.extensions.model.storage

import io.art.model.configurator.StorageModelConfigurator
import io.art.model.configurator.TarantoolSpaceModelConfigurator
import io.art.model.configurator.TarantoolStorageModelConfigurator
import kotlin.reflect.KClass

class TarantoolStorageConfiguratorExtension(private val delegate: TarantoolStorageModelConfigurator) :TarantoolStorageModelConfigurator() {
    inline fun <reified T, reified K> space(space: String, noinline configurator: TarantoolSpaceModelConfigurator.() -> Unit = {}) =
            space(space, T::class, K::class, configurator)

    inline fun <reified T, reified K> space(space: String, vararg configurators: TarantoolSpaceModelConfigurator.() -> Unit) =
            space(space, T::class, K::class, *configurators)





    fun space(space:String, spaceModelClass: KClass<*>, primaryKeyClass: KClass<*>, configurator: TarantoolSpaceModelConfigurator.() -> Unit = {}) =
            delegate.space(space, spaceModelClass.java, primaryKeyClass.java, { spaceConfig -> spaceConfig.apply(configurator) })

    fun space(space:String, spaceModelClass: KClass<*>, primaryKeyClass: KClass<*>, vararg configurators: TarantoolSpaceModelConfigurator.() -> Unit) =
            configurators.forEach { configurator -> space(space, spaceModelClass, primaryKeyClass, configurator) }


}