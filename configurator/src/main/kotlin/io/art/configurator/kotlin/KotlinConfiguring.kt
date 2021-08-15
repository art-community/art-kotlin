package io.art.configurator.kotlin

import io.art.configurator.Configuring.configuration
import io.art.core.constants.StringConstants.EMPTY_STRING

inline fun <reified T> configuration(): T = configuration<T>(EMPTY_STRING)
inline fun <reified T> configuration(section: String): T = configuration(section, T::class.java) as T
