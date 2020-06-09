package irena.test.campaigns.utils;

public class BaseResponse<T> {
    private T data;
    private String message;

    public T getData() {
        return data;
    }

    public BaseResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public BaseResponse(T data) {
        this.data = data;
        this.message = "";
    }

    public BaseResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }
}
