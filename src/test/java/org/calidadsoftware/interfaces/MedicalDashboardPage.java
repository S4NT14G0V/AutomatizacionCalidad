// Page Object that maps elements of the Medical Admin dashboard page
package org.calidadsoftware.interfaces;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class MedicalDashboardPage extends PageObject {

        // Sidebar elements
        public static final Target SIDEBAR_LOGO = Target.the("Medical Admin logo in sidebar")
                        .located(By.xpath("//h1[text()='Medical']"));

        public static final Target ADMIN_OFFICES_MENU = Target.the("Admin Consultorios menu item")
                        .located(By.xpath("//span[contains(text(),'Admin. Consultorios')]"));

        public static final Target DASHBOARD_CONTAINER = Target.the("dashboard main container")
                        .located(By.xpath(
                                        "//div[contains(@class,'flex') or contains(@class,'dashboard') or .//h1[text()='Medical']]"));

        public static final Target LOGOUT_BUTTON = Target.the("logout button")
                        .located(By.xpath("//span[contains(text(),'Cerrar Sesion')]"));

        // User information
        public static final Target USER_NAME = Target.the("logged in user name")
                        .located(By.xpath("//p[@class='text-sm font-semibold text-medical-dark truncate']"));

        public static final Target USER_EMAIL = Target.the("logged in user email")
                        .located(By.xpath("//p[@class='text-xs text-medical-gray truncate']"));

        // Office management elements
        public static final Target NEW_OFFICE_BUTTON = Target.the("new office button")
                        .located(By.xpath("//button[contains(text(),'Nuevo Consultorio')]"));

        public static final Target LOCATION_FILTER = Target.the("location filter dropdown")
                        .located(By.xpath("//button[contains(@class,'w-[200px]')][.//span[contains(text(),'sedes')]]"));

        public static final Target SPECIALTY_FILTER = Target.the("specialty filter dropdown")
                        .located(By.xpath(
                                        "//button[contains(@class,'w-[200px]')][.//span[contains(text(),'especialidades')]]"));

        public static final Target STATUS_FILTER = Target.the("status filter dropdown")
                        .located(By.xpath(
                                        "//button[contains(@class,'w-[200px]')][.//span[contains(text(),'estados')]]"));

        public static final Target OFFICE_LIST = Target.the("office list container")
                        .located(By.cssSelector(".space-y-4"));

        public static final Target OFFICE_ITEMS = Target.the("office list items")
                        .located(By.xpath("//div[contains(@class,'border-b border-medical-border')]"));

        public static final Target FIRST_OFFICE_NAME = Target.the("first office name")
                        .located(By.xpath("(//h3[@class='font-semibold text-medical-dark mb-1'])[1]"));

        public static final Target MODIFY_BUTTONS = Target.the("modify buttons")
                        .located(By.xpath("//button[contains(text(),'Modificar')]"));

        public static final Target DELETE_BUTTONS = Target.the("delete buttons")
                        .located(By.xpath("//button[.//svg[contains(@class,'lucide-trash2')]]"));

        // Office form elements (for creating/editing)
        public static final Target OFFICE_NAME_INPUT = Target.the("office name input")
                        .located(By.xpath("//input[@id='name' or @id='number']"));

        public static final Target OFFICE_SPECIALTY_SELECT = Target.the("office specialty select trigger")
                        .located(By.xpath("//button[@id='type' or @role='combobox'][preceding-sibling::label[contains(text(),'Tipo')]]"));

        public static final Target OFFICE_LOCATION_SELECT = Target.the("office location select trigger")
                        .located(By.xpath("//button[@id='location' or @role='combobox'][preceding-sibling::label[contains(text(),'Sede')]]"));

        public static final Target OFFICE_STATUS_SELECT = Target.the("office status select trigger")
                        .located(By.xpath("//button[@id='status' or @role='combobox'][preceding-sibling::label[contains(text(),'Estado')]]"));

        // Select dropdown options (visible when dropdown is opened)
        public static Target selectOptionByValue(String optionValue) {
                return Target.the("select option: " + optionValue)
                                .located(By.xpath("//select[@aria-hidden='true']//option[@value='" + optionValue + "']"));
        }

        public static final Target SAVE_OFFICE_BUTTON = Target.the("save office button")
                        .located(By.xpath("//button[contains(text(),'Guardar')]"));

        public static final Target CANCEL_BUTTON = Target.the("cancel button")
                        .located(By.xpath("//button[contains(text(),'Cancelar')]"));

        public static final Target CONFIRM_DELETE_BUTTON = Target.the("confirm delete button")
                        .located(By.xpath("//div[@role='alertdialog']//button[text()='Eliminar']"));

        public static final Target DIALOG_CONTAINER = Target.the("dialog or modal container")
                        .located(By.cssSelector("[role='dialog'], .modal, .dialog"));

        // Dynamic locators for specific offices
        public static Target officeByName(String officeName) {
                return Target.the("office with name: " + officeName)
                                .located(By.xpath("//h3[contains(text(),'Consultorio " + officeName + "')]"));
        }

        public static Target modifyButtonForOffice(String officeName) {
                return Target.the("modify button for office: " + officeName)
                                .located(By.xpath("//h3[contains(text(),'Consultorio " + officeName
                                                + "')]/parent::div/parent::div//button[contains(text(),'Modificar')]"));
        }

        public static Target deleteButtonForOffice(String officeName) {
                return Target.the("delete button for office: " + officeName)
                                .located(By.xpath("//h3[contains(text(),'Consultorio " + officeName
                                                + "')]/parent::div/parent::div//button[.//svg[contains(@class,'lucide-trash2')]]"));
        }

        public static Target officeSpecialty(String officeName) {
                return Target.the("specialty for office: " + officeName)
                                .located(By.xpath(
                                                "//h3[contains(text(),'" + officeName + "')]/following-sibling::p[1]"));
        }

        public static Target officeLocation(String officeName) {
                return Target.the("location for office: " + officeName)
                                .located(By.xpath(
                                                "//h3[contains(text(),'" + officeName + "')]/following-sibling::p[1]"));
        }
}
