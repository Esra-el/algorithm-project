/**
 * Nome: Esra Aihab Farag Cognome: Elareibi
 * Matricola: 7170469
 */
package Elareibi;

public class NodoVP<T> {

	// Variable that stores the data held by the node
	private T info;

	// Constructor that initializes the node with given data
	public NodoVP(T info) {
		this.info = info;
	}

	// Returns the data stored in the node
	public T getInfo() {
		return info;
	}

	// Updates the data stored in the node
	public void setInfo(T info) {
		this.info = info;
	}

	// Returns a String that represents the data stored in the node
	@Override
	public String toString() {
		return info.toString();
	}
	// Main method to test NodoVP class

	public static void main(String[] args) {
		NodoVP<String> node = new NodoVP<>("Example");
		System.out.println("Node content: " + node.getInfo()); // Prints "Example"

		node.setInfo("New value");
		System.out.println("Updated node content: " + node.getInfo()); // Prints "New value"

		System.out.println("toString() output: " + node.toString()); // Prints "New value"
	}

}
