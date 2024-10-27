package side.project.checkgeom_severless.support.error

class CoreApiException : RuntimeException {
    val errorType: ErrorType

    val data: Any?

    constructor(errorType: ErrorType) : super(errorType.message) {
        this.errorType = errorType
        this.data = null
    }

    constructor(errorType: ErrorType, data: Any?) : super(errorType.message) {
        this.errorType = errorType
        this.data = data
    }
}
