class ListNode {
    int value = 0;
    ListNode next;
  
    ListNode(int value) {
        this.value = value;
    }
  }
  
  class LinkedListCycle {
  
    public static boolean hasCycle(ListNode head) {
        // TODO: Write your code here
        // use fast and slow pointer to detect cycle
        ListNode fastNode = head;
        ListNode slowNode = head;

        while(fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if(fastNode == slowNode){
                return true;
            }
        }

        return false;
    }

    public static int circleLength(ListNode head){
        // use fast and slow pointer to detect cycle
        int length = -1;
        ListNode fastNode = head;
        ListNode slowNode = head;

        while(fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if(fastNode == slowNode){
                length = 0;
                do{
                    // fastNode = fastNode.next.next;
                    slowNode = slowNode.next;
                    length++;
                } while(fastNode != slowNode);
                return length;
            }
        }

        return length;
    }


  
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));
    }
  }