package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import logger.MyLogger;

public class LoginPage extends Form {

    private final ITextBox txbLogin = getElementFactory().getTextBox(By.xpath("//input[@id='index_email']"), "TextBox Login");
    private final IButton btnSignIn = getElementFactory().getButton(By.xpath("//button[@class='FlatButton FlatButton--primary FlatButton--size-l FlatButton--wide VkIdForm__button VkIdForm__signInButton']"), "Button Sign In");
   
    public LoginPage() {
        super(By.xpath("//div[@id='index_login']"), "Login Page");
    }
    
    public void signIn(String login) {
        MyLogger.getMyLogger().info(getName(), "Entering User Name");
        txbLogin.clearAndType(login);
        btnSignIn.click();
    }
}
