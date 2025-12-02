// Page Object that maps elements of the Medical Admin dashboard page
package org.calidadsoftware.interfaces;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Page Object for the Medical Admin Dashboard
 * Uses stable data-testid selectors and structured locators for reliable test execution
 */
public class MedicalDashboardPage extends PageObject {

        // Main dashboard container - primary validation target
        public static final Target DASHBOARD_CONTAINER = Target.the("dashboard main container")
                        .located(By.cssSelector("[data-testid='clinics-dashboard']"));

        // Sidebar elements
        public static final Target SIDEBAR_LOGO = Target.the("Medical Admin logo in sidebar")
                        .located(By.xpath("//h1[text()='Medical']"));

        public static final Target ADMIN_OFFICES_MENU = Target.the("Admin Consultorios menu item")
                        .located(By.xpath("//span[contains(text(),'Admin. Consultorios')]"));

        public static final Target LOGOUT_BUTTON = Target.the("logout button")
                        .located(By.cssSelector("[data-testid='logout-button']"));

        // User information
        public static final Target USER_NAME = Target.the("logged in user name")
                        .located(By.cssSelector("p.text-sm.font-semibold.text-medical-dark.truncate"));

        public static final Target USER_EMAIL = Target.the("logged in user email")
                        .located(By.cssSelector("p.text-xs.text-medical-gray.truncate"));

        // Office management - primary actions
        public static final Target NEW_OFFICE_BUTTON = Target.the("new office button")
                        .located(By.cssSelector("[data-testid='new-office-button']"));

        // Filter dropdowns
        public static final Target LOCATION_FILTER = Target.the("location filter dropdown")
                        .located(By.xpath("//button[contains(@class,'w-[200px]')][.//span[contains(text(),'sedes')]]"));

        public static final Target SPECIALTY_FILTER = Target.the("specialty filter dropdown")
                        .located(By.xpath(
                                        "//button[contains(@class,'w-[200px]')][.//span[contains(text(),'especialidades')]]"));

        public static final Target STATUS_FILTER = Target.the("status filter dropdown")
                        .located(By.xpath(
                                        "//button[contains(@class,'w-[200px]')][.//span[contains(text(),'estados')]]"));

        // Office list
        public static final Target OFFICE_LIST = Target.the("office list container")
                        .located(By.cssSelector(".space-y-4"));

        public static final Target OFFICE_ITEMS = Target.the("office list items")
                        .located(By.cssSelector("[data-testid^='office-item-']"));

        public static final Target FIRST_OFFICE_NAME = Target.the("first office name")
                        .located(By.xpath("(//h3[@class='font-semibold text-medical-dark mb-1'])[1]"));

        // Office form elements - registration form (uses stable IDs)
        public static final String OFFICE_NAME_INPUT_ID = "name";
        public static final String OFFICE_SPECIALTY_SELECT_ID = "type";
        public static final String OFFICE_LOCATION_SELECT_ID = "sede";
        public static final String OFFICE_STATUS_SELECT_ID = "status";

        public static final Target OFFICE_NAME_INPUT = Target.the("office name input")
                        .located(By.cssSelector("[data-testid='office-name-input']"));

        public static final Target OFFICE_SPECIALTY_SELECT = Target.the("office specialty select trigger")
                        .located(By.cssSelector("[data-testid='office-specialty-select']"));

        public static final Target OFFICE_LOCATION_SELECT = Target.the("office location select trigger")
                        .located(By.cssSelector("[data-testid='office-location-select']"));

        public static final Target OFFICE_STATUS_SELECT = Target.the("office status select trigger")
                        .located(By.cssSelector("[data-testid='office-status-select']"));

        // Dialog/Modal elements
        public static final Target DIALOG_CONTAINER = Target.the("dialog or modal container")
                        .located(By.cssSelector("[role='dialog']"));

        public static final Target ALERT_DIALOG_CONTAINER = Target.the("alert dialog container")
                        .located(By.cssSelector("[role='alertdialog']"));

        public static final Target REGISTRATION_FORM = Target.the("clinic registration form")
                        .located(By.cssSelector("[data-testid='clinic-registration-form']"));

        public static final Target EDIT_DIALOG = Target.the("edit office dialog")
                        .located(By.cssSelector("[data-testid='edit-office-dialog']"));

        public static final Target DELETE_DIALOG = Target.the("delete office dialog")
                        .located(By.cssSelector("[data-testid='delete-office-dialog']"));

        // Form buttons - using data-testid for reliability
        public static final Target SAVE_OFFICE_BUTTON = Target.the("save office button")
                        .located(By.cssSelector("[data-testid='save-office-button']"));

        public static final Target CANCEL_BUTTON = Target.the("cancel button")
                        .located(By.cssSelector("[data-testid='cancel-button']"));

        public static final Target EDIT_SAVE_BUTTON = Target.the("edit save button")
                        .located(By.cssSelector("[data-testid='edit-save-button']"));

        public static final Target EDIT_CANCEL_BUTTON = Target.the("edit cancel button")
                        .located(By.cssSelector("[data-testid='edit-cancel-button']"));

        public static final Target CONFIRM_DELETE_BUTTON = Target.the("confirm delete button")
                        .located(By.cssSelector("[data-testid='delete-confirm-button']"));

        public static final Target CANCEL_DELETE_BUTTON = Target.the("cancel delete button")
                        .located(By.cssSelector("[data-testid='delete-cancel-button']"));

        // Edit form inputs
        public static final Target EDIT_OFFICE_NAME_INPUT = Target.the("edit office name input")
                        .located(By.cssSelector("[data-testid='edit-office-name-input']"));

        public static final Target EDIT_OFFICE_SPECIALTY_SELECT = Target.the("edit office specialty select")
                        .located(By.cssSelector("[data-testid='edit-office-specialty-select']"));

        public static final Target EDIT_OFFICE_LOCATION_SELECT = Target.the("edit office location select")
                        .located(By.cssSelector("[data-testid='edit-office-location-select']"));

        public static final Target EDIT_OFFICE_STATUS_SELECT = Target.the("edit office status select")
                        .located(By.cssSelector("[data-testid='edit-office-status-select']"));

        // Dynamic locators for specific offices using data-testid
        public static Target officeByName(String officeName) {
                return Target.the("office item with name: " + officeName)
                                .located(By.cssSelector("[data-testid='office-item-" + officeName + "']"));
        }

        public static Target modifyButtonForOffice(String officeName) {
                return Target.the("modify button for office: " + officeName)
                                .located(By.cssSelector("[data-testid='modify-button-" + officeName + "']"));
        }

        public static Target deleteButtonForOffice(String officeName) {
                return Target.the("delete button for office: " + officeName)
                                .located(By.cssSelector("[data-testid='delete-button-" + officeName + "']"));
        }

        public static Target officeSpecialty(String officeName) {
                return Target.the("specialty for office: " + officeName)
                                .located(By.xpath(
                                                "//div[@data-testid='office-item-" + officeName + "']//p[contains(@class,'text-sm')]"));
        }

        public static Target officeLocation(String officeName) {
                return Target.the("location for office: " + officeName)
                                .located(By.xpath(
                                                "//div[@data-testid='office-item-" + officeName + "']//p[contains(@class,'text-sm')]"));
        }

        /**
         * Find office by its visible name/heading text (not data-testid)
         * This is more reliable since frontend uses office number IDs in data-testid
         */
        public static Target officeByNameText(String officeName) {
                return Target.the("office with visible name: " + officeName)
                                .located(By.xpath("//h3[contains(@class,'font-semibold') and contains(text(),'" + officeName + "')]"));
        }
}
