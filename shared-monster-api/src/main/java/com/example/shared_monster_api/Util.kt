package com.example.shared_monster_api

import com.example.shared_monster_api.domain.entity.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException

fun <Entity, ResponseEntity> runApiCall(
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO),
    toEntity: ResponseEntity.() -> Entity,
    query: suspend () -> Response<ResponseEntity>,
): Flow<Result<Entity>> =
    flow {
        emit(Result.Loading())
        val deferredResponse = coroutineScope.async { query() }
        try {
            val response = deferredResponse.await()
            val responseBody = response.body()
            when {
                response.isSuccessful && responseBody != null -> {
                    emit(Result.Success(responseBody.toEntity()))
                }
                /*response.isSuccessful && responseBody == null && response.code() == Result.Error.Forbidden.RESPONSE_CODE-> {
                    Result.Error.Forbidden<T>("forbidden")
                }
                 response.isSuccessful && responseBody == null && response.code() == kotlin.Result.Error.Unauthorized.RESPONSE_CODE-> {
                     kotlin.Result.Error.Unauthorized<T>("unauthorized")
                 }
                 response.isSuccessful && responseBody == null && response.code() == kotlin.Result.Error.NotFound.RESPONSE_CODE-> {
                     kotlin.Result.Error.NotFound<T>("not found")
                 }*/
                else -> emit(Result.Failure("что то пошло не так"))
            }
        } catch (e: HttpException) {
            emit(Result.Error(exception = e, message = "http error"))
        } catch (e: ConnectException) {
            emit(Result.Error(exception = e, message = "connection error"))
        }
    }
