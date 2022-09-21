package utils.APIutils;

public enum LikeType {
    
    POST("post");

    private String likeType;

    LikeType(String likeType) {
        this.likeType = likeType;
    }

    public String getLikeType() {
        return likeType;
    }
}
