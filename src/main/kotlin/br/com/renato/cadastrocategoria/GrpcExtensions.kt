package br.com.renato.cadastrocategoria

import br.com.renato.NovaCategoriaRequest
import br.com.renato.NovoAutorRequest
import br.com.renato.exception.ErrorMessage

fun NovaCategoriaRequest.toModel(): Categoria {
    return Categoria(
        nome = nome
    )
}

fun NovaCategoriaRequest.valida(): ErrorMessage? {
    if (nome.isBlank()) return ErrorMessage("O nome deve ser informado")
    return null
}