// Page Object that maps elements of the Medical Admin login page
package org.calidadsoftware.interfaces;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class MedicalLoginPage extends PageObject {

        public static final Target EMAIL_INPUT = Target.the("email input field")
                        .located(By.id("email"));

        public static final Target PASSWORD_INPUT = Target.the("password input field")
                        .located(By.id("password"));

        public static final Target PASSWORD_TOGGLE_BUTTON = Target.the("show/hide password button")
                        .located(By.cssSelector("button[aria-label*='contraseña']"));

        public static final Target LOGIN_BUTTON = Target.the("login button")
                        .located(By.cssSelector("button[type='submit']"));

        public static final Target LOGIN_TITLE = Target.the("login page title")
                        .located(By.xpath("//h3[contains(text(),'Inicio de sesión')]"));

        public static final Target LOGIN_SUBTITLE = Target.the("login page subtitle")
                        .located(By.xpath("//p[contains(text(),'para acceder al módulo')]"));

        public static final Target ERROR_MESSAGE = Target.the("error message")
                        .located(By.cssSelector(".text-destructive, [role='alert']"));

        public static final Target ERROR_ALERT = Target.the("error alert container")
                        .located(By.cssSelector(
                                        "[role='alert'].text-destructive, [role='alert'] .text-destructive, [role='alert']"));

        public static final Target ERROR_ALERT_DESCRIPTION = Target.the("error alert description")
                        .located(By.xpath("//div[@role='alert']//div[contains(@class,'text-sm')]"));

        public static final Target LOGIN_FORM = Target.the("login form")
                        .located(By.cssSelector("form"));

        public static final Target EMAIL_PLACEHOLDER = Target.the("email placeholder")
                        .located(By.cssSelector("input[placeholder*='ejemplo@medicaladmin.com']"));

        public static final Target CARD_CONTAINER = Target.the("login card container")
                        .located(By.cssSelector(".rounded-lg.border.bg-card"));

        public static final Target USER_NAME_DISPLAY = Target.the("user name in dashboard")
                        .located(By.xpath("//p[@class='text-sm font-semibold text-medical-dark truncate']"));
}
