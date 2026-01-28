/**
 * Nome: Esra Aihab Farag Cognome: Elareibi
 * Matricola: 7170469
 */
package Elareibi;
import java.util.*;

public class AlberoVP<T> {

	// nodes: contains all nodes in the tree
	private ArrayList<NodoVP<T>> nodes;
	// parents: parallel list, parents.get(i) is the parent of nodes.get(i), null
	// means root
	private ArrayList<NodoVP<T>> parents;

	// Constructs an empty tree
	public AlberoVP() {
		nodes = new ArrayList<>();
		parents = new ArrayList<>();
	}

	// Inserts the root node; throws if already present
	public NodoVP<T> insertRoot(T info) {

		if (!nodes.isEmpty()) {
			throw new IllegalStateException("Tree already has a root");

		}

		NodoVP<T> root = new NodoVP<>(info);
		nodes.add(root);
		parents.add(null);
		return root;
	}

	// Inserts a new root; makes existing root its child
	public NodoVP<T> insertNewRoot(T info) {
		NodoVP<T> newRoot = new NodoVP<>(info);
		NodoVP<T> oldRoot = null;

		for (int i = 0; i < parents.size(); i++) {
			if (parents.get(i) == null) {
				oldRoot = nodes.get(i);
				parents.set(i, newRoot);
				break;
			}
		}

		nodes.add(newRoot);
		parents.add(null);
		return newRoot;
	}

	// Returns the current root node, or null if tree is empty
	public NodoVP<T> getRoot() {

		for (int i = 0; i < parents.size(); i++) {
			if (parents.get(i) == null) {
				return nodes.get(i);
			}
		}
		return null;
	}

	// Gets all children of the specified parent node
	public ArrayList<NodoVP<T>> getChildren(NodoVP<T> parent) {
		ArrayList<NodoVP<T>> children = new ArrayList<>();

		for (int i = 0; i < parents.size(); i++) {
			if (parents.get(i) == parent) {
				children.add(nodes.get(i));
			}
		}
		return children;
	}

	// Returns the parent of the given node, or null if not found
	public NodoVP<T> getParent(NodoVP<T> node) {

		int index = nodes.indexOf(node);
		if (index == -1) {
			return null;
		}
		return parents.get(index);
	}

	// Returns total count of nodes in the tree
	public int getNumberOfNodes() {
		return nodes.size();
	}

	/*
	 * Computes and returns the depth level of the node (how far the node is from
	 * the root)
	 */
	public int getLevel(NodoVP<T> node) {
		int level = 0;
		NodoVP<T> current = node;

		while (getParent(current) != null) {
			current = getParent(current);
			level++;
		} //
		return level;
	}

	// Adds a child with specified info under the given parent
	public NodoVP<T> addChild(NodoVP<T> parent, T info) {
		NodoVP<T> child = new NodoVP<>(info);
		nodes.add(child);
		parents.add(parent);
		return child;
	}

	// Returns number of children of the specified node
	public int getNumberOfChildren(NodoVP<T> node) {
		return getChildren(node).size();
	}

	// Get the info stored in the given node
	public T getContent(NodoVP<T> node) {
		return node.getInfo();
	}

	// Calculates height (max depth) of the tree, returns -1 if empty
	public int getHeight() {
		if (nodes.isEmpty()) {
			return -1;
		}

		int maxHeight = 0;

		for (NodoVP<T> node : nodes) {
			int nodeLevel = getLevel(node);
			if (nodeLevel > maxHeight) {
				maxHeight = nodeLevel;
			}
		}
		return maxHeight;
	}

	// Counts and returns number of leaf nodes(foglie)
	public int getNumberOfLeaves() {
		int leafCount = 0;

		for (NodoVP<T> node : nodes) {
			if (getNumberOfChildren(node) == 0) {
				leafCount++;
			}
		}
		return leafCount;
	}

	// Updates the info stored in a given node
	public void setContent(NodoVP<T> node, T newInfo) {
		node.setInfo(newInfo);
	}

	/////////////////////
	// Performs a depth-first traversal from the root and returns list of nodes
	public ArrayList<NodoVP<T>> depthFirstTraversal() {
		ArrayList<NodoVP<T>> result = new ArrayList<>();
		NodoVP<T> root = getRoot();

		if (root != null) {
			depthFirstHelper(root, result);
		}
		return result;
	}

	// Recursively adds nodes in depth-first manner to result list
	private void depthFirstHelper(NodoVP<T> node, ArrayList<NodoVP<T>> result) {
		result.add(node);

		for (NodoVP<T> child : getChildren(node)) {
			depthFirstHelper(child, result);
		}
	}

	// Performs a breadth-first traversal and returns list of nodes
	public ArrayList<NodoVP<T>> breadthFirstTraversal() {
		ArrayList<NodoVP<T>> result = new ArrayList<>();
		Queue<NodoVP<T>> queue = new LinkedList<>();
		NodoVP<T> root = getRoot();

		if (root == null) {
			return result;
		}

		queue.offer(root);

		while (!queue.isEmpty()) {
			NodoVP<T> current = queue.poll();
			result.add(current);

			for (NodoVP<T> child : getChildren(current)) {
				queue.offer(child);
			}
		}
		return result;
	}

	// Returns the tree as a string: each node followed by its children in [ ]
	public String toString() {
		NodoVP<T> root = getRoot();
		if (root == null) {
			return "";
		}
		return toStringHelper(root);
	}

	private String toStringHelper(NodoVP<T> node) {
		StringBuilder sb = new StringBuilder();
		sb.append(node.getInfo());

		ArrayList<NodoVP<T>> children = getChildren(node);
		if (!children.isEmpty()) {
			sb.append("[");

			for (int i = 0; i < children.size(); i++) {
				sb.append(toStringHelper(children.get(i)));
				if (i < children.size() - 1) {
					sb.append(", ");
				}
			}
			sb.append("]");
		} else {
			sb.append("[ ]");
		}
		return sb.toString();
	}

	/** Method used to test the tree implementation */
	public static void main(String[] args) {

		AlberoVP<String> tree = new AlberoVP<>();

		System.out.println("=== Testing Tree Implementation ===");

		System.out.println("\n1. Inserting root 'Claudia'");
		NodoVP<String> claudia = tree.insertRoot("Claudia");
		System.out.println("Root: " + tree.getRoot().getInfo());
		System.out.println("Number of nodes: " + tree.getNumberOfNodes());

		System.out.println("\n2. Adding children to Claudia");
		NodoVP<String> marco = tree.addChild(claudia, "Marco");
		NodoVP<String> luca = tree.addChild(claudia, "Luca");
		NodoVP<String> giulia = tree.addChild(claudia, "Giulia");

		System.out.println("Children of Claudia:");
		for (NodoVP<String> child : tree.getChildren(claudia)) {
			System.out.println("  - " + child.getInfo());
		}

		System.out.println("\n3. Adding more nodes");
		NodoVP<String> silvia = tree.addChild(marco, "Silvia");
		NodoVP<String> ugo = tree.addChild(marco, "Ugo");
		NodoVP<String> andrea = tree.addChild(giulia, "Andrea");
		NodoVP<String> gianna = tree.addChild(giulia, "Gianna");
		NodoVP<String> carlo = tree.addChild(andrea, "Carlo");

		System.out.println("Total nodes: " + tree.getNumberOfNodes());
		System.out.println("Tree height: " + tree.getHeight());
		System.out.println("Number of leaves: " + tree.getNumberOfLeaves());

		System.out.println("\n4. Testing node levels");
		System.out.println("Level of Claudia: " + tree.getLevel(claudia));
		System.out.println("Level of Marco: " + tree.getLevel(marco));
		System.out.println("Level of Silvia: " + tree.getLevel(silvia));
		System.out.println("Level of Carlo: " + tree.getLevel(carlo));

		System.out.println("\n5. Testing traversals");
		System.out.println("Depth-first traversal:");
		for (NodoVP<String> node : tree.depthFirstTraversal()) {
			System.out.print(node.getInfo() + " ");
		}
		System.out.println();

		System.out.println("Breadth-first traversal:");
		for (NodoVP<String> node : tree.breadthFirstTraversal()) {
			System.out.print(node.getInfo() + " ");
		}
		System.out.println();

		System.out.println("\n6. String representation");
		System.out.println(tree.toString());

		System.out.println("\n7. Changing content");
		System.out.println("Before: " + ugo.getInfo());
		tree.setContent(ugo, "Ugo Modified");
		System.out.println("After: " + ugo.getInfo());

		System.out.println("\n8. Testing new root insertion");
		NodoVP<String> newRoot = tree.insertNewRoot("SuperRoot");
		System.out.println("New root: " + tree.getRoot().getInfo());
		System.out.println("Old root is now child of: " + tree.getParent(claudia).getInfo());

		System.out.println("\n=== All tests completed successfully! ===");
	}

}
