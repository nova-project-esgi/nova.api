package com.esgi.nova.domain.services.service

import com.esgi.nova.ports.provided.services.ICrudService
import com.esgi.nova.ports.required.ICrudPersistence

abstract class BaseService<Id, Input, Output>(override val persistence: ICrudPersistence<Id, Input, Output>) :
        ICrudService<Id, Input, Output>,
        IGetOneService<Id, Output>,
        IGetAllService<Output>,
        ICreateService<Input, Output>,
        IGetPageService<Output>,
        IUpdateOneService<Input, Id, Output> {
}


