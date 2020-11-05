package com.esgi.nova.ports.provided.dtos.choice.queries.translated_choice_detailed

import com.esgi.nova.ports.provided.dtos.choice.queries.TranslatedChoiceDto
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventWithoutRelationsDto
import com.esgi.nova.ports.provided.dtos.event.translated_event_detailed.TranslatedChoiceDetailedDto

class TranslatedChoiceDetailedDto(translatedChoiceDto: TranslatedChoiceDto, val event: TranslatedEventWithoutRelationsDto) :
    TranslatedChoiceDetailedDto(translatedChoiceDto) {

}