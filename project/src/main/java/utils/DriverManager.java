package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverManager {

    private static WebDriver driver;
    private static String currentBrowser;


    private DriverManager() {
    }


    public static WebDriver getDriver(String browser) {
        if (browser == null || browser.isEmpty()) {
            throw new IllegalArgumentException("Browser name cannot be null or empty");
        }
        browser = browser.toLowerCase().trim();

        if (driver != null) {
            if (!browser.equals(currentBrowser)) {
                throw new IllegalStateException(
                        "Driver already initialized for '" + currentBrowser +
                                "', requested '" + browser + "'. Call DriverManager.quit() first.");
            }
            return driver;
        }

        switch (browser) {
            case "chrome" -> {
                var options = getChromeOptions();
                driver = new ChromeDriver(options);
                currentBrowser = "chrome";
            }
            case "firefox" -> {
                var options = getFirefoxOptions();
                driver = new FirefoxDriver(options);
                currentBrowser = "firefox";
            }
            default -> throw new IllegalStateException("Unexpected value: " + browser);
        }

        driver.manage().window().maximize();
        return driver;
    }

    private static FirefoxOptions getFirefoxOptions() {
        var options = new FirefoxOptions();
        options.addArguments("--start-maximized",
                "--incognito",
                "--disable-notifications",
                "--disable-popup-blocking",
                "--disable-blink-features=AutomationControlled",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-logging",
                "--disable-extensions",
                "--disable-web-security",
                "--silent",
                "--log-level=3",
                "--disable-features=PasswordLeakDetection,PasswordManagerEnabled");
        return options;
    }

    private static ChromeOptions getChromeOptions() {
        var options = new ChromeOptions();
        options.addArguments("--start-maximized",
                "--incognito",
                "--disable-notifications",
                "--disable-popup-blocking",
                "--disable-blink-features=AutomationControlled",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-logging",
                "--disable-extensions",
                "--disable-web-security",
                "--silent",
                "--log-level=3",
                "--disable-features=PasswordLeakDetection,PasswordManagerEnabled");
        return options;
    }

    public static void quit() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
                currentBrowser = null;
            }
        }
    }
}
