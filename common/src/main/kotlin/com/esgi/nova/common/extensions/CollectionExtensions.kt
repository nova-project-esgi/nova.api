package com.esgi.nova.common.extensions

fun <E> MutableList<E>.mergeDiff(otherCollection: Collection<E>, predicate: (E, E) -> Boolean) {
    for (oE in otherCollection) {
        if (this.any { e -> predicate(oE, e) }) continue
        this.add(oE)
    }
}