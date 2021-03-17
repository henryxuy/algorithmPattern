import java.util.List;

public class ReverseEveryKElementsElse {
    public static ListNode reverse(ListNode head, int k) {
        if (k <= 1 || head == null)
            return head;
        // reverse the k sized sub-list alternately
        boolean reverseFlag = true;

        ListNode current = head;
        ListNode parent = null;
        ListNode newHead = head;

        while(current != null){
            ListNode[] returnedNodes;          // new head and tail of the revered sub-list
            if(reverseFlag){
                returnedNodes = reversePart(current, k);
                // link the new head and tail
                if(current == head){
                    newHead = returnedNodes[0];
                }
                else{
                    parent.next = returnedNodes[0];
                }
                current.next = returnedNodes[1];
                parent = returnedNodes[0];
                current = returnedNodes[1];

                reverseFlag = false;
            }
            else{
                // go through k nodes
                for(int i = 0; i < k && current != null; i++){
                    parent = current;
                    current = current.next;
                }
                reverseFlag = true;
            }
        }

        return newHead;
    }

    // iteratively reverse the part of the linkedList with given length
    private static ListNode[] reversePart(ListNode head, int k){
        // reverse the [head, head + k - 1] (k elements in total), return the new head, 
        // and the originally next node of new head

        ListNode current = head.next;
        ListNode parent = head;
        for(int i = 0; i < k - 1 && current != null; i++){
            ListNode nextNode = current.next;
            current.next = parent;

            parent = current;
            current = nextNode;
        }

        return new ListNode[] { parent, current };
    }



    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
    
        ListNode result = ReverseEveryKElementsElse.reverse(head, 3);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
    
}
