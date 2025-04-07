package dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Value
@Builder
public class AdditionRequest {
    @SerializedName("additional_info")
    String additionalInfo;

    @SerializedName("additional_number")
    int additionalNumber;
}
