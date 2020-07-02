package com.core.ds.hashtable;

import java.util.Scanner;

/**
 * 哈希表
 *
 */
public class HashTabDemo {

	public static void main(String[] args) {
		HashTab empTab = new HashTabDemo().new HashTab(5);
		char key = ' ';
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("请输入操作：l-list, a-add, d-del, e-exit,");
			key = scanner.next().charAt(0);
			switch (key) {
			case 'a':
				System.out.println("请输入一个编号:");
				int no = scanner.nextInt();
				System.out.println("请输入一个姓名:");
				String name = scanner.next();
				empTab.add(empTab.new Employee(no, name));
				break;
			case 'd':
				System.out.println("请输入一个编号:");
				empTab.delete(scanner.nextInt());
				break;
			case 'l':
				empTab.list();
				break;
			case 'e':
				scanner.close();
				loop = false;
				System.out.println("程序退出");
				break;
			}

		}
	}

	/**
	 * 哈希表
	 */
	private class HashTab {
		private EmployeeLinkedList[] elementData;
		private int size;
		
		public HashTab(int size) {
			this.size = size;
			elementData = new EmployeeLinkedList[size];
			for (int i = 0; i < size; i++) {
				elementData[i] = new EmployeeLinkedList(i);
			}
		}

		/**
		 * 添加一个节点
		 * 
		 * @param e
		 */
		public void add(Employee e) {
			int index = hashCode(e.no);
			elementData[index].add(e);
		}

		/**
		 * 删除一个节点
		 */
		public void delete(int no) {
			elementData[hashCode(no)].delete(no);
		}

		/**
		 * 遍历
		 */
		public void list() {
			for (int i = 0; i < size; i++) {
				elementData[i].list();
			}
		}

		/**
		 * 散列函数
		 * @param no
		 * @return
		 */
		public int hashCode(int no) {
			return no % size;
		}
		
		/**
		 * 链表
		 * 
		 */
		private class EmployeeLinkedList {
			private Employee first;
			private int no;

			public EmployeeLinkedList(int no) {
				this.no = no;
			}

			public void add(Employee e) {
				if (null == first) {
					first = e;
					return;
				}
				Employee temp = first;
				while (temp.next != null) {
					temp = temp.next;
				}
				temp.next = e;
			}

			public void delete(int no) {
				if (null == first) {
					return;
				}

				boolean isExist = false;
				Employee temp = first;
				while (temp.next != null) {
					if (temp.next.no == no) {
						isExist = true;
						break;
					}
					temp = temp.next;
				}
				if (isExist) {
					temp.next = temp.next.next;
					return;
				}
				// temp可能是第一个节点
				if (temp.no == first.no) {
					first = null;
				}

			}

			public void list() {
				if (null == first) {
					System.out.println("第" + (no + 1) + "条链表为空");
					return;
				}
				System.out.print("第" + (no + 1) + "条链表:");
				Employee temp = first;
				while (temp != null) {
					System.out.print(temp + "\t");
					temp = temp.next;
				}
				System.out.println();
			}

		}

		/**
		 * 节点
		 */
		private class Employee {
			private int no;
			private String name;
			public Employee next;

			public Employee(int no, String name) {
				super();
				this.no = no;
				this.name = name;
			}

			@Override
			public String toString() {
				StringBuilder builder = new StringBuilder();
				builder.append("Employee [no=");
				builder.append(no);
				builder.append(", name=");
				builder.append(name);
				builder.append("]");
				return builder.toString();
			}
		}
		
	}


}
