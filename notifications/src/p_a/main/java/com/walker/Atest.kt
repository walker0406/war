package com.walker

/**
 * Created by admin on 2021/8/10.
 */
class Node(val value: Int) {
    var next: Node? = null
}

fun main() {
    var head: Node? = null
    var cur: Node? = null
    for (i in 0..5) {
        if (i == 0) {
            head = Node(i)
            cur = head
        } else {
            cur?.next = Node(i)
            cur = cur?.next
        }

    }
    //print(head)

    print(reverseNode(head))
}

private fun print(head: Node?) {
    var test = head
    var last: Node? = null
    while (test != null) {
        print(test?.value)
        last = test
        test = test.next
    }
}

fun reverseNode(head: Node?): Node? {

    var cur: Node? = head
    var next: Node? = head?.next
    var pre: Node?
    while (next != null) {
        pre = cur
        cur = next
        next = next.next
        cur.next = pre
        if (head == pre) {
            pre?.next = null
        }
    }
    return cur

}

