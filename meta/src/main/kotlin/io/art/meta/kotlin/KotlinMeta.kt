package io.art.meta.kotlin

import io.art.core.module.ModuleActivator
import io.art.meta.Meta.declaration
import io.art.meta.model.MetaClass
import io.art.meta.model.MetaLibrary
import io.art.meta.model.MetaType
import io.art.meta.module.MetaActivator
import io.art.meta.module.MetaInitializer

inline fun <reified T> declaration(): MetaClass<T> = declaration(T::class.java)

inline fun <reified T> definition(): MetaType<T> = declaration<T>().definition()


object KotlinMetaActivator {
    fun <T : MetaLibrary> meta(factory: () -> T): ModuleActivator {
        return MetaActivator.meta {
            registerKotlinMetaTypes()
            registerKotlinMetaTransformers()
            factory()
        }
    }

    fun <T : MetaLibrary> meta(factory: () -> T, initializer: (current: MetaInitializer) -> MetaInitializer): ModuleActivator {
        val metaFactory: () -> MetaLibrary = {
            registerKotlinMetaTypes()
            registerKotlinMetaTransformers()
            factory()
        }
        return MetaActivator.meta(metaFactory, initializer)
    }
}
