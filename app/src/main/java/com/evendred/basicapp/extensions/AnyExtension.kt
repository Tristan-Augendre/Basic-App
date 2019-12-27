package com.evendred.basicapp.extensions

import java.lang.ref.WeakReference

@Suppress("UNCHECKED_CAST")
fun <T> Any.cast(): T = this as T

fun <T> T.weak(): WeakReference<T> = WeakReference(this)