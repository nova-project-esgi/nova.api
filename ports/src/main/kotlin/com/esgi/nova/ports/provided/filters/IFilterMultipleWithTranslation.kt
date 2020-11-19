package com.esgi.nova.ports.provided.filters

import com.esgi.nova.ports.provided.dtos.ITranslation

interface IFilterMultipleWithTranslation<T,L>: IFilterMultiple<T>, ITranslation<L>