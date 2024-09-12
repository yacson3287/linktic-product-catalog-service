package com.linktic.product_catalog_service.domain.exceptions;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ExceptionDetail {
    private String message;
    private Map<String, Object> details;

    public void addDetail(String key, Object message) {
        details.put(key, message);
    }

    public ExceptionDetail(String message) {
        this.message = message;
        details = new HashMap<>();
    }

    public String getJson() {
        return new Gson().toJson(this);
    }

}
