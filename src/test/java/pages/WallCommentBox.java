package pages;

import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import constants.ElementConstants;
import logger.MyLogger;

public class WallCommentBox extends Form {

    private static final String STRING_COMMENT_WAll_POST = "//div[@id='wpt%d_%d']";
    
    public WallCommentBox() {
        super(By.xpath("//div[@class='replies_wrap']"), "Profile Wall Box Comments");
    }

    public String getAuthorIdAndIdCommentWallPost(int userId, int commentId) {
        MyLogger.getMyLogger().info(getName(), "Getting comment id from the user's wall");
        ITextBox txbCommentWallPost = getElementFactory().getTextBox(By.xpath(String.format(STRING_COMMENT_WAll_POST, userId, commentId)), "Wall Comment");
        return txbCommentWallPost.getAttribute(ElementConstants.NAME_PROPERTIES_ATTRIBUTE_ELEMENT);
    }
}
