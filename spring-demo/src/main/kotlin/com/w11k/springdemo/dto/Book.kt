package com.w11k.springdemo.dto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class Book {
    @Id
    @GeneratedValue
    val id: Long? = null
    val name: String? = null
}


@Repository
interface BookRepository : JpaRepository<Book, Long?>


@Service
class BookService {
    @Autowired
    private val bookRepository: BookRepository? = null
    fun list(): List<Book> {
        return bookRepository!!.findAll()
    }
}

@RestController
@RequestMapping("/api/book")
class BookController(val bookRepository: BookRepository) {

    @GetMapping
    fun getAllBooks(): List<Book> = this.bookRepository.findAll()

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Long): Book? = this.bookRepository.getReferenceById(id)

}
