package io.art.meta.kotlin

import io.art.core.caster.Caster.cast
import io.art.core.collection.ImmutableLazyArrayImplementation
import io.art.core.factory.ArrayFactory.immutableLazyArrayOf
import io.art.meta.transformer.MetaTransformer

object KotlinSequenceTransformer : MetaTransformer<Sequence<*>> {
    override fun fromArray(value: List<*>): Sequence<*> = value.asSequence()

    override fun toArray(value: Sequence<*>): List<*> = value.toList()

    override fun fromLazyArray(value: ImmutableLazyArrayImplementation<*>): Sequence<*> = value.asSequence()

    override fun toLazyArray(value: Sequence<*>): ImmutableLazyArrayImplementation<*> {
        val list = value.toList()
        return cast(immutableLazyArrayOf(list) { element -> element })
    }
}
