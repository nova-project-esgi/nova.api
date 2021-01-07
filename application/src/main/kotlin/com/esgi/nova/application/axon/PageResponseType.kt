package com.esgi.nova.application.axon

import com.esgi.nova.core_api.pagination.PageBase
import org.axonframework.messaging.responsetypes.AbstractResponseType
import org.axonframework.messaging.responsetypes.ResponseType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class PageResponseType<R>(expectedResponseType: Class<*>?) : AbstractResponseType<PageBase<R>>(
    expectedResponseType
) {
    override fun matches(responseType: Type?): Boolean {
        responseType?.let { type ->
            return isParameterizedType(type) &&
                    isParameterizedTypeOfExpectedType(type) &&
                    isIPage(type)
        }
        return false
    }

    override fun responseMessagePayloadType(): Class<PageBase<R>> {
        return expectedResponseType as Class<PageBase<R>>
    }

    private fun isIPage(responseType: Type): Boolean {
        val rawType = (responseType as ParameterizedType).rawType as Class<*>
        return rawType.isAssignableFrom(PageBase::class.java)
    }

    companion object {
        fun <R> pageOf(type: Class<R>): ResponseType<PageBase<R>> = PageResponseType(type)
    }
}