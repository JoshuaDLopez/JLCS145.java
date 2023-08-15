//Joshua Lopez
//cs 145
// lab 5
// august 1st 2023
public class TowersOfHanoi {

    public static void hanoi(int n, char source, char target, char auxiliary) {
        // Base case: If there is only one disk, move it from source to target directly.
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + target);
            return;
        }

        // Recursive step: Move n-1 disks from source to auxiliary using target as an auxiliary tower.
        hanoi(n - 1, source, auxiliary, target);

        // Move the largest disk from source to target.
        System.out.println("Move disk " + n + " from " + source + " to " + target);

        // Move the n-1 disks from auxiliary to target using source as an auxiliary tower.
        hanoi(n - 1, auxiliary, target, source);
    }

    public static void main(String[] args) {
        // Example usage with 4 disks initially stacked on tower A, B, and C.
        hanoi(4, 'A', 'C', 'B');
    }
}
