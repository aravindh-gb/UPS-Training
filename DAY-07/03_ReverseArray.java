import java.util.Arrays;
public class ReverseArray {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};
        for (int left = 0, right = numbers.length - 1; left < right; left++, right--) {
            int temp = numbers[left]; numbers[left] = numbers[right]; numbers[right] = temp;
        }
        System.out.println(Arrays.toString(numbers));
    }
}
