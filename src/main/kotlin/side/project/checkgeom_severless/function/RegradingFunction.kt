package side.project.checkgeom_severless.function

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import side.project.checkgeom_severless.controller.v1.LibraryController
import java.util.*
import java.util.function.Consumer


@Component  //설마 안되면 다시 콘피그레이션으로 변경해서 사용을 하자
class RegradingFunction(
   private val libraryController: LibraryController
) {
    @Bean
	fun uppercase(): (String) -> String {
		return { it.uppercase(Locale.getDefault()) }
	}

	@Bean
    fun postTest(): Consumer<Map<String, Any>> {
        return Consumer { map ->
            map.entries.forEach { entry ->
                println("Key: ${entry.key}, Value: ${entry.value}")
            }
        }
    }

}