package com.esgi.nova.ports.provided.dtos

interface ITranslationEntityKey<E,L>:ITranslation<L> {
    val entityId: E
}