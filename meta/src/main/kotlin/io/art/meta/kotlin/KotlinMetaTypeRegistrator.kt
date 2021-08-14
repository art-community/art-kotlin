package io.art.meta.kotlin

import io.art.core.caster.Caster.cast
import io.art.meta.computer.TransformersComputer.computeInputTransformer
import io.art.meta.computer.TransformersComputer.computeOutputTransformer
import io.art.meta.constants.MetaConstants.MetaTypeInternalKind.*
import io.art.meta.model.MetaType
import io.art.meta.registry.CustomMetaTypeMutableRegistry
import io.art.meta.registry.CustomMetaTransformerMutableRegistry
import io.art.meta.transformer.CustomTransformers


internal fun registerKotlinMetaTypes() {
    MetaType.builder<Unit>()
            .type(Unit::class.java)
            .internalKind(VOID)
            .build()
            .let(CustomMetaTypeMutableRegistry::register)
    MetaType.builder<Nothing>()
            .type(Nothing::class.java)
            .internalKind(VOID)
            .build()
            .let(CustomMetaTypeMutableRegistry::register)
    MetaType.builder<Function0<*>>()
            .type(Function0::class.java)
            .internalKind(SUPPLIER)
            .build()
            .let(CustomMetaTypeMutableRegistry::register)
    MetaType.builder<Sequence<*>>()
            .type(Sequence::class.java)
            .internalKind(STREAM)
            .build()
            .let(CustomMetaTypeMutableRegistry::register)
    MetaType.builder<Lazy<*>>()
            .type(Lazy::class.java)
            .internalKind(LAZY)
            .build()
            .let(CustomMetaTypeMutableRegistry::register)
}

internal fun registerKotlinMetaTransformers() {
    registerFunctionTransformer()
    registerLazyTransformer()
    registerSequenceTransformer()
}

private fun registerFunctionTransformer() {
    val input = { type: MetaType<*> ->
        val parameter = type.parameters().get(0)
        KotlinFunctionTransformer(cast(computeInputTransformer(parameter)))
    }
    val output = { type: MetaType<*> ->
        val parameter = type.parameters().get(0)
        KotlinFunctionTransformer(cast(computeOutputTransformer(parameter)))
    }
    CustomMetaTransformerMutableRegistry.register(Function0::class.java, CustomTransformers(input, output))
}

private fun registerLazyTransformer() {
    val input = { type: MetaType<*> ->
        val parameter = type.parameters().get(0)
        KotlinLazyTransformer(cast(computeInputTransformer(parameter)))
    }
    val output = { type: MetaType<*> ->
        val parameter = type.parameters().get(0)
        KotlinLazyTransformer(cast(computeOutputTransformer(parameter)))
    }
    CustomMetaTransformerMutableRegistry.register(Lazy::class.java, CustomTransformers(input, output))
}

private fun registerSequenceTransformer() {
    val input = { _: MetaType<*> -> KotlinSequenceTransformer }
    val output = { _: MetaType<*> -> KotlinSequenceTransformer }
    CustomMetaTransformerMutableRegistry.register(Sequence::class.java, CustomTransformers(input, output))
}
