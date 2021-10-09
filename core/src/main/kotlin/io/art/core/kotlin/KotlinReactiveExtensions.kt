package io.art.core.kotlin

import io.art.core.extensions.ReactiveExtensions
import reactor.core.publisher.Flux

fun <T> Flux<T>.compensate(predicate: Boolean, compensation: Flux<T>): Flux<T> =
        ReactiveExtensions.compensate(this, { predicate }, { compensation })

fun <T> Flux<T>.compensate(predicate: Boolean, compensation: (T) -> Flux<T>): Flux<T> =
        ReactiveExtensions.compensate(this, { predicate }, compensation)


fun <T> Flux<T>.compensate(predicate: (T) -> Boolean, compensation: (T) -> Flux<T>): Flux<T> =
        ReactiveExtensions.compensate(this, predicate, compensation)

fun <T> Flux<T>.compensate(predicate: (T) -> Boolean, compensation: Flux<T>): Flux<T> =
        ReactiveExtensions.compensate(this, predicate) { compensation }


fun <T> Flux<T>.compensateOnError(predicate: (Throwable) -> Boolean, compensation: (Throwable) -> Flux<T>): Flux<T> =
        ReactiveExtensions.compensateOnError(this, predicate, compensation)

fun <T> Flux<T>.compensateOnError(predicate: (Throwable) -> Boolean, compensation: Flux<T>): Flux<T> =
        ReactiveExtensions.compensateOnError(this, predicate) { compensation }


fun <T> Flux<T>.compensateOnError(predicate: Boolean, compensation: (Throwable) -> Flux<T>): Flux<T> =
        ReactiveExtensions.compensateOnError(this, { predicate }, compensation)

fun <T> Flux<T>.compensateOnError(predicate: Boolean, compensation: Flux<T>): Flux<T> =
        ReactiveExtensions.compensateOnError(this, { predicate }, { compensation })


fun <T> Flux<T>.compensateOnError(compensation: (Throwable) -> Flux<T>): Flux<T> =
        ReactiveExtensions.compensateOnError(this, compensation)

fun <T> Flux<T>.compensateOnError(compensation: Flux<T>): Flux<T> =
        ReactiveExtensions.compensateOnError(this) { compensation }
