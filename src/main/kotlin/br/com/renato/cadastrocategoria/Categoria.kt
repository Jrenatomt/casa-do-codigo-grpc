package br.com.renato.cadastrocategoria

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class Categoria(
    @field:NotBlank @Column(nullable = false) val nome: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
