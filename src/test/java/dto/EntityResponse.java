package dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityResponse {
    @SerializedName("addition")
    private AdditionResponse addition;

    @SerializedName("id")
    private int id;

    @SerializedName("important_numbers")
    private List<Integer> importantNumbers;

    @SerializedName("title")
    private String title;

    @SerializedName("verified")
    private boolean verified;
}
