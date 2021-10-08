package io.art.core.kotlin

import io.art.core.extensions.ReactiveExtensions
import reactor.core.publisher.Flux

fun <T> Flux<T>.compensate(predicate: (T) -> Boolean, compensation: (T) -> Flux<T>): Flux<T> =
        ReactiveExtensions.compensate(this, predicate, compensation)

fun <T> Flux<T>.compensateOnError(predicate: (Throwable) -> Boolean, compensation: (Throwable) -> Flux<T>): Flux<T> =
        ReactiveExtensions.compensateOnError(this, predicate, compensation)
