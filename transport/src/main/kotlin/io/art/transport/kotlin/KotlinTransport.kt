package io.art.transport.kotlin

import io.art.transport.extensions.TransportExtensions.asPrettyString

fun Any.asPrettyString(): String = asPrettyString(this)
