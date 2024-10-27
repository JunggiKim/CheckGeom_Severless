package side.project.checkgeom_severless.support.response

import side.project.checkgeom_severless.support.error.ErrorMessage
import side.project.checkgeom_severless.support.error.ErrorType

class ApiResponse<S>  (
    val result: ResultType,
    private val data: S?,
    val error: ErrorMessage?
) {

    companion object {
        fun success(): ApiResponse<Nothing> {
            return ApiResponse(ResultType.SUCCESS, null, null)
        }

        fun <S> success(data: S): ApiResponse<S> {
            return ApiResponse(ResultType.SUCCESS, data, null)
        }

      fun error(error: ErrorType): ApiResponse<Nothing> {
            return ApiResponse(ResultType.ERROR, null, ErrorMessage(error))
        }

        fun error(error: ErrorType, errorData: Any?): ApiResponse<Nothing> {
            return ApiResponse(ResultType.ERROR, null, ErrorMessage(error, errorData))
        }
    }
}

enum class ResultType {
    SUCCESS,
    ERROR
}

class ErrorMessage(
    val errorType: ErrorType,
    val errorData: Any? = null
)
