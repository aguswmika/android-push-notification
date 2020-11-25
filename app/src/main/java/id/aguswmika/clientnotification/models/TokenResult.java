package id.aguswmika.clientnotification.models;

import com.google.gson.annotations.SerializedName;

public class TokenResult {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
