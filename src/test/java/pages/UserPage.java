package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class UserPage extends Form {

    public UserPage() {
        super(By.xpath("//div[@id='profile']"), "User Page");
    }

    public WallPostBox getWallPostBox() {
        return new WallPostBox();
    }

    public WallCommentBox getWallCommentBox() {
        return new WallCommentBox();
    }
}
