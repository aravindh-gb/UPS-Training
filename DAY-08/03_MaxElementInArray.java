public class MaxElementInArray {
    public static void main(String[] args) {
        int[] values = {5, 18, 2, 41, 9};
        int max = values[0];
        for (int value : values) if (value > max) max = value;
        System.out.println("Maximum value: " + max);
    }
}
