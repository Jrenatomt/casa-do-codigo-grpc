package br.com.renato.cadastrocategoria

import br.com.renato.CadastroCategoriaServiceGrpc
import br.com.renato.NovaCategoriaRequest
import br.com.renato.NovaCategoriaResponse
import br.com.renato.exception.CategoriaDuplicadaException
import br.com.renato.exception.ErrorHandler
import br.com.renato.exception.errorResponse
import io.grpc.Status
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@ErrorHandler
@Singleton
class CadastroCategoriaGrpcEndpoint(@Inject private val repository: CategoriaRepository) :
    CadastroCategoriaServiceGrpc.CadastroCategoriaServiceImplBase() {

    override fun cadastraCategoria(
        request: NovaCategoriaRequest,
        responseObserver: StreamObserver<NovaCategoriaResponse>
    ) {

        val possivelErroValidacao = request.valida()
        possivelErroValidacao?.let {
            responseObserver.errorResponse(Status.INVALID_ARGUMENT, it)
            return
        }

        if (repository.existsByNome(request.nome)){
            throw CategoriaDuplicadaException("Categoria já Cadastrada")
        }

        val categoria = request.toModel()
        repository.save(categoria)

        responseObserver.onNext(NovaCategoriaResponse
            .newBuilder()
            .setId(categoria.id.toString())
            .build())
        responseObserver.onCompleted()
    }
}