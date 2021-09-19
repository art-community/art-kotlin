package io.art.json.kotlin

import io.art.json.Json
import io.art.json.module.JsonActivator
import io.art.json.module.JsonActivator.*
import io.art.launcher.Activator
import io.art.meta.kotlin.definition
import io.art.meta.model.TypedObject.typed

inline fun <reified T> String.fromJson(): T = Json.json().reader().read(definition<T>(), this) as T
inline fun <reified T> T.toJson(): String = Json.json().writer().writeToString(typed(definition<T>(), this))

fun Activator.json() {
    module(JsonActivator.json())
}
