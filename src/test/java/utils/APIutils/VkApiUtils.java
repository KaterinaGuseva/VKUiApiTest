package utils.APIutils;

import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import pojo.like_post.LikePostResponse;
import pojo.photo.GetUploadServerResponse;
import pojo.photo.PostPhotoDataResponse;
import pojo.wall_comment.WallCommentResponse;
import pojo.wall_post.WallPostResponse;
import pojo.photo.SaveUploadFileResponse;
import constants.ApiParamConstants;
import constants.PhotoConstants;

import java.io.File;

import static io.restassured.RestAssured.given;

import static org.apache.http.HttpStatus.*;

@UtilityClass
public class VkApiUtils {

    public static WallPostResponse executeWallPost(int ownerId, String message) {
        return given()
                .pathParam(ApiParamConstants.PATH_METHOD, ApiMethodName.WALL_POST.getMethodName())
                .queryParam(ApiParamConstants.PARAM_OWNER_ID, ownerId)
                .queryParam(ApiParamConstants.PARAM_MESSAGE, message)
                .when()
                .get()
                .then()
                .statusCode(SC_OK)
                .extract().as(WallPostResponse.class);
    }

    public static GetUploadServerResponse getWallUploadServer(int ownerId) {
        return given()
                .pathParam(ApiParamConstants.PATH_METHOD, 
                        ApiMethodName.WALL_GET_UPLOAD_SERVER.getMethodName())
                .queryParam(ApiParamConstants.PARAM_OWNER_ID, ownerId)
                .when()
                .get()
                .then()
                .statusCode(SC_OK)
                .extract().as(GetUploadServerResponse.class);
    }

    public static PostPhotoDataResponse postPhotoUploadServer(String uploadUrl, String path) {
        File file = new File(path);
        return   given()
                .multiPart(PhotoConstants.API_PARAMETER_PHOTO, file,
                        ContentType.MULTIPART.toString())
                .post(uploadUrl)
                .then()
                .statusCode(SC_OK)
                .extract().jsonPath().getObject(".", PostPhotoDataResponse.class);
    }
    
    public static SaveUploadFileResponse saveUploadWallFile(int userId, String photo, int server, String hash) {
        return given()
                .pathParam(ApiParamConstants.PATH_METHOD, ApiMethodName.WALL_SAFE_PHOTO.getMethodName())
                .queryParam(ApiParamConstants.PARAM_USER_ID, userId)
                .queryParam(ApiParamConstants.PARAM_PHOTO, photo)
                .queryParam(ApiParamConstants.PARAM_SERVER, server)
                .queryParam(ApiParamConstants.PARAM_HASH, hash)
                .when()
                .post()       
                .then()
                .statusCode(SC_OK)
                .extract().as(SaveUploadFileResponse.class);
    }
    
    public static void executeEditWallPost(int ownerId, int postId, String message, String typeAttachment, int ownerMediaId, int mediaId) {
               given()
                .pathParam(ApiParamConstants.PATH_METHOD, ApiMethodName.WALL_EDIT.getMethodName())
                .queryParam(ApiParamConstants.PARAM_OWNER_ID, ownerId)
                .queryParam(ApiParamConstants.PARAM_POST_ID, postId)
                .queryParam(ApiParamConstants.PARAM_MESSAGE, message)
                .queryParam(ApiParamConstants.PARAM_ATTACHMENTS, 
                        String.format("%s%d_%d", typeAttachment, ownerMediaId, mediaId))
                .when()
                .get()
                .then()
                .statusCode(SC_OK);         
    }
    
    public static WallCommentResponse executeWallComment(int ownerId, int postId, String message) {
        return given()
                .pathParam(ApiParamConstants.PATH_METHOD, ApiMethodName.WALL_CREATE_COMMENT.getMethodName())
                .queryParam(ApiParamConstants.PARAM_OWNER_ID, ownerId)
                .queryParam(ApiParamConstants.PARAM_POST_ID, postId)
                .queryParam(ApiParamConstants.PARAM_MESSAGE, message)
                .when()
                .get()
                .then()
                .statusCode(SC_OK)
                .extract().as(WallCommentResponse.class);
    }

    public static LikePostResponse checkIsLikePost(int userId, int postId) {
        return given()
                .pathParam(ApiParamConstants.PATH_METHOD, ApiMethodName.WALL_CHECK_LIKE_POST.getMethodName())
                .queryParam(ApiParamConstants.PARAM_USER_ID, userId)
                .queryParam(ApiParamConstants.PARAM_TYPE, LikeType.POST.getLikeType())
                .queryParam(ApiParamConstants.PARAM_ITEM_ID, postId)
                .when()
                .get()
                .then()
                .statusCode(SC_OK)
                .extract().as(LikePostResponse.class);
    }
    
    public static void deleteWallPost(int ownerId, int postId) {
               given()
                .pathParam(ApiParamConstants.PATH_METHOD, ApiMethodName.WALL_DELETE_POST.getMethodName())
                .queryParam(ApiParamConstants.PARAM_OWNER_ID, ownerId)
                .queryParam(ApiParamConstants.PARAM_POST_ID, postId)
                .when()
                .get()
                .then()
                .statusCode(SC_OK);
    }
}
