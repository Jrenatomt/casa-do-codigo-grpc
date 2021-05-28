package br.com.renato.cadastroautor

import br.com.renato.NovoAutorRequest
import br.com.renato.exception.ErrorMessage

fun NovoAutorRequest.toModel(): Autor {
    return Autor(
        nome = nome,
        email = email,
        descricao = descricao
    )
}

fun NovoAutorRequest.valida(): ErrorMessage? {

    val emailValido = "[A-Za-z0-9_-]+@[A-Za-z]+\\.[A-Za-z]+".toRegex()

    if (nome.isBlank()) return ErrorMessage("O nome deve ser informado")
    if (email.isBlank()) return ErrorMessage("O e-mail deve ser informado")
    if (!email.matches(emailValido)) return ErrorMessage("E-mail no formato inválido")
    if (descricao.isBlank()) return ErrorMessage("Descrição Obrigatoria")

    return null
}
