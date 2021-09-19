package io.art.transport.kotlin

import io.art.core.checker.ModuleChecker.*
import io.art.json.kotlin.toJson
import io.art.launcher.Activator
import io.art.transport.module.TransportActivator
import io.art.yaml.kotlin.toYaml

inline fun <reified T> T.toPrettyString(): String {
    if (!withMeta()) return toString()
    if (withYaml()) return toYaml()
    if (withJson()) return toJson()
    return toString()
}


fun Activator.transport() {
    module(TransportActivator.transport())
}
