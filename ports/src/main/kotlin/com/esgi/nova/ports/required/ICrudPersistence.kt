package com.esgi.nova.ports.required

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne

interface ICrudPersistence<Id, InputDto, OutputDto>:
        IGetOne<Id,OutputDto>,
        ICreate<InputDto, OutputDto>,
        IGetAllTotal<OutputDto>,
        IUpdateOne<InputDto, Id, OutputDto>,
        IGetAll<OutputDto>
