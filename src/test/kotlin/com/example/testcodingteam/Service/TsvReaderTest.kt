package com.example.testcodingteam.Service

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TsvReaderTest {
    private val tsvReader: TsvReader = TsvReader()

    @Test
    fun testCount() {
        var path = this.javaClass.classLoader.getResource("test.tsv").toURI();
        var result = tsvReader.readTsvFile(path);
        assertEquals(result.size, 7237)
    }
}