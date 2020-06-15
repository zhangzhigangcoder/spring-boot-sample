package com.core.ds.linkedlist;

/**
 * 单链表
 *
 */
public class SingleLinkedListDemo {

	public static void main(String[] args) {
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
		SingleLinkedList singleLinkedList = new SingleLinkedList(true);
		singleLinkedList.add(hero1);
		singleLinkedList.add(hero2);
		singleLinkedList.add(hero3);
		singleLinkedList.add(hero4);
//		hero2 = new HeroNode(2, "时迁", "跳上蚤");
//		singleLinkedList.update(hero2);
//		singleLinkedList.delete(hero2);
		singleLinkedList.list();
//		System.out.println(findLastIndexNode(singleLinkedList.getHead(), 2));
		reverseLinkedList(singleLinkedList.getHead());
		singleLinkedList.list();
	}
	
	
	/**
	 * 面试题：获取单向链表的有效节点个数
	 * @param head 链表的头节点(假设是待头节点的链表)
	 * @return 返回的就是有效节点的个数
	 */
	public static int getLength(HeroNode head) {
		if (null == head.next) {
			return 0;
		}
		int length = 0;
		HeroNode temp = head.next;
		while (null != temp) {
			length ++;
			temp = temp.next;
		}
		return length;
	}
	
	/**
	 * 面试题： 查找单向链表中的倒数第k个节点
	 * @param head
	 * @param index
	 * @return
	 */
	public static HeroNode findLastIndexNode(HeroNode head, int index) {
		// 判断是否为空
		if (null == head.next) {
			return null;
		}
		// 1. 查询链表有效长度
		int length = getLength(head);
		if (index <= 0 || index > length) {
			return null;
		}
		HeroNode temp = head.next;
		for (int i = 1; i <= length - index; i++) {
			temp = temp.next;
		}
		return temp;
	}

	/**
	 * 面试题：单向链表逆序
	 * @param head
	 */
	public static void reverseLinkedList(HeroNode head) {
		// 链表只有一个头节点或只有一个数据节点时，直接返回
		if (null == head.next || null == head.next.next) {
			return;
		}
		// 创建一个新链表头节点
		HeroNode newHead = new HeroNode(0, null, null);
		HeroNode curTemp = head.next;
		HeroNode nextNodeTemp = null;
		while (null != curTemp) {
			// 保存下一个节点引用
			nextNodeTemp = curTemp.next; 
			// 把当前节点插入到新链表第一个数据节点位置
			curTemp.next = newHead.next;
			newHead.next = curTemp;
			// 当前节点向后移动一位
			curTemp = nextNodeTemp;
		}
		// 将原链表第一个数据节点指向新链表第一个数据节点
		head.next = newHead.next;
	}
	
	/**
	 * 从尾到头打印单向链表
	 * 方式一：先将链表逆序，然后遍历，这样会修改链表顺序，不推荐
	 * 方式二：利用栈的特点，实现逆序打印
	 * @param head
	 */
	public static void m(HeroNode head) {
		
	}
	
}

class SingleLinkedList {
	// 初始化头节点
	private HeroNode head = new HeroNode(0, null, null);

	// 是否排序
	private boolean sorted;

	public SingleLinkedList() {
		this(false);
	}

	public SingleLinkedList(boolean sorted) {
		this.sorted = sorted;
	}

	// 添加一个节点
	public void add(HeroNode node) {
		if (sorted) {
			addByOrder(node);
			return;
		}
		addWithoutOrder(node);
	}

	// 无序添加节点
	private void addWithoutOrder(HeroNode node) {
		HeroNode temp = head;
		// 查找最后一个节点
		while (null != temp.next) {
			temp = temp.next;
		}
		// 添加到链表尾部
		temp.next = node;
	}

	// 有序添加节点
	private void addByOrder(HeroNode node) {
		HeroNode temp = head;
		boolean isExist = false; // 节点是否已存在
		// 查找插入位置的前一个节点
		while (true) {
			if (null == temp.next) { // 只有一个头节点
				break;
			}
			if (temp.next.no >= node.no) { // 找到插入位置前一个节点
				if (temp.next.no == node.no) {
					isExist = true;
				}
				break;
			}
			temp = temp.next;
		}
		if (isExist) {
			System.out.println("节点已存在: " + node);
			return;
		}
		// 添加到链表中
		node.next = temp.next;
		temp.next = node;
	}
	
	// 根据no，修改节点
	public void update(HeroNode newNode) {
		if (null == head.next) {
			System.out.println("链表为空");
			return;
		}
		HeroNode temp = head.next;
		boolean isExist = false;
		while (true) {
			if (null == temp) { // 只有一个头节点
				break;
			}
			if (temp.no == newNode.no) { // 查找到待更新节点
				isExist = true;
				break;
			}
			temp = temp.next;
		}
		if (!isExist) {
			System.out.println("节点不存在: " + newNode);
			return;
		}
		temp.name = newNode.name;
		temp.nikename = newNode.nikename;
	}
	
	// 删除节点
	public void delete(HeroNode delNode) {
		if (null == head.next) {
			System.out.println("链表为空");
			return;
		}
		HeroNode temp = head;
		boolean isExist = false;
		while (true) {
			if (null == temp) { // 只有一个头节点
				break;
			}
			if (temp.next.no == delNode.no) { // 查找到待删除节点的前一个节点
				isExist = true;
				break;
			}
			temp = temp.next;
		}
		if (!isExist) {
			System.out.println("节点不存在: " + delNode);
			return;
		}
		// 跳过要删除的节点，指向下下个节点
		temp.next = temp.next.next;
	}
	
	// 显示链表
	public void list() {
		if (null == head.next) {
			System.out.println("链表为空");
			return;
		}
		HeroNode temp = head.next;
		while (null != temp) {
			System.out.println(temp);
			temp = temp.next;
		}
	}
	
	public HeroNode getHead() {
		return head;
	}
}

// Node节点
class HeroNode {

	public int no;
	public String name;
	public String nikename;
	public HeroNode next;

	public HeroNode(int no, String name, String nikename) {
		this.no = no;
		this.name = name;
		this.nikename = nikename;
	}
	
	public HeroNode clone() {
		HeroNode heroNode = new HeroNode(this.no, this.name, this.nikename);
		heroNode.next = this.next;
		return heroNode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HeroNode [no=");
		builder.append(no);
		builder.append(", name=");
		builder.append(name);
		builder.append(", nikename=");
		builder.append(nikename);
		builder.append("]");
		return builder.toString();
	}
}
