package com.evendred.basicapp.mapper

interface Mapper<A, B> {
    fun map(from: A): B
}