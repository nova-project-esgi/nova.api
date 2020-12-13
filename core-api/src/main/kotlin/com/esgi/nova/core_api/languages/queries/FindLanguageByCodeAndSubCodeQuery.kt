package com.esgi.nova.core_api.languages.queries

data class FindLanguageByCodeAndSubCodeQuery(val code: String, val subCode: String?) {
}