
public class Node {
	private Node leftChild;
	private Node rightChild;
	private Node parent;
	private String data;
	private int height = 0;
	
	/**Stores a string in the node 
	 * 
	 * @param dat
	 */
	public void setData(String dat) {
		data = dat;
	}
	/**sets the left child of the node 
	 * 
	 * @param left
	 */
	public void setLeft(Node left) {
		leftChild = left;
	}
	/**sets the right child of the node 
	 * 
	 * @param right
	 */
	public void setRight(Node right) {
		rightChild = right;
	}
	/**set the parent of the node 
	 * 
	 * @param par
	 */
	public void setParent(Node par) {
		parent = par;
	}
	/**sets the height of the node 
	 * 
	 * @param h
	 */
	public void setHeight(int h) {
		height = h;
	}
	/**returns the left child of the node 
	 * 
	 * @return
	 */
	public Node getLeft() {
		return leftChild;
	}
	/**returns the right child of the node 
	 * 
	 * @return
	 */
	public Node getRight() {
		return rightChild;
	}
	/**returns the parent of the node 
	 * 
	 * @return
	 */
	public Node getParent() {
		return parent;
	}
	/**returns the string stored in the node 
	 * 
	 * @return
	 */
	public String getData() {
		return data;
	}
	/** returns the height of the node
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}
}
