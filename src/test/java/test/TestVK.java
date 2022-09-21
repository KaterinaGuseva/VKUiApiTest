package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import pojo.photo.GetUploadServerResponse;
import pojo.photo.PostPhotoDataResponse;
import pojo.photo.SaveUploadFileResponse;
import pojo.wall_comment.WallCommentResponse;
import pojo.wall_post.WallPostResponse;
import utils.APIutils.SideBarButtonType;
import utils.APIutils.VkApiUtils;
import configuration.SettingsVkConfig;
import constants.PhotoConstants;
import configuration.RegistrationConfig;
import utils.random_generators.RandomText;
import constants.TestConstants;

public class TestVK extends BaseTest {
    
    @Test
    public static void testWallPostVk() {

        LoginPage loginPage = new LoginPage();
        loginPage.signIn(RegistrationConfig.getLogin());
        PasswordPage passwordPage = new PasswordPage();
        passwordPage.enterPassword(RegistrationConfig.getPassword());
        SideBarBox sideBarBox = new SideBarBox();
        sideBarBox.clickSideBarButton(SideBarButtonType.SIDE_BAR_BUTTON_MY_PROFILE.getTypeParameterSideBar(), 
                SideBarButtonType.SIDE_BAR_BUTTON_NAME.getTypeParameterSideBar());
        String randomMessage = RandomText.getRandomString();
        int ownerId = SettingsVkConfig.getOwnerId();
        WallPostResponse wallPostResponse = VkApiUtils.executeWallPost(ownerId, randomMessage);
        UserPage userPage = new UserPage();
        WallPostBox wallPostBox = userPage.getWallPostBox();
        int postId = wallPostResponse.getResponse().getPostId();
        Assert.assertEquals(wallPostBox.getAuthorIdAndIdWallPost(ownerId, postId),
                String.format("wpt%d_%d", ownerId, postId), 
                "User id and post id from the post on the wall should match " +
                        "the data sent in the request");
        Assert.assertEquals(wallPostBox.getTextFromPost(ownerId, postId), randomMessage,
                "The message from the post on the wall should match" +
                        " the message sent in the request");

        GetUploadServerResponse getUploadServerResponse = VkApiUtils.getWallUploadServer(ownerId);
        PostPhotoDataResponse postPhotoDataResponse = VkApiUtils.postPhotoUploadServer
                (getUploadServerResponse.getResponse().getUploadUrl(), 
                        PhotoConstants.UPLOAD_PHOTO_PATH);
        SaveUploadFileResponse saveUploadFileResponse = VkApiUtils.saveUploadWallFile(ownerId, 
                postPhotoDataResponse.getPhoto(), postPhotoDataResponse.getServer(), 
                postPhotoDataResponse.getHash());
        String newRandomMessage = RandomText.getRandomString();
        int photoId = saveUploadFileResponse.getResponse().get(PhotoConstants.INDEX_ID_PHOTO).getId();
        VkApiUtils.executeEditWallPost(ownerId, postId, newRandomMessage, 
                PhotoConstants.API_PARAMETER_PHOTO, ownerId, photoId);
        Assert.assertEquals(wallPostBox.getTextFromPost(ownerId, postId), newRandomMessage, 
                "The message from the post on the wall should match " +
                        "the message sent in the edited request");
        Assert.assertEquals(wallPostBox.getIdPhotoWallPost(ownerId, postId),
                String.format("/photo%d_%d", ownerId, photoId), 
                "Photo id from the comment post on the wall should match" +
                        " the id sent in the request");
        
        WallCommentResponse wallCommentResponse = VkApiUtils.executeWallComment(ownerId, postId, 
                RandomText.getRandomString());
        wallPostBox.clickShowCommentWall();
        WallCommentBox wallCommentBox = userPage.getWallCommentBox();
        int commentId = wallCommentResponse.getResponse().getCommentId();
        Assert.assertEquals(wallCommentBox.getAuthorIdAndIdCommentWallPost(ownerId, commentId),
                String.format("wpt%d_%d", ownerId, commentId),
                "User id and comment id from the comment post on the wall should match" +
                        " the data sent in the request");
        
        wallPostBox.clickLikePost(ownerId, postId);
        Assert.assertEquals(VkApiUtils.checkIsLikePost(ownerId, postId).getResponse().getLiked(),
                TestConstants.MARK_LIKE_POST, 
                String.format("The post should be liked by the user with id = %d", ownerId));
        
        VkApiUtils.deleteWallPost(ownerId, postId);
        Assert.assertTrue(wallPostBox.isDisplayedWallPost(ownerId, postId), 
                String.format("The post with id = %d should be deleted", postId));
    }
}
