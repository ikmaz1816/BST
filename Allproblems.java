package bst;

import java.util.Scanner;
class Pair
{
	int max;
	int min;
	int maxsize;
	public Pair(int max,int min,int maxsize)
	{
		this.max=max;
		this.min=min;
		this.maxsize=maxsize;
	}
}
class Node
{
	int data;
	Node left;
	Node right;
	public Node(int data)
	{
		this.data=data;
	}
}
public class Allproblems {
	static Scanner sc;
	static
	{
		sc=new Scanner(System.in);
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
	public static void inorder(Node node)
	{
		if(node==null)
			return;
		inorder(node.left);
		System.out.print(node.data+" ");
		inorder(node.right);
	}
	public static int search(Node node,int data)
	{
		if(node!=null && node.data!=data)
		{
			node = node.data>data ? node.left : node.right;
		}
		if(node!=null)
			return node.data;
		return -1;
	}
	public static int floor(Node node,int data)
	{
		if(node==null)
			return -1;
		int floor=-1;
		while(node!=null)
		{
			if(node.data==data)
			{
				return node.data;
			}
			else if(node.data<data)
			{
				floor=node.data;
				node=node.right;
			}
			else
			{
				node = node.left;
			}
		}
		return floor;
	}
	public static int ceil(Node node,int data)
	{
		if(node==null)
			return -1;
		int ceil=-1;
		while(node!=null)
		{
			if(node.data==data) {
				return node.data;
			}
			if(node.data>data)
			{
				ceil=node.data;
				node=node.left;
			}
			else
			{
				node=node.right;
			}
		}
		return ceil;
	}
	public static int successorInorder(Node node,int data)
	{
		if(node==null)
			return -1;
		int max=-1;
		while(node!=null)
		{
			if(node.data>data)
			{
				max=node.data;
				node=node.left;
			}
			else
				node=node.right;
		}
		return max;
	}
	public static int predecessorInorder(Node node,int data)
	{
		if(node==null)
			return -1;
		int min=-1;
		while(node!=null)
		{
			if(node.data<data)
			{
				min=node.data;
				node=node.right;
			}
			else
				node=node.left;
		}
		return min;
	}
	public static int max(Node node,int[] max)
	{
		while(node!=null)
		{
			max[0]=node.data;
			node=node.right;
		}
		return max[0];
	}
	public static int min(Node node,int[] min)
	{
		while(node!=null)
		{
			min[0]=node.data;
			node=node.left;
		}
		return min[0];
	}
	public static void insert(Node node,int data)
	{
		while(node!=null)
		{
			if(node.data>data)
			{
			if(node.left!=null)
				node=node.left;
			else
			{
				node.left=new Node(data);
				break;
			}
			}
			else
			{
				if(node.right!=null)
					node=node.right;
				else
				{
					node.right=new Node(data);
					break;
				}
			}
		}
	}
	public static Node delete(Node root,int data)
	{
		if(root==null)
			return root;
		if(root.data==data)
			return helper(root);
		Node root1=root;
		while(root!=null)
		{
			if(root.data>data)
			{
				if(root.left!=null && root.left.data==data)
				{
					root.left=helper(root.left);
					break;
				}
				else
					root=root.left;
			}
			else
			{
				if(root.right!=null && root.right.data==data)
				{
					root.right=helper(root.right);
					break;
				}
				else
					root=root.right;
			}
		}
		return root1;
	}
	private static Node helper(Node root) {
		Node rightchild=root.right;
		Node rightmostchild=root.left;
		while(rightmostchild.right!=null)
		{
			rightmostchild=rightmostchild.right;
		}
		rightmostchild.right=rightchild;
		return root.left;
	}
	private static Node LCA(Node node,int p,int q)
	{
		if(node==null)
			return node;
		while(node!=null)
		{
			if(node.data>p && node.data>q)
			{
				node=node.left;
			}
			else if(node.data<p && node.data<q)
			{
				node=node.right;
			}
			else
			{
				break;
			}
		}
		return node;
	}
	public static void smallest(Node node,int[] max,int k,int[] i)
	{
		if(node==null)
			return;
		smallest(node.left,max,k,i);
		i[0]++;
		if(i[0]==k)
		{
			max[0]=node.data;
		}
		smallest(node.right,max,k,i);
	}
	public static void largest(Node node,int[] max,int k,int[] i)
	{
		if(node==null)
			return;
		largest(node.right,max,k,i);
		i[0]++;
		if(i[0]==k)
		{
			max[0]=node.data;
		}
		smallest(node.left,max,k,i);
	}
	public static boolean check(Node node,int max,int min)
	{
		if(node==null)
			return true;
		if(node.data<min || node.data>max)
			return false;
		return check(node.left,node.data,min) && check(node.right,max,node.data);
	}
	public static Node create(int[] arr,int[] i,int bound)
	{
		if(i[0]==arr.length || arr[i[0]]>bound)
			return null;
		Node n=new Node(arr[i[0]++]);
		n.left=create(arr,i,n.data);
		n.right=create(arr,i,bound);
		return n;
	}
	public static void preOrder(Node node)
	{
		if(node==null)
			return;
		System.out.print(node.data+" ");
		preOrder(node.left);
		preOrder(node.right);
	}
	public static boolean twoSum(Node node,Node node1)
	{
		BinarySearchIterator l=new BinarySearchIterator(node);
		BinarySearchIteratorDesc r=new BinarySearchIteratorDesc(node1);
		int sum=0;
		while(l.hasNext() && r.hasNext())
		{
			int a=l.peek();
			int b=r.peek();
			if(a+b==sum)
			{
				return true;
			}
			if(a+b>sum)
				r.next();
			else
				l.next();
		}
		return false;
	}
	private static Node first=null,middle=null,last=null,prev=null;
	public static void recover(Node node)
	{
		if(node==null)
			return;
		recover(node.left);
		if(prev!=null && node.data<prev.data)
		{
			if(first==null)
			{
				first=prev;
				middle=node;
			}
			else
				last=node;
		}
		prev=node;
		recover(node.right);
	}
	public static Pair largestBST(Node node)
	{
		if(node==null)
			return new Pair(Integer.MIN_VALUE,Integer.MAX_VALUE,0);
		Pair left=largestBST(node.left);
		Pair right=largestBST(node.right);
		if(left.max<node.data && right.min>node.data)
			return new Pair(Math.min(node.data,left.min),Math.max(node.data, right.max),left.maxsize+right.maxsize+1);
		return new Pair(Integer.MAX_VALUE,Integer.MIN_VALUE,Math.max(left.maxsize, right.maxsize));
	}
	public static void main(String[] args) {
		Node root=create();
		System.out.println(largestBST(root).maxsize);
	}

}
