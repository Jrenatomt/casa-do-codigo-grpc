package br.com.renato.cadastroautor

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity(name = "tb_autor_grpc")
class Autor(
    @field:NotBlank @Column(nullable = false) val nome: String,
    @field:NotBlank @Column(nullable = false) @field:Email val email: String,
    @field:NotBlank @Column(nullable = false) val descricao: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    val criadoEm: LocalDateTime = LocalDateTime.now()
}