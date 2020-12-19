package com.esgi.nova.file_manager.application.init_treatment

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class InitTreatmentCommand(@TargetAggregateIdentifier val id: UUID = UUID.randomUUID())
