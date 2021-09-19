package io.art.scheduler.kotlin

import io.art.launcher.Activator
import io.art.scheduler.module.SchedulerActivator

fun Activator.scheduler() {
    module(SchedulerActivator.scheduler())
}
