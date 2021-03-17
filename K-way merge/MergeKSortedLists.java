import java.util.*;

class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

class MergeKSortedLists {

    public static ListNode merge(ListNode[] lists) {
        // One ListNode denotes one sorted array
        ListNode result = new ListNode(-1);
        ListNode current = null;
        ListNode previous = null;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((o1, o2) -> (o1.value - o2.value));

        for(int i = 0; i < lists.length; i++){
            if(lists[i] != null)
                minHeap.offer(lists[i]);
        }
        while(!minHeap.isEmpty()){
            current = minHeap.poll();
            if(previous != null){
                // connect the nodes in the result linked list
                previous.next = current;
            }
            else{
                // the first node
                result = current;
            }
            // offer the next node of current to the minHeap to loop through the whole linked list
            if(current.next != null)
                minHeap.offer(current.next);
            previous = current;
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(6);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);

        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(4);

        ListNode result = MergeKSortedLists.merge(new ListNode[] { l1, l2, l3 });
        System.out.print("Here are the elements form the merged list: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}