class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
  }
  
  class ReverseLinkedList {
  
    public static ListNode reverse(ListNode head) {
        // in-place reverse the singly linkedlist
        ListNode previous = null;
        ListNode current = head;
        ListNode temp = null;
        while(current != null){
            temp = current.next;
            current.next = previous;

            previous = current;
            current = temp;
        }

        return previous;
    }
  
    public static void main(String[] args) {
      ListNode head = new ListNode(2);
      head.next = new ListNode(4);
      head.next.next = new ListNode(6);
      head.next.next.next = new ListNode(8);
      head.next.next.next.next = new ListNode(10);
  
      ListNode result = ReverseLinkedList.reverse(head);
      System.out.print("Nodes of the reversed LinkedList are: ");
      while (result != null) {
        System.out.print(result.value + " ");
        result = result.next;
      }
    }
  }