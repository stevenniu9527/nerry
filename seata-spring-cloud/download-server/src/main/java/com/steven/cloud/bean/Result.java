package com.steven.cloud.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Result {
    private static final Result result = new Result();
    @Setter
    @Getter
    private String appName;
    @Getter
    private List<Map<String, Object>> actionResults = new ArrayList<>();

    public static Result getResult() {
        return result;
    }

    public void setActionResult(String txId, long branchId, String result, Object data) {
        Map<String, Object> action = new HashMap<>();
        action.put("xid", txId);
        action.put("branchId", String.valueOf(branchId));
        action.put("result", result);
        action.put("data", data);
        this.actionResults.add(action);
    }
}
