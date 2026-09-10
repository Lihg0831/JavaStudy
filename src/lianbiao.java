public class lianbiao {

    /**
     * 单向链表节点。
     * val 保存当前位的数字，next 指向下一个节点。
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 将两个由链表表示的非负整数相加。
     *
     * <p>链表按逆序保存数字，例如 2 -> 4 -> 3 表示 342。
     * 返回结果也使用相同的逆序形式。</p>
     *
     * @param l1 第一个整数对应的链表头节点
     * @param l2 第二个整数对应的链表头节点
     * @return 两数之和对应的链表头节点
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 使用哑节点作为结果链表的起点，避免单独处理第一个结果节点。
        ListNode head = new ListNode(0);
        ListNode cur = head;
        int carry = 0;

        // 只要任一链表还有数字，或者最后仍有进位，就继续计算。
        while (l1 != null || l2 != null || carry != 0) {
            // 较短的链表遍历完后，将它当前位的值视为 0。
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int sum = val1 + val2 + carry;

            carry = sum / 10;                         // 十位部分作为下一轮的进位
            cur.next = new ListNode(sum % 10);       // 个位部分作为当前结果节点
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        // head 是辅助节点，真正的结果从 head.next 开始。
        return head.next;
    }
}
