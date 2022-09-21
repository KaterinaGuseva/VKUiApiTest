package utils.APIutils;

public enum ApiMethodName {
    
    WALL_POST("wall.post"),
    WALL_GET_UPLOAD_SERVER("photos.getWallUploadServer"),
    WALL_SAFE_PHOTO("photos.saveWallPhoto"),
    WALL_EDIT("wall.edit"),
    WALL_CREATE_COMMENT("wall.createComment"),
    WALL_CHECK_LIKE_POST("likes.isLiked"),
    WALL_DELETE_POST("wall.delete");
    
    private String methodName;

    ApiMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }
}
