package com.cw.catapoke.utils

import org.junit.Test

class AppUtilTest {

    @Test
    fun `test get species id from url string`() {
        // given
        val url = "https://pokeapi.co/api/v2/pokemon-species/1/"
        val expectedId = 1
        // when
        val result = AppUtil.getId(url)
        // then
        assert(result == expectedId)
    }

    @Test
    fun `test get species id from url without id returns default value`() {
        // given
        val url = "https://pokeapi.co/api/v2/pokemon-species/"
        val expectedId = -1
        // when
        val result = AppUtil.getId(url)
        // then
        assert(result == expectedId)
    }

    @Test
    fun `test capture rate difference should returns false when difference is negative`() {
        // given
        val currentCaptureRate = 10
        val evolutionSpeciesCaptureRate = 20

        // when
        val result = AppUtil.isPositiveCaptureRateDiff(currentCaptureRate, evolutionSpeciesCaptureRate)

        // then
        assert(!result.second)
        assert(result.first == -10)
    }
}