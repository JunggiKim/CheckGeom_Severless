package side.project.checkgeom_severless.util

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

object WebDriverUtil {
    fun createWebDriver(): WebDriver {
        WebDriverManager.chromedriver().setup()
        return ChromeDriver(createOptions())
    }

    fun createWebDriverWait(webDriver: WebDriver?): WebDriverWait {
        return WebDriverWait(webDriver, Duration.ofSeconds(2))
    }

    private fun createOptions(): ChromeOptions {
        val chromeOptions = ChromeOptions()
        chromeOptions.addArguments("--disable-popup-blocking") // 팝업안띄움
        chromeOptions.addArguments("--headless") // 브라우저 안띄움
        chromeOptions.addArguments("--disable-gpu") // gpu 비활성화
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false") // 이미지 로딩을 실행하지 않음
        chromeOptions.addArguments("--mute-audio") // 음소거 옵션
        chromeOptions.addArguments("--no-sandbox")
        chromeOptions.addArguments("--disable-dev-shm-usage")

        return chromeOptions
    }
}
