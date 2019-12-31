package com.evendred.basicapp.extensions

import org.mockito.ArgumentCaptor
import org.mockito.Mockito

inline fun <reified T> spy(): T = Mockito.spy(T::class.java)
inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
inline fun <reified T> captor() = ArgumentCaptor.forClass(T::class.java)
