package com.esgi.nova.domain.services.mapping_service

import com.esgi.nova.ports.provided.services.ICrudService
import com.esgi.nova.ports.required.ICrudPersistence

abstract class BaseMappingService<Id, Output, Input, MappableOutput>
(override val persistence: ICrudPersistence<Id, Input, Output>) :
        IMappingGetAllService<Output, MappableOutput>,
        IMappingGetOneService<Id, Output, MappableOutput>,
        IMappingCreateService<Input, Output, MappableOutput>,
        IMappingGetPageService<Output, MappableOutput>,
        IMappingUpdateService<Input, Id, Output, MappableOutput>,
        IMapping<Output, MappableOutput>,
        ICrudService<Id, Input, MappableOutput> {
    override val outputMapper by lazy{this}

}