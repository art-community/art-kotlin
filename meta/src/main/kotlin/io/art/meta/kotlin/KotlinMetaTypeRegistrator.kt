package io.art.meta.kotlin

import io.art.core.caster.Caster.cast
import io.art.meta.computer.TransformersComputer.computeInputTransformer
import io.art.meta.computer.TransformersComputer.computeOutputTransformer
import io.art.meta.constants.MetaConstants.MetaTypeInternalKind.SUPPLIER
import io.art.meta.constants.MetaConstants.MetaTypeInternalKind.VOID
import io.art.meta.model.MetaType
import io.art.meta.registry.CustomMetaTypeMutableRegistry
import io.art.meta.registry.CustomTransformerMutableRegistry
import io.art.meta.transformer.CustomTransformers


internal fun registerKotlinMetaTypes() {
    MetaType.builder<Unit>()
            .type(Unit::class.java)
            .internalKind(VOID)
            .build()
            .let(CustomMetaTypeMutableRegistry::register)
    MetaType.builder<Function0<*>>()
            .type(Function0::class.java)
            .internalKind(SUPPLIER)
            .build()
            .let(CustomMetaTypeMutableRegistry::register)
}

internal fun registerKotlinMetaTransformers() {
    val input = { type: MetaType<*> ->
        val parameter = type.parameters().get(0)
        Function0Transformer(cast(computeInputTransformer(parameter)))
    }
    val output = { type: MetaType<*> ->
        val parameter = type.parameters().get(0)
        Function0Transformer(cast(computeOutputTransformer(parameter)))
    }
    CustomTransformerMutableRegistry.register(Function0::class.java, CustomTransformers(input, output))
}
