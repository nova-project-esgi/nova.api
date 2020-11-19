package com.esgi.nova.domain.services.mapping_service

interface IMapping<In, Out> {
    fun map(obj: In): Out;
}