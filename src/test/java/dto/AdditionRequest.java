package dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionRequest {
    @SerializedName("additional_info")
    private String additionalInfo;

    @SerializedName("additional_number")
    private int additionalNumber;
}
