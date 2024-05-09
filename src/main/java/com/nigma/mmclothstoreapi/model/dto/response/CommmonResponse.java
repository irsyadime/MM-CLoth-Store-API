package com.nigma.mmclothstoreapi.model.dto.response;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommmonResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;
}
