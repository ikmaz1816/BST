package bst;

import java.util.Scanner;
import java.util.Stack;

public class BinarySearchIteratorDesc {

	Stack<Node> s;
	static Scanner sc=new Scanner(System.in);
	public BinarySearchIteratorDesc(Node node)
	{
		s=new Stack<>();
		insert(node);
	}
	private void insert(Node node) {
		while(node!=null)
		{
			s.add(node);
			node=node.right;
		}
	}
	public int next()
	{
		Node temp=s.pop();
		insert(temp.left);
		return temp.data;
	}
	public boolean hasNext()
	{
		return !s.isEmpty();
	}
	public int peek()
	{
		return s.peek().data;
	}
	public static Node create()
	{
		int data=sc.nextInt();
		if(data==-1)
			return null;
		Node n=new Node(data);
		n.left=create();
		n.right=create();
		return n;
	}
	public static void main(String[] args) {
		Node root=create();
		BinarySearchIteratorDesc b=new BinarySearchIteratorDesc(root);
		while(b.hasNext())
		{
			System.out.print(b.next()+" ");
		}
	}


}
