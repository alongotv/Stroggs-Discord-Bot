package domain.usecase

import utils.FileUtils

class RemoveLocalFileUseCase {
    /** RemoveLocalFileUseCase is used for removing local files using provided [path] */
    operator fun invoke(path: String) {
        FileUtils.deleteFileFromPath(path)
    }
}
