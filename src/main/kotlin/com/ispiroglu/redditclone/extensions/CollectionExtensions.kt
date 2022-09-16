package com.ispiroglu.redditclone.extensions

import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap


fun <K : Any, V> mutableMultiValueMapOf(vararg pairs: Pair<K, V>): MultiValueMap<K, V> {
    return LinkedMultiValueMap<K, V>().apply {
        pairs.forEach { (key, value) -> add(key, value) }
    }
}
