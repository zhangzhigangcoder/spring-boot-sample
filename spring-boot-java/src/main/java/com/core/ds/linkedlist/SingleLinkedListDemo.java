package com.core.ds.linkedlist;

/**
 * 无序单链表
 *
 */
public class SingleLinkedListDemo {

	public static void main(String[] args) {
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
		SingleLinkedList singleLinkedList = new SingleLinkedList(true);
		singleLinkedList.add(hero4);
		singleLinkedList.add(hero1);
		singleLinkedList.add(hero2);
		singleLinkedList.add(hero3);
		hero2 = new HeroNode(2, "时迁", "跳上蚤");
		singleLinkedList.update(hero2);
		singleLinkedList.list();
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
	public void addWithoutOrder(HeroNode node) {
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
			if (temp.next.no >= node.no) {
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
			if (null == temp) {
				break;
			}
			if (temp.no == newNode.no) {
				isExist = true;
				break;
			}
			temp = temp.next;
		}
		if (!isExist) {
			System.out.println("节点不存在: " + newNode);
		}
		temp.name = newNode.name;
		temp.nikename = newNode.nikename;
		
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
