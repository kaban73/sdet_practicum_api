package dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@Value
@Builder
public class EntityResponse {
    @SerializedName("addition")
    AdditionResponse addition;

    @SerializedName("id")
    int id;

    @SerializedName("important_numbers")
    List<Integer> importantNumbers;

    @SerializedName("title")
    String title;

    @SerializedName("verified")
    boolean verified;
}
