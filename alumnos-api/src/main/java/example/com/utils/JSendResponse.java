package example.com.utils;

public class JSendResponse<T> {
    private String status;
    private T data;
    private String message;

    public JSendResponse(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> JSendResponse<T> success(T data, String message) {
        return new JSendResponse<>("success", data, message);
    }

    public static <T> JSendResponse<T> fail(String message) {
        return new JSendResponse<>("fail", null, message);
    }

    public static <T> JSendResponse<T> error(String message) {
        return new JSendResponse<>("error", null, message);
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
