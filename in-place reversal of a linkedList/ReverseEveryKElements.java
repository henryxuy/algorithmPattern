import java.util.*;

class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}

class ReverseEveryKElements {

    public static ListNode reverse(ListNode head, int k) {
      // reverse the list in group of k, still reverse if # of remaining node < k
      if(head == null){
        return null;
      }
      ListNode end = head;
      for(int i = 0; i < k; i++){
        if(end == null){
          break;
        }
        end = end.next;
      }
      ListNode newHead = reversePart(head, end);
      head.next = reverse(end, k);
        
      return newHead;
    }


    public static ListNode reverseKGroupIteration(ListNode head, int k){
      if(k < 1 || head == null){
        return head;
      }
      ListNode current = head, previousTail = null;
      ListNode resultHead = head;
      int time = 0;
      while(true){
        boolean endFlag = false;
        ListNode currentEnd = current;
        for(int i = 0; i < k; i++){
          if(currentEnd == null){
            endFlag = true;
            break;
          }
          currentEnd = currentEnd.next;
        }
        // reverse the [current, currentEnd)
        ListNode newHead = reversePart(current, currentEnd);
        if(time == 0){
          resultHead = newHead;
        }
        if(previousTail != null){
          previousTail.next = newHead;
        }

        previousTail = current;
        current = currentEnd;
        time++;
        if(endFlag){
          break;
        }
      }

      return resultHead;
    }




    public static ListNode reverseKGroup(ListNode head, int k){
      // recursively reverse the list in group of k, not reverse if # of remaining node < k
      if(head == null){
        return null;
      }
      ListNode end = head;
      for(int i = 0; i < k; i++){
        // actually, end is the (k + 1)th node in the end of the loop
        if(end == null){
          return head;
        }
        end = end.next;
      }
      ListNode newHead = reversePart(head, end);   // Note: end is not reversed and is in the next k-group
      head.next = reverseKGroup(end, k);        // original head is the tail of reversed group. Link it to the head of next group

      return newHead;
    }


    private static ListNode reversePart(ListNode start, ListNode end){
      // reverse the linked list in [start, end) and return the new head
      ListNode current=start, previous=null, next=start;

      while(current != end){
        next = current.next;
        current.next = previous;

        previous = current;
        current = next;
      }

      return previous;      // [start, end), so we should return previous
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

    ListNode result = ReverseEveryKElements.reverseKGroupIteration(head, 3);
    System.out.print("Nodes of the reversed LinkedList are: ");
    while (result != null) {
      System.out.print(result.value + " ");
      result = result.next;
    }
  }
}
