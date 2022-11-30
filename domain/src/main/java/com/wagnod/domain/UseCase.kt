package com.wagnod.domain

interface UseCase<Input, Output> {
    suspend fun execute(input: Input): Output
}

suspend fun <T> UseCase<Unit, T>.execute(): T = execute(Unit)