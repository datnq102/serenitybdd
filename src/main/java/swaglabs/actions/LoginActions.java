package swaglabs.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import swaglabs.model.User;
import swaglabs.pageobjects.LoginForm;

public class LoginActions extends UIInteractionSteps {

    @Step("Login as {0}")
    public void as(User user) {
        open();
        $(LoginForm.USER_NAME).sendKeys(user.getUsername());
        $(LoginForm.PASSWORD).sendKeys(user.getPassword());
        $(LoginForm.LOGIN_BUTTON).click();
    }
}
