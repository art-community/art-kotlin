package io.art.transport.kotlin

import io.art.core.checker.ModuleChecker.*
import io.art.json.kotlin.toJson
import io.art.yaml.kotlin.toYaml

inline fun <reified T> T.asPrettyString(): String {
    if (!withMeta()) return toString()
    if (withYaml()) return toYaml()
    if (withJson()) return toJson()
    return toString()
}
