package com.themoviedatabase.android.domain.usecases.sigining

import com.themoviedatabase.android.data.model.auth.SigningRequestParams
import com.themoviedatabase.android.di.distpacher.IoDispatcher
import com.themoviedatabase.android.domain.model.auth.exception.SigningException
import com.themoviedatabase.core.domain.flow.FlowUseCase
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ValidateFormSigingUseCase @Inject constructor(@IoDispatcher dispatcher: CoroutineDispatcher): FlowUseCase<SigningRequestParams, Boolean>(dispatcher) {

    @ExperimentalCoroutinesApi
    override fun execute(parameters: SigningRequestParams): Flow<MDBResult<Boolean>> {
       return flow {
           validateObligatoryFields(parameters).onStart {
               emit(MDBResult.Loading)
           }.collect {
               if (it is MDBResult.Success) {
                   validateUser(parameters).collect { validateResultUser->
                       if (validateResultUser is MDBResult.Success) {
                           validatePassword(parameters.password).collect { result ->
                               emit(result)
                           }
                       } else if(validateResultUser is MDBResult.Error){
                           emit(MDBResult.Error(validateResultUser.exception))
                       }
                   }
               } else if(it is MDBResult.Error){
                   emit(MDBResult.Error(it.exception))
               }
           }
       }
    }

    private fun validateUser(parameters: SigningRequestParams): Flow<MDBResult<Boolean>> {
        return flow {
            if (parameters.user.isEmpty()) {
                emit(MDBResult.Error(SigningException(SigningException.USER_IS_EMPTY)))
            } else {
               emit(MDBResult.Success(true))
            }
        }
    }
    private fun validatePassword(password: String): Flow<MDBResult<Boolean>> {
        return flow {
            when {
                password.isEmpty() -> {
                    emit(MDBResult.Error(SigningException(SigningException.PASSWORD_IS_EMPTY)))
                }
                password.length < 4 -> {
                    emit(MDBResult.Error(SigningException(SigningException.PASSWORD_TO_SHORT)))
                }
                else -> {
                    emit(MDBResult.Success(true))
                }
            }
        }
    }

    private fun validateObligatoryFields(parameters: SigningRequestParams): Flow<MDBResult<Boolean>> {
        return flow {
            if(parameters.user.isEmpty() && parameters.password.isEmpty()) {
                emit(MDBResult.Error(SigningException(SigningException.ALL_FIELDS_EMPTY)))
            }else {
                emit(MDBResult.Success(true))
            }
        }
    }
}