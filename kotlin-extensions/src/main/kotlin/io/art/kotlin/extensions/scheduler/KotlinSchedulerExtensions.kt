package io.art.kotlin.extensions.scheduler

import io.art.core.callable.ExceptionCallable
import io.art.scheduler.manager.Scheduling
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.concurrent.Callable


inline fun <reified T> schedule(startTime: LocalDateTime, task: Callable<T>) = Scheduling.schedule(task, startTime)!!

inline fun <reified T> schedule(task: Callable<T>) = Scheduling.schedule(task, now())!!


inline fun <reified T> scheduleFixedRate(startTime: LocalDateTime, period: Duration, task: ExceptionCallable<out T>) = Scheduling.scheduleFixedRate(task, startTime, period)

inline fun <reified T> scheduleFixedRate(period: Duration, task: ExceptionCallable<out T>) = Scheduling.scheduleFixedRate(task, now(), period)


inline fun <reified T> scheduleDelayed(startTime: LocalDateTime, period: Duration, task: ExceptionCallable<out T>) = Scheduling.scheduleDelayed(task, startTime, period)

inline fun <reified T> scheduleDelayed(period: Duration, task: ExceptionCallable<out T>) = Scheduling.scheduleDelayed(task, now(), period)
