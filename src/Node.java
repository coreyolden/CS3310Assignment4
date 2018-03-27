
public class Node {
	private Node leftChild;
	private Node rightChild;
	private Node parent;
	private String data;
	private int height = 0;
	
	public void setData(String dat) {
		data = dat;
	}
	public void setLeft(Node left) {
		leftChild = left;
	}
	public void setRight(Node right) {
		rightChild = right;
	}
	public void setParent(Node par) {
		parent = par;
	}
	public void setHeight(int h) {
		height = h;
	}
	public Node getLeft() {
		return leftChild;
	}
	public Node getRight() {
		return rightChild;
	}
	public Node getParent() {
		return parent;
	}
	public String getData() {
		return data;
	}
	public int getHeight() {
		return height;
	}
}
