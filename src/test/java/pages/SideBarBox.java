package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import logger.MyLogger;

public class SideBarBox extends Form {

    private static final String STRING_MENU_SIDE_BAR = "//li[@id='%s']";
   
    public SideBarBox() {
        super(By.xpath("//div[@class='side_bar_inner']"), "Side Bar");
    }
    
    public void clickSideBarButton(String dataOfLocator, String buttonName) {
        MyLogger.getMyLogger().info(getName(), String.format("Click Side Bar Button %s", buttonName));
        IButton btnSideBar = getElementFactory().getButton(By.xpath(String.format(STRING_MENU_SIDE_BAR, dataOfLocator)), String.format("Side Bar Button %s", buttonName));
        btnSideBar.click();
    }
}
