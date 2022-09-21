package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import constants.ElementConstants;
import constants.PhotoConstants;
import logger.MyLogger;

public class WallPostBox extends Form {
    
    private static final String STRING_WALL_POST = "//div[@id='wpt%d_%d']";
    private static final String STRING_TEXT_WALL_POST = "//div[@id='wpt%d_%d']//div[contains(@class,'wall_post_text')]";
    private static final String STRING_PHOTO_WALL_POST = "//div[@id='wpt%d_%d']//div//a";
    private static final String STRING_BTN_LIKE = "//div[@class='like_wrap _like_wall%d_%d ']//div//div//div[@class='PostBottomActionContainer PostButtonReactionsContainer']";
    private final ITextBox txbTextShowComment = getElementFactory().getTextBox(By.xpath("//span[@class='js-replies_next_label']"), "Show Comment Wall");
   
    public WallPostBox() {
        super(By.xpath("//div[@class='wall_module']"), "Profile Wall");
    }
    
    public String getAuthorIdAndIdWallPost(int userId, int postId) {
        MyLogger.getMyLogger().info(getName(), "Getting post wall data from the user's wall");
        ITextBox txbWallPost = getElementFactory().getTextBox(By.xpath(String.format(STRING_WALL_POST, userId, postId)), "Wall Post");
        txbWallPost.getJsActions().scrollToTheCenter();
        return txbWallPost.getAttribute(ElementConstants.NAME_PROPERTIES_ATTRIBUTE_ELEMENT);
    }
    
    public String getTextFromPost(int userId, int postId) {
        MyLogger.getMyLogger().info(getName(), "Getting text from the post wall");
        ITextBox txbTextWallPost = getElementFactory().getTextBox(By.xpath(String.format(STRING_TEXT_WALL_POST, userId, postId)), "Text Wall Post");
        return txbTextWallPost.getText();
    }

    public String getIdPhotoWallPost(int userId, int photoId) {
        MyLogger.getMyLogger().info(getName(), "Getting id photo on the wall post");
        ITextBox txbPhoto = getElementFactory().getTextBox(By.xpath(String.format(STRING_PHOTO_WALL_POST, userId, photoId)), "Photo Wall Post");
        return txbPhoto.getAttribute(PhotoConstants.NAME_PROPERTIES_ATTRIBUTE_PHOTO);
    }
  
    public void clickShowCommentWall() {
        MyLogger.getMyLogger().info(getName(), "Opening comments");
        txbTextShowComment.click();
    }
   
    public void clickLikePost(int userId, int postId) {
        MyLogger.getMyLogger().info(getName(), "Clicking Like Button");
        IButton btnLike = getElementFactory().getButton(By.xpath(String.format(STRING_BTN_LIKE, userId, postId)), "Button Like");
        btnLike.click();
    }
    
    public boolean isDisplayedWallPost(int userId, int postId) {
        ITextBox txbWallPost = getElementFactory().getTextBox(By.xpath(String.format(STRING_WALL_POST, userId, postId)), "Wall Post");
        return txbWallPost.state().waitForDisplayed();
    }
}
