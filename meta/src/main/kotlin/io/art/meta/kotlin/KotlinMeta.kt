package io.art.meta.kotlin

import io.art.core.module.ModuleActivator
import io.art.launcher.Activator
import io.art.meta.Meta
import io.art.meta.Meta.declaration
import io.art.meta.model.MetaClass
import io.art.meta.model.MetaLibrary
import io.art.meta.model.MetaType
import io.art.meta.model.TypeReference
import io.art.meta.module.MetaActivator
import io.art.meta.module.MetaInitializer


inline fun <reified T> declaration(): MetaClass<T> = declaration(T::class.java)

inline fun <reified T> definition(): MetaType<T> = Meta.definition(object : TypeReference<T>() {}.type)

object KotlinMetaActivator {
    fun <T : MetaLibrary> meta(factory: () -> T): ModuleActivator {
        return MetaActivator.meta(factory) { initializer ->
            initializer
                    .registerKotlinMetaTypes()
                    .registerKotlinMetaTransformers()
        }
    }

    fun <T : MetaLibrary> meta(factory: () -> T, initializer: (MetaInitializer) -> MetaInitializer): ModuleActivator {
        return MetaActivator.meta(factory) { current ->
            initializer(current.registerKotlinMetaTypes().registerKotlinMetaTransformers())
        }
    }
}


fun <T : MetaLibrary> Activator.meta(factory: () -> T) {
    module(KotlinMetaActivator.meta(factory))
}

fun <T : MetaLibrary> Activator.meta(factory: () -> T, initializer: (MetaInitializer) -> MetaInitializer) {
    module(KotlinMetaActivator.meta(factory, initializer))
}
