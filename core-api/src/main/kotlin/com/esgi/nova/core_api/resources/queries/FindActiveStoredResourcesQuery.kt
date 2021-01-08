package com.esgi.nova.core_api.resources.queries

import com.esgi.nova.core_api.resources.ResourceIdentifier

data class FindActiveStoredResourcesQuery (val ids: List<ResourceIdentifier>) {
}