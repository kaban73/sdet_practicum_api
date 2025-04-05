package dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@Value
@Builder
public class EntityRequest {
    @SerializedName("addition")
    @Builder.Default
    AdditionRequest addition = null;

    @SerializedName("important_numbers")
    @Builder.Default
    List<Integer> importantNumbers = List.of(1, 2, 3);

    @SerializedName("title")
    @Builder.Default
    String title = "default_title";

    @SerializedName("verified")
    @Builder.Default
    boolean verified = true;
}
