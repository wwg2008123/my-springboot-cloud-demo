package exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorCode {
    private int code;
    private String error;
    @JsonProperty("more_info")
    private String moreInfo;

    public ErrorCode() {
    }

    public ErrorCode(int code, String error) {
        this.code = code;
        this.error = error;
        this.moreInfo = "http://localhost:12345/" + this.code;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMoreInfo() {
        return this.moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
