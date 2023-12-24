package io.plagov.testautomationexample.mobile;

import com.codeborne.selenide.WebDriverProvider;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

@ParametersAreNonnullByDefault
public class DriverConfiguration implements WebDriverProvider {

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setPlatformName("Android");
        options.setPlatformVersion("14.0");
        options.setDeviceName("Android Emulator");
        options.setNewCommandTimeout(Duration.ofSeconds(11));
        options.setFullReset(false);
        options.autoGrantPermissions();
        options.setAppPackage("fi.hsl.app");
        options.setApp(getApp().getAbsolutePath());

        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File getApp() {
        URL apk = Thread.currentThread().getContextClassLoader().getResource("hsl.apk");
        if (apk == null) {
            throw new RuntimeException("APK file is not found under resources");
        }
        return new File(apk.getFile());
    }
}
