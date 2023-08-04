
public class Node<T extends Comparable<? super T>> {
	// Cada nodo tiene una altura, datos y un hijo izquierdo y derecho
	//Atributos
	private int height;
	private Node<T> left, right;
	private T value;
	
	//Getters y Setters  
	public Node(T value) {
		this.value = value;
		height = 1;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}