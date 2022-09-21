package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import logger.MyLogger;

public class PasswordPage extends Form {

    private final ITextBox txbPassword = getElementFactory().getTextBox(By.xpath("//input[@name='password']"), "TextBox Password");
    private final IButton btnContinue = getElementFactory().getButton(By.xpath("//span[@class='vkuiButton__in']"), "Button Continue");
    
    public PasswordPage() {
        super(By.xpath("//form[@class='vkc__EnterPasswordNoUserInfo__content']"), "Password Page");
    }

    public void enterPassword(String password) {
        MyLogger.getMyLogger().info(getName(), "Entering User Password");
        txbPassword.clearAndType(password);
        btnContinue.click();
    }
}
