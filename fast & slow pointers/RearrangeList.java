public class RearrangeList {

    public static void reorder(ListNode head) {
        // find the middle first
        // then inverse the last half
        // at last insert them. The original middle will be the new end node

        // 1. find the middle
        ListNode fast = head, slow = head;
        int halfLength = 0;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            halfLength++;
        }
        ListNode middle = slow;
        // 2. reverse the last half
        ListNode current = middle.next;
        ListNode parent = middle;
        while(current != null){
            ListNode nextNode = current.next;
            current.next = parent;

            parent = current;
            current = nextNode;
        }
        // now the parent is the last node

        // 3. insert the nodes
        ListNode nodeToInsert = parent;
        current = head.next;
        parent = head;
        for(int i = 0; i < halfLength; i++){
            // insert the node between parent and current
            ListNode nextInsertNode = nodeToInsert.next;
            parent.next = nodeToInsert;
            nodeToInsert.next = current;

            parent = current;
            current = current.next;
            nodeToInsert = nextInsertNode;
        }

        middle.next = null;
    }

    
    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        RearrangeList.reorder(head);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

}
