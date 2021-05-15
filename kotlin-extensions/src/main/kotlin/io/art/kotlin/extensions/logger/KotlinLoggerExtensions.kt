package io.art.kotlin.extensions.logger

import io.art.logging.logger.Logger
import io.art.logging.module.LoggingModule.logger
import kotlin.reflect.KClass


inline fun <reified T> T.logger(): Logger = logger(T::class.java)
fun logger(contextClass: KClass<*>): Logger = logger(contextClass.java)
fun logger(contextClass: Class<*>): Logger = logger(contextClass)
fun logger(name: String): Logger = logger(name)
