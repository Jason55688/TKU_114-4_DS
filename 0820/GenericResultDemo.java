public class GenericResultDemo {

    public static void main(String[] args) {

        // 成功案例：String
        Result<String> loginResult =
                new Result<>(
                        true,
                        "登入成功",
                        "Amy");

        // 成功案例：Integer
        Result<Integer> scoreResult =
                new Result<>(
                        true,
                        "成績查詢成功",
                        95);

        // 失敗案例
        Result<String> failResult =
                new Result<>(
                        false,
                        "查詢失敗",
                        null);

        System.out.println("=== Login Result ===");
        System.out.println(loginResult);

        String userName =
                loginResult.getData();

        System.out.println(
                "User = " + userName);

        System.out.println();

        System.out.println("=== Score Result ===");
        System.out.println(scoreResult);

        Integer score =
                scoreResult.getData();

        System.out.println(
                "Score = " + score);

        System.out.println();

        System.out.println("=== Fail Result ===");
        System.out.println(failResult);

        if (failResult.getData() == null) {

            System.out.println(
                    "Data is null");
        }
    }
}

// Generic Class
class Result<T> {

    private boolean success;
    private String message;
    private T data;

    public Result(
            boolean success,
            String message,
            T data) {

        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {

        return "Result{"
                + "success=" + success
                + ", message='"
                + message + '\''
                + ", data="
                + data
                + '}';
    }
}