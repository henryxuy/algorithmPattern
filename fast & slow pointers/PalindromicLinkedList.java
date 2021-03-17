public class PalindromicLinkedList {
    public static boolean isPalindromeElse(ListNode head) {
        if (head == null || head.next == null)
          return true;
    
        // find middle of the LinkedList
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
          slow = slow.next;
          fast = fast.next.next;
        }
    
        ListNode headSecondHalf = reverse(slow); // reverse the second half
        ListNode copyHeadSecondHalf = headSecondHalf; // store the head of reversed part to revert back later
    
        // compare the first and the second half
        while (head != null && headSecondHalf != null) {
          if (head.value != headSecondHalf.value) {
            break; // not a palindrome
          }
          head = head.next;
          headSecondHalf = headSecondHalf.next;
        }
    
        reverse(copyHeadSecondHalf); // revert the reverse of the second half
        if (head == null || headSecondHalf == null) // if both halves match
          return true;
        return false;
      }
    
      private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
          ListNode next = head.next;
          head.next = prev;
          prev = head;
          head = next;
        }
        return prev;
      }


    public static boolean isPalindrome(ListNode head) {
        // check whether a linkedlist is palindromic or not
        // in-place O(1): (1) use fast & slow pointer to find the middle
        // (2) reverse the last half
        // (3) see whether is palindromic
        // (4) reverse the last half back to original sequence
        ListNode fast = head, slow = head;
        int halfCount = 0;
        boolean result = true;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            halfCount++;
        }
        // now the slow is the middle, reverse the [middle...end]
        ListNode middle = slow;
        ListNode current = middle.next, parent = middle;
        while(current != null){
            ListNode nextNode = current.next;
            current.next = parent;

            parent = current;
            current = nextNode;
        }
        // at the end of the loop: parent == lastNode, current == null

        // judge the palindrome and reverse the list back
        ListNode start = head;
        current = parent;
        parent = null;
        for(int i = 0; i < halfCount; i++){
            ListNode nextNode = current.next;
            if(start.value != current.value){
                result = false;
            }
            if(parent != null){
                current.next = parent;
            }
            else{
                current.next = null;
            }
            start = start.next;
            parent = current;
            current = nextNode;
        }


        return result;
    }
    


    public static void main(String[] args) {
          ListNode head = new ListNode(2);
          head.next = new ListNode(4);
          head.next.next = new ListNode(6);
          head.next.next.next = new ListNode(4);
          head.next.next.next.next = new ListNode(2);
          System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head));
          System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head));

          head.next.next.next.next.next = new ListNode(2);
          System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head));
  }
    
}
