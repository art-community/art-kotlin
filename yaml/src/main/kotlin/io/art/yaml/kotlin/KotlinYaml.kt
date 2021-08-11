package io.art.yaml.kotlin

import io.art.meta.kotlin.definition
import io.art.meta.model.TypedObject.typed
import io.art.yaml.Yaml.yaml


inline fun <reified T> String.fromYaml(): T = yaml().reader().read(definition<T>(), this) as T
inline fun <reified T> T.toYaml(): String = yaml().writer().writeToString(typed(definition<T>(), this))
