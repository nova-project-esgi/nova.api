package com.esgi.nova.application.axon

import com.esgi.nova.core_api.pagination.PageBase
import org.axonframework.queryhandling.QueryGateway
import java.util.concurrent.CompletableFuture


inline fun <reified R, reified Q> QueryGateway.queryPage(query: Q): CompletableFuture<PageBase<R>>
= query(query, PageResponseType.pageOf(R::class.java))
