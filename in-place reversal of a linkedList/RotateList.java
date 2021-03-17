public class RotateList {

    public static ListNode rotate(ListNode head, int rotations) {
        int length = 0;
        ListNode current = head;
        while(current != null){
            length++;
            current = current.next;
        }

        ListNode parent = null;
        current = head;
        int rotationCount = rotations % length;
        int skipCount = length - rotationCount;
        // skip some meaningless nodes in the beginning
        for(int i = 0; i < skipCount; i++){
            parent = current;
            current = current.next;
        }
        // link the new head and tail
        ListNode newHead = current;
        parent.next = null;
        for(int i = 0; i < rotationCount - 1; i++){
            current = current.next;
        }
        current.next = head;

        return newHead;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
    
        ListNode result = RotateList.rotate(head, 3);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
    
}
