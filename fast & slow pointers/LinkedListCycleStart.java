class ListNode {
    int value = 0;
    ListNode next;
  
    ListNode(int value) {
      this.value = value;
    }
  }
  
  class LinkedListCycleStart {
  
    public static ListNode findCycleStart(ListNode head) {
        // TODO: Write your code here
        // use fast & slow pointer to find the start of the cycle contained in a linked list
        // calculcate the length of cycle L first, suppose start x steps from the head

        // then first make pointer2 go L steps
        // then let pointer1 go from head. When they meet, pointer2 (L + x) steps, pointer1 x steps
        // and they meet at the start point of the cycle
        ListNode fastPointer = head;
        ListNode slowPointer = head;
        while(fastPointer.next != null && fastPointer.next != null){
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
            if(fastPointer == slowPointer){
                // if has cycle, calculate the length first
                int length = calculateCycleLength(fastPointer);
                // use two pointers to find the starting node
                return findStart(head, length);
            }
        }
        return head;
    }

    private static int calculateCycleLength(ListNode current){
        int length = 0;
        ListNode pointer = current;
        do{
            pointer = pointer.next;
            length++;
        } while(pointer != current);

        return length;
    }

    private static ListNode findStart(ListNode start, int cycleLength){
        ListNode aheadPointer = start, behindPointer = start;
        for(int i = 0; i < cycleLength; i++){
            aheadPointer = aheadPointer.next;
        }
        while(aheadPointer != behindPointer){
            aheadPointer = aheadPointer.next;
            behindPointer = behindPointer.next;
        }

        return aheadPointer;
    }

  
    public static void main(String[] args) {
      ListNode head = new ListNode(1);
      head.next = new ListNode(2);
      head.next.next = new ListNode(3);
      head.next.next.next = new ListNode(4);
      head.next.next.next.next = new ListNode(5);
      head.next.next.next.next.next = new ListNode(6);
  
      head.next.next.next.next.next.next = head.next.next;
      System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);
  
      head.next.next.next.next.next.next = head.next.next.next;
      System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);
  
      head.next.next.next.next.next.next = head;
      System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);
    }
  }