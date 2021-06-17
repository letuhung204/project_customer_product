

package com.rfx.api.model;

/**
 * Created by hubinotech on 03/04/20.
 */
public class SuccessResponse {

    String status = "";
    int statusCode = 0;
    String message = "";

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
