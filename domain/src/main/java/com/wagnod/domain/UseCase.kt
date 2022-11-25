package com.wagnod.domain

interface UseCase<Input, Output> {
    suspend fun execute(input: Input): Output
}

suspend fun UseCase<Unit, Any>.execute() = execute(Unit)