package br.com.renato.cadastroautor


import br.com.renato.CadastroAutorServiceGrpc
import br.com.renato.NovoAutorRequest
import br.com.renato.NovoAutorResponse
import br.com.renato.exception.EmailDuplicadoException
import br.com.renato.exception.ErrorHandler
import br.com.renato.exception.errorResponse
import io.grpc.Status
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@ErrorHandler
@Singleton
class CadastroAutorGrpcEndpoint(@Inject private val repository: AutorRepository) :
    CadastroAutorServiceGrpc.CadastroAutorServiceImplBase() {

    override fun cadastraAutor(request: NovoAutorRequest, responseObserver: StreamObserver<NovoAutorResponse>) {

        val possivelErroValidacao = request.valida()
        possivelErroValidacao?.let {
            responseObserver.errorResponse(Status.INVALID_ARGUMENT, it)
            return
        }

        if (repository.existsByEmail(request.email)){
            throw EmailDuplicadoException("E-mail já cadatrado")
        }

        val autor = request.toModel()
        repository.save(autor)

        responseObserver.onNext(NovoAutorResponse
            .newBuilder()
            .setId(autor.id.toString())
            .build())

        responseObserver.onCompleted()
    }
}




