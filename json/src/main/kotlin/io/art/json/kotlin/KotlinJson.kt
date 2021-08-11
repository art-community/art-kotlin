package io.art.json.kotlin

import io.art.json.Json.json
import io.art.meta.kotlin.definition
import io.art.meta.model.TypedObject.typed

inline fun <reified T> String.fromJson(): T = json().reader().read(definition<T>(), this) as T
inline fun <reified T> T.toJson(): String = json().writer().writeToString(typed(definition<T>(), this))
