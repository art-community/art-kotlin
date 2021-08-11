package io.art.meta.kotlin

import io.art.meta.Meta.declaration
import io.art.meta.model.MetaClass
import io.art.meta.model.MetaType

inline fun <reified T> declaration(): MetaClass<T> = declaration(T::class.java)

inline fun <reified T> definition(): MetaType<T> = declaration<T>().definition()
