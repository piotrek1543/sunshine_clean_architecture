package com.piotrek1543.android.boilerplate.domain.test.factory

import java.util.concurrent.ThreadLocalRandom

/**
 * Factory class for data instances
 */
class DataFactory {

    companion object Factory {

        fun randomInt(): Int {
            return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
        }

        fun randomDouble(): Double {
            return randomInt().toDouble()
        }

        fun randomUuid(): String {
            return java.util.UUID.randomUUID().toString()
        }

    }

}