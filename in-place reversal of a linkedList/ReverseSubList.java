import java.util.*;

class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

class ReverseSubList {

    public static ListNode reverse(ListNode head, int p, int q) {
        // reverse the linkedList from p to q
        ListNode current = head;
        ListNode previous = null;
        ListNode predecessor = null;
        ListNode successor = null;
        ListNode temp = null;
        int i = 0;

        for(; current != null && i < p - 1; i++){
            // skip the front p-1 nodes
            previous = current;
            current = current.next;
        }
        predecessor = previous;     // save the node at i = p - 1
        successor = current;        // save the node at i = p
        previous = current;
        current = current.next;
        i++;
        for(; current != null && i < q; i++){
            // reverse the node from p to q
            // i = p to q - 1
            temp = current.next;
            current.next = previous;

            previous = current;
            current = temp;
        }
        if(predecessor != null){
            predecessor.next = previous;     // connect the p - 1 and q
        }
        else{
            // we need to change the head into q
            head = previous;
        }
        successor.next = current;        // connect the p and q + 1

        return head;    
    }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    ListNode result = ReverseSubList.reverse(head, 2, 4);
    System.out.print("Nodes of the reversed LinkedList are: ");
    while (result != null) {
      System.out.print(result.value + " ");
      result = result.next;
    }
  }
}