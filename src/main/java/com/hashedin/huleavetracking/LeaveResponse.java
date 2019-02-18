package com.hashedin.huleavetracking;

public class LeaveResponse {
    private LeaveStatus status;
    private String response;

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public LeaveResponse(LeaveStatus status, String response) {
        this.status = status;
        this.response = response;
    }
}
