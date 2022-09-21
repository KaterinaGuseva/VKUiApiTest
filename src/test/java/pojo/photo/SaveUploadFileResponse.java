package pojo.photo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveUploadFileResponse {

   public ArrayList<SaveFileResponse> response;
}
