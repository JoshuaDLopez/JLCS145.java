//joshua lopez
//july 18th
//cs 145
public class PhonebookManager {
    private ListNode head;

    public PhonebookManager() {
        this.head = null;
    }

    // Method to add an entry at the beginning of the list
    public void addEntryAtBeginning(String firstName, String lastName, String address, String city, String phoneNumber) {
        ListNode newNode = new ListNode(firstName, lastName, address, city, phoneNumber);
        newNode.next = head;
        head = newNode;
    }

    // Method to add an entry at the end of the list
    public void addEntryAtEnd(String firstName, String lastName, String address, String city, String phoneNumber) {
        ListNode newNode = new ListNode(firstName, lastName, address, city, phoneNumber);
        if (head == null) {
            head = newNode;
            return;
        }

        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Method to add an entry at a specific index (position) in the list
    public void addEntryAtIndex(int index, String firstName, String lastName, String address, String city, String phoneNumber) {
        if (index < 0) {
            System.out.println("Invalid index. Entry not added.");
            return;
        }

        if (index == 0) {
            addEntryAtBeginning(firstName, lastName, address, city, phoneNumber);
            return;
        }

        ListNode newNode = new ListNode(firstName, lastName, address, city, phoneNumber);
        ListNode current = head;
        int currentPosition = 0;

        while (current != null && currentPosition < index - 1) {
            current = current.next;
            currentPosition++;
        }

        if (current == null) {
            System.out.println("Invalid index. Entry not added.");
            return;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    // Method to delete an entry at a specific index (position) in the list
    public void deleteEntryAtIndex(int index) {
        if (head == null || index < 0) {
            System.out.println("Invalid index. Entry not deleted.");
            return;
        }

        if (index == 0) {
            head = head.next;
            return;
        }

        ListNode current = head;
        int currentPosition = 0;

        while (current.next != null && currentPosition < index - 1) {
            current = current.next;
            currentPosition++;
        }

        if (current.next == null) {
            System.out.println("Invalid index. Entry not deleted.");
            return;
        }

        current.next = current.next.next;
    }

    // Method to modify an entry at a specific index (position) in the list
    public void modifyEntryAtIndex(int index, String firstName, String lastName, String address, String city, String phoneNumber) {
        if (index < 0) {
            System.out.println("Invalid index. Entry not modified.");
            return;
        }

        ListNode current = head;
        int currentPosition = 0;

        while (current != null && currentPosition < index) {
            current = current.next;
            currentPosition++;
        }

        if (current == null) {
            System.out.println("Invalid index. Entry not modified.");
            return;
        }

        current.firstName = firstName;
        current.lastName = lastName;
        current.address = address;
        current.city = city;
        current.phoneNumber = phoneNumber;
    }

    // Method to print the phonebook entries in a nice format
    public void printPhonebook() {
        ListNode current = head;
        System.out.println("Phonebook Entries:");

        while (current != null) {
            System.out.println("Name: " + current.firstName + " " + current.lastName);
            System.out.println("Address: " + current.address);
            System.out.println("City: " + current.city);
            System.out.println("Phone Number: " + current.phoneNumber);
            System.out.println("------------------------");
            current = current.next;
        }
    }
}
