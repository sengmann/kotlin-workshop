package com.w11k.springdemo.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class BookServiceUnitTest {
    @Autowired
    private val bookService: BookService? = null

    @Test
    fun whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        val books = bookService!!.list()
        assertThat(books.size).isEqualTo(3)
    }
}
