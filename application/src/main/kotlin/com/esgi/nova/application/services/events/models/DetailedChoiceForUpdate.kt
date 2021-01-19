package com.esgi.nova.application.services.events.models

import java.util.*

class DetailedChoiceForUpdate(
    var id: UUID?,
    translations: List<ChoiceTranslationForEdition>,
    resources: List<ChoiceResourceForEdition>
) : DetailedChoiceForEdition(translations, resources)