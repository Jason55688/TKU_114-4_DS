public class RecursiveCallReport {

    public static int sum(int[] data, int index) {
        if (data == null || index >= data.length) {
            return 0;
        }

        int currentValue = data[index];
        int recursiveResult = sum(data, index + 1);
        int returnValue = currentValue + recursiveResult;

        System.out.println("Index: " + index + ", Current Value: " + currentValue 
                + ", Recursive Result: " + recursiveResult + ", Return Value: " + returnValue);

        return returnValue;
    }

    public static void main(String[] args) {
        System.out.println("--- 測試一般陣列 ---");
        int[] arr1 = {10, 20, 30, 40};
        System.out.println("Total: " + sum(arr1, 0));

        System.out.println("\n--- 測試單一元素 ---");
        int[] arr2 = {99};
        System.out.println("Total: " + sum(arr2, 0));

        System.out.println("\n--- 測試空陣列 ---");
        int[] arr3 = {};
        System.out.println("Total: " + sum(arr3, 0));
    }
}