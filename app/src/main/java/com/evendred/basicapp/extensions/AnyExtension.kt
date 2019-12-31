package com.evendred.basicapp.extensions

import org.mockito.Mockito

@Suppress("UNCHECKED_CAST")
fun <T> Any.cast(): T = this as T