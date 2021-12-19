/**
 * point1: double linked list
 * point2: number addition
 *
 * description: this is a question that need to calculate two numbers represented by two non-empty single-linked list.
 * The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and
 * return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * for example, "342" is stored as [2,4,3], "465" is stored as [5,6,4]
 * and the result output is [7,0,8] where 342+465 = 807
 *
 * another example:
 * l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9], output = [8,9,9,9,0,0,0,1]
 * where 9999999 + 9999 = 10009998
 *
 * Thoughts:
 * 1. the result is calculated by adding the value of node in first singly-linked list and the relevant value of node
 * (same position) in second singly-linked list. The calculated result will be stored in reverse order in returned
 * singly-linked list
 *
 * 2. You should notice following situations about input:
 * (1) only the traversal of first singly-linked list is done
 * (2) only the traversal of second singly-linked list is done
 * (3) both traversals of first and second singly-linked list are done
 *
 * 3. You should notice following situations when implementing the addition algorithm:
 * (1) carry problem of middle digit
 * (2) carry problem of the highest digit
 * (3) how to create the returned singly-linked list with above issue
 *
 * 4. detailed thought:
 * Since we need to calculate numbers by accessing each node one by one, hence, the recursive algorithm was applied for
 * construction.
 *
 * In the progress of addition, the input situations above not appear connective. But since we will use recursive, hence
 * these situations can be solved respectively.
 *
 * But before constructing the addition, it is noticed that the returned final result is stored in a singly-linked list
 * with reversed order.
 *
 * Firstly, at the end of addition, if there are no carry problems occurs (a + b >= 10), the only thing is to create
 * a new node, set the addition result as value and the next node as "null". If there are carry problem occurs, the
 * addition result need to -10, then create a new node which value be the single digit and create another new node which
 * value be "1" as the carried number as the next node of the first created node, and it's next node will be "null".
 * E.g.
 * node1: [9] -> [null]
 * node2: [9] -> [null]
 * result: [8] -> [1] -> [null]
 *
 * Secondly, if in the case that when the first singly-linked list still in traversal but the traversal of second
 * singly-linked list are done or inverse situation (we judge this situation by check the current node's next node),
 * the thing the need to do is create a new node which the value is the addition result, and the next node is the
 * result that call this function itself with that the first argument is the next node of current first node, and the
 * second argument is the new node with value 0 and it's next node is "null", and vice versa.
 * If in this case and meets the carry problem, the additional thing that need to do is add "1" to the value of first
 * node's next node's value. (if the first singly-linked list is in traversal but the second is not), and then do the
 * above step.
 *
 * The next situation is the normal situation, no further elaboration here.
 */
public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // calculate current value
        int currentNodeVal = l1.val + l2.val;
        if (l1.next == null && l2.next == null){
            if (currentNodeVal >= 10) {
                currentNodeVal = currentNodeVal - 10;
                return new ListNode(currentNodeVal,new ListNode(1,null));
            }
            return new ListNode(currentNodeVal, null);
        }

        if(l1.next == null){
            if (currentNodeVal >= 10) {
                currentNodeVal = currentNodeVal - 10;
                l2.next.val +=1;
            }
            return new ListNode(currentNodeVal, addTwoNumbers(new ListNode(0, null), l2.next));
        }

        if (l2.next == null){
            if (currentNodeVal >= 10) {
                currentNodeVal = currentNodeVal - 10;
                l1.next.val +=1;
            }
            return new ListNode(currentNodeVal, addTwoNumbers(l1.next, new ListNode(0, null)));
        }

        if (currentNodeVal >= 10) {
            currentNodeVal = currentNodeVal - 10;
            l1.next.val +=1;
        }
        return new ListNode(currentNodeVal,addTwoNumbers(l1.next,l2.next));
    }

    public static void main(String[] args) {
        ListNode node3 = new ListNode(3,null);
        ListNode node2 = new ListNode(4, node3);
        ListNode node1 = new ListNode(2, node2);
        ListNode node6 = new ListNode(4, null);
        ListNode node5 = new ListNode(6, node6);
        ListNode node4 = new ListNode(5, node5);
//
//        ListNode node7 = new ListNode(0,null);
//        ListNode node8 = new ListNode(0, null);

//        ListNode node7 = new ListNode(9,null);
//        ListNode node6 = new ListNode(9, node7);
//        ListNode node5 = new ListNode(9, node6);
//        ListNode node4 = new ListNode(9, node5);
//        ListNode node3 = new ListNode(9, node4);
//        ListNode node2 = new ListNode(9, node3);
//        ListNode node1 = new ListNode(9,node2);
//
//        ListNode node11 = new ListNode(9, null);
//        ListNode node10 = new ListNode(9,node11);
//        ListNode node9 = new ListNode(9, node10);
//        ListNode node8 = new ListNode(9, node9);

        ListNode result = addTwoNumbers(node1,node4);
        while (result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
