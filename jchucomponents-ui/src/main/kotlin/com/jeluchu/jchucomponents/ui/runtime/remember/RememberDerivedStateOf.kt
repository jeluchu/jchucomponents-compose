/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

@file:Suppress("unused")

package com.jeluchu.jchucomponents.ui.runtime.remember

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.Snapshot

/**
 *
 * This is an overload for the original method.
 * If you have any questions, please see the documentation of the original method
 *
 * Remembers a [State] object whose [State.value] is the result of [calculation]. The result of
 * calculation will be cached in such a way that calling [State.value] repeatedly will not cause
 * [calculation] to be executed multiple times, but reading [State.value] will cause all [State]
 * objects that got read during the [calculation] to be read in the current [Snapshot], meaning
 * that this will correctly subscribe to the derived state objects if the value is being read in
 * an observed context such as a [Composable] function.
 * Derived states without mutation policy trigger updates on each dependency change. To avoid
 * invalidation on update, provide suitable [SnapshotMutationPolicy] through [derivedStateOf]
 * overload.
 *
 * @param calculation the calculation to create the value this state object represents.
 */
@Composable
fun <T> rememberDerivedStateOf(
    key1: Any?,
    calculation: () -> T,
): State<T> = remember(key1) {
    derivedStateOf(calculation)
}

/**
 *
 * This is an overload for the original method.
 * If you have any questions, please see the documentation of the original method
 *
 * Remembers a [State] object whose [State.value] is the result of [calculation]. The result of
 * calculation will be cached in such a way that calling [State.value] repeatedly will not cause
 * [calculation] to be executed multiple times, but reading [State.value] will cause all [State]
 * objects that got read during the [calculation] to be read in the current [Snapshot], meaning
 * that this will correctly subscribe to the derived state objects if the value is being read in
 * an observed context such as a [Composable] function.
 * Derived states without mutation policy trigger updates on each dependency change. To avoid
 * invalidation on update, provide suitable [SnapshotMutationPolicy] through [derivedStateOf]
 * overload.
 *
 * @param calculation the calculation to create the value this state object represents.
 */
@Composable
fun <T> rememberDerivedStateOf(
    key1: Any?,
    key2: Any?,
    calculation: () -> T,
): State<T> = remember(key1, key2) {
    derivedStateOf(calculation)
}

/**
 *
 * This is an overload for the original method.
 * If you have any questions, please see the documentation of the original method
 *
 * Remembers a [State] object whose [State.value] is the result of [calculation]. The result of
 * calculation will be cached in such a way that calling [State.value] repeatedly will not cause
 * [calculation] to be executed multiple times, but reading [State.value] will cause all [State]
 * objects that got read during the [calculation] to be read in the current [Snapshot], meaning
 * that this will correctly subscribe to the derived state objects if the value is being read in
 * an observed context such as a [Composable] function.
 * Derived states without mutation policy trigger updates on each dependency change. To avoid
 * invalidation on update, provide suitable [SnapshotMutationPolicy] through [derivedStateOf]
 * overload.
 *
 * @param calculation the calculation to create the value this state object represents.
 */
@Composable
fun <T> rememberDerivedStateOf(
    key1: Any?,
    key2: Any?,
    key3: Any?,
    calculation: () -> T,
): State<T> = remember(key1, key2, key3) { derivedStateOf(calculation) }

/**
 *
 * This is an overload for the original method.
 * If you have any questions, please see the documentation of the original method
 *
 * Remembers a [State] object whose [State.value] is the result of [calculation]. The result of
 * calculation will be cached in such a way that calling [State.value] repeatedly will not cause
 * [calculation] to be executed multiple times, but reading [State.value] will cause all [State]
 * objects that got read during the [calculation] to be read in the current [Snapshot], meaning
 * that this will correctly subscribe to the derived state objects if the value is being read in
 * an observed context such as a [Composable] function.
 * Derived states without mutation policy trigger updates on each dependency change. To avoid
 * invalidation on update, provide suitable [SnapshotMutationPolicy] through [derivedStateOf]
 * overload.
 *
 * @param calculation the calculation to create the value this state object represents.
 */

@Composable
fun <T> rememberDerivedStateOf(
    vararg keys: Any?,
    calculation: () -> T,
): State<T> = remember(keys) { derivedStateOf(calculation) }