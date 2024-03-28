package com.godcare.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public class Response<T> {
    private final Boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    private Response(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }
    public static <T> Response<T> success(T data) {
        return new Response<>(true, data);
    }

    public static <T> Response<T> fail(T data) {
        return new Response<>(false, data);
    }
}
