package pojo.photo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveFileResponse {

    @JsonProperty("album_id")
    private int albumId;
    private int date;
    private int id;
    @JsonProperty("owner_id")
    private int ownerId;
}
