package pojo.photo;

import lombok.Data;

@Data
public class PostPhotoDataResponse {
    
    private int server;
    private String photo;
    private String hash;
}
