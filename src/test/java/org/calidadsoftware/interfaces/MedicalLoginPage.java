// Page Object that maps elements of the Medical Admin login page
package org.calidadsoftware.interfaces;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Page Object for the Medical Admin Login Page
 * Uses stable data-testid selectors for reliable test execution
 */
public class MedicalLoginPage extends PageObject {

        // Primary selectors using data-testid for stability
        public static final Target LOGIN_PAGE = Target.the("login page container")
                        .located(By.cssSelector("[data-testid='login-page']"));

        public static final Target LOGIN_CARD = Target.the("login card")
                        .located(By.cssSelector("[data-testid='login-card']"));

        public static final Target LOGIN_FORM = Target.the("login form")
                        .located(By.cssSelector("[data-testid='login-form']"));

        public static final Target EMAIL_INPUT = Target.the("email input field")
                        .located(By.cssSelector("[data-testid='email-input']"));

        public static final Target PASSWORD_INPUT = Target.the("password input field")
                        .located(By.cssSelector("[data-testid='password-input']"));

        public static final Target LOGIN_BUTTON = Target.the("login button")
                        .located(By.cssSelector("[data-testid='login-button']"));

        // Fallback selectors using IDs (still stable)
        public static final Target EMAIL_INPUT_BY_ID = Target.the("email input by id")
                        .located(By.id("email"));

        public static final Target PASSWORD_INPUT_BY_ID = Target.the("password input by id")
                        .located(By.id("password"));

        // Content validation selectors
        public static final Target LOGIN_TITLE = Target.the("login page title")
                        .located(By.xpath("//h3[contains(text(),'Inicio de sesión')]"));

        public static final Target LOGIN_SUBTITLE = Target.the("login page subtitle")
                        .located(By.xpath("//p[contains(text(),'para acceder al módulo')]"));

        public static final Target ERROR_ALERT = Target.the("error alert container")
                        .located(By.cssSelector("[role='alert']"));

        public static final Target ERROR_ALERT_DESCRIPTION = Target.the("error alert description")
                        .located(By.cssSelector("[role='alert'] [id*='-error']"));

        public static final Target PASSWORD_TOGGLE_BUTTON = Target.the("show/hide password button")
                        .located(By.cssSelector("button[aria-label*='contraseña']"));

        public static final Target USER_NAME_DISPLAY = Target.the("user name in dashboard")
                        .located(By.cssSelector("p.text-sm.font-semibold.text-medical-dark.truncate"));
}
