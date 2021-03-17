class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}


class ReverseListRecursively{
  ListNode reverse(ListNode head){
    // reverse the list recursively, and return the new head
    if(head.next == null){
      return head;
    }
    ListNode newHeadNext = reverse(head.next);
    head.next.next = head;
    head.next = null;              // only effective for the last one in stack(first on called). Others are overwritten (by previous code)

    return newHeadNext;
  }
  ListNode successor = null;

  ListNode reverseN(ListNode head, int n){
    if(n == 1){
      successor = head.next;
      return head;
    }
    ListNode newHeadNext = reverseN(head.next, n - 1);
    head.next.next = head;
    head.next = successor;

    return newHeadNext;
  }

  ListNode reverseBetween(ListNode head, int m, int n){
    if(m == 1){
      return reverseN(head, n);
    }

    head.next = reverseBetween(head.next, m - 1, n - 1);
    return head;
  }





}



