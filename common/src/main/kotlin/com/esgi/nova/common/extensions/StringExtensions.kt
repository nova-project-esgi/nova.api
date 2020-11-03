package com.esgi.nova.common.extensions

fun String.toNegativeRegex(): Regex = Regex("^(?!${this}).*\$")