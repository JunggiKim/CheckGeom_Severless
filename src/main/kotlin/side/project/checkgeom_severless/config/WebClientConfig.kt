package side.project.checkgeom_severless.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.HttpMessageWriter
import org.springframework.http.codec.LoggingCodecSupport
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory
import reactor.core.publisher.Mono
import java.net.http.HttpClient
import java.time.Duration


@Configuration

class WebClientConfig {

    val log: Logger = LoggerFactory.getLogger(WebClientConfig::class.java)


    @Bean
    fun webClient(): WebClient {

        // memory setting
        val exchangeStrategies = ExchangeStrategies.builder()
            .codecs {
                it.defaultCodecs().maxInMemorySize(1024 * 1024 * 50)
            }
            .build()

        // log setting
        exchangeStrategies
            .messageWriters().stream()
            .filter { obj: HttpMessageWriter<*>? ->
                LoggingCodecSupport::class.java.isInstance(
                    obj
                )
            }
            .forEach { writer: HttpMessageWriter<*> ->
                (writer as LoggingCodecSupport).isEnableLoggingRequestDetails =
                    true
            }


        val defaultUriBuilderFactory = DefaultUriBuilderFactory()
        defaultUriBuilderFactory.encodingMode = DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY

        return WebClient.builder()
            .uriBuilderFactory(defaultUriBuilderFactory)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .codecs { configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024) }
            .defaultHeader("Content-Type", "application/json")
//            .defaultHeader(  브라우저로 속여야 할 경우 주석을 풀어봅시다
//                "user-agent",
//                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36"
//            )
            .exchangeStrategies(exchangeStrategies)
            .filter(ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
                log.debug("Request: {} {}", clientRequest.method(), clientRequest.url())
                clientRequest.headers().forEach { name, values ->
                    values.forEach { value -> log.debug("{} : {}", name, value) }
                }
                Mono.just(clientRequest)
            })   // request , response Filters logging setting
            .filter(ExchangeFilterFunction.ofResponseProcessor { clientResponse ->
                clientResponse.headers().asHttpHeaders().forEach { name, values ->
                    values.forEach { value -> log.debug("{} : {}", name, value) }
                }
                Mono.just(clientResponse)
            })
            .build()
    }


//   @Bean
//    fun connectionProvider(): ConnectionProvider {
//        return ConnectionProvider.builder("http-pool")
//            .maxConnections(100) // connection pool의 갯수
//            .pendingAcquireTimeout(Duration.ofMillis(0)) // 커넥션 풀에서 커넥션을 얻기 위해 기다리는 최대 시간
//            .pendingAcquireMaxCount(-1) // 커넥션 풀에서 커넥션을 가져오는 시도 횟수 (-1: no limit)
//            .maxIdleTime(Duration.ofMillis(1000L)) // 커넥션 풀에서 idle 상태의 커넥션을 유지하는 시간
//            .build()
//    }

}