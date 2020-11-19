package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.*
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination

interface ICrudService<Id, Input, Output>:
        IGetOne<Id, Output>,
        IGetAll<Output>,
        ICreate<Input, Output>,
        IGetPage<Output>,
        IUpdateOne<Input, Id, Output>{
}