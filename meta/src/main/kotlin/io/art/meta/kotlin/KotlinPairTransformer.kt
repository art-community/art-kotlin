package io.art.meta.kotlin

import io.art.meta.transformer.MetaTransformer

object KotlinPairTransformer : MetaTransformer<Pair<*, *>> {
    override fun toMap(value: Pair<*, *>): MutableMap<*, *> = mutableMapOf(value)

    override fun fromMap(value: MutableMap<*, *>): Pair<*, *> = value.entries.first().toPair()
}
