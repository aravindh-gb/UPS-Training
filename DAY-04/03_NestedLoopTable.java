public class NestedLoopTable {
    public static void main(String[] args) {
        for (int row = 1; row <= 3; row++) {
            for (int column = 1; column <= 3; column++) {
                System.out.print((row * column) + "\t");
            }
            System.out.println();
        }
    }
}
