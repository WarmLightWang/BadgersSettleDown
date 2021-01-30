import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class represents a Sett, where a group of Badgers live together. Each
 * Sett is organized as a BST of Badger nodes.
 */
public class Sett {

	private Badger topBadger; // store the root of the BST, initially null

	/**
	 * Constructs an empty Sett.
	 */
	public Sett() {

	}

	/**
	 * Retrieve the top Badger within this Sett (the one that was settled first).
	 * 
	 * @return The Badger living on the top of the current Sett.
	 */
	public Badger getTopBadger() {
		return topBadger;
	}

	/**
	 * Checks whether this Sett is empty.
	 * 
	 * @return true if this Sett is empty, false otherwise.
	 */
	public boolean isEmpty() {
		if (topBadger == null) // check whether topBadger is null
			return true;
		return false;
	}

	/**
	 * Creates a new Badger object with the specified size, and inserts them into
	 * this Sett (BST). You must implement the naive insert algorithm discussed in
	 * zybooks and lecture: which only preserves the order property of each node,
	 * and is not guaranteed to result in balance.
	 * 
	 * @param size - The size of the new Badger that will be settled.
	 * @throws java.lang.IllegalArgumentException - When a Badger with the specified
	 *                                            size already exists within this
	 *                                            Sett. The message in this
	 *                                            exception must read: "WARNING:
	 *                                            failed to settle the badger with
	 *                                            size {size}, as there is already a
	 *                                            badger with the same size in this
	 *                                            sett", where {size} needs to be
	 *                                            replaced with the specified size
	 *                                            parameter.
	 */
	public void settleBadger(int size) throws java.lang.IllegalArgumentException {
		Badger newBadger = new Badger(size); // convert the size to a Badger with the desired size
		settleHelper(topBadger, newBadger); // use private helper method
	}

	/**
	 * This recursive helper method is used to help settle a new Badger within this
	 * Sett.
	 * 
	 * @param current   - The current Badger (previously settled within this Sett)
	 *                  that we are considering settling the newBadger beneath
	 *                  (either to its left or right).
	 * @param newBadger - The new Badger that needs to be settled within this Sett.
	 * @throws java.lang.IllegalArgumentException -
	 */
	private void settleHelper(Badger current, Badger newBadger) throws java.lang.IllegalArgumentException {
		if (topBadger == null) { // always check root first to avoid nullPointerException
			topBadger = newBadger; // if the root is null, assign the newBadger to it
			return;
		}
		if (newBadger.getSize() == current.getSize()) // if new size already exist, throw exception
			throw new IllegalArgumentException("WARNING: failed to settle the badger with size " + newBadger.getSize()
					+ ", as there is already a badger with the same size in this sett");
		if (newBadger.getSize() > current.getSize()) {
			if (current.getRightLowerNeighbor() == null) // base case, end recursive
				current.setRightLowerNeighbor(newBadger);
			else
				settleHelper(current.getRightLowerNeighbor(), newBadger); // continue recursive
		} else {
			if (current.getLeftLowerNeighbor() == null) // base case, end recursive
				current.setLeftLowerNeighbor(newBadger);
			else
				settleHelper(current.getLeftLowerNeighbor(), newBadger); // continue recursive
		}
	}

	/**
	 * Finds a Badger of a specified size in this Sett.
	 * 
	 * @param size - The size of the Badger object to search for and return.
	 * @return The Badger found with the specified size.
	 * @throws java.util.NoSuchElementException - When there is no Badger in this
	 *                                          Sett with the specified size. The
	 *                                          message within this exception must
	 *                                          read "WARNING: failed to find a
	 *                                          badger with size {size} in the
	 *                                          sett", where {size} needs to be
	 *                                          replaced with the specified size
	 *                                          parameter.
	 */
	public Badger findBadger(int size) throws java.util.NoSuchElementException {
		return findHelper(topBadger, size); // use private helper method
	}

	/**
	 * This recursive helper method is used to help find a Badger within this Sett.
	 * 
	 * @param current - The current Badger that is the root of a (sub) tree that we
	 *                are searching for a Badger with the specified size within.
	 * @param size    - The size of the Badger object to search for and return.
	 * @return The Badger found with the specified size.
	 * @throws java.util.NoSuchElementException -
	 */
	private Badger findHelper(Badger current, int size) throws java.util.NoSuchElementException {
		if (current == null) // base case, end recursive, throw not found exception
			throw new NoSuchElementException("WARNING: failed to find a badger with size " + size + " in the sett");
		if (current.getSize() == size) // base case, end recursive
			return current;
		if (size < current.getSize())
			return findHelper(current.getLeftLowerNeighbor(), size); // continue recursive
		else
			return findHelper(current.getRightLowerNeighbor(), size); // continue recursive
	}

	/**
	 * Counts how many Badgers live in this Sett.
	 * 
	 * @return The number of Badgers living in this Sett.
	 */
	public int countBadger() {
		return countHelper(topBadger); // use private helper method
	}

	/**
	 * This recursive helper method is used to help count the number of Badgers in
	 * this Sett.
	 * 
	 * @param current - The current Badger that is the root of a (sub) tree that we
	 *                are counting the number of Badgers within.
	 * @return the number of Badgers living in the Sett rooted at the current
	 *         Badger.
	 */
	private int countHelper(Badger current) {
		if (current == null) // base case, end recursive
			return 0;
		// separately count left and right subtree use recursive
		return 1 + countHelper(current.getLeftLowerNeighbor()) + countHelper(current.getRightLowerNeighbor());
	}

	/**
	 * Gets all Badgers living in the Sett as a list in ascending order of their
	 * size: smallest one in the front (at index zero), through the largest one at
	 * the end (at index size-1).
	 *
	 * @return A list of all Badgers living in the Sett in ascending order by size.
	 */
	public java.util.List<Badger> getAllBadgers() {
		ArrayList<Badger> allBadgers = new ArrayList<Badger>(); // create a list first then process it
		getAllHelper(topBadger, allBadgers); // use private helper method, list will be modified
		return allBadgers;
	}

	/**
	 * This recursive helper method is used to help collect the Badgers within this
	 * Sett into a List.
	 *
	 * @param allBadgers - The list of all Badgers living in the Sett that is rooted
	 *                   at the current Badger node. The contents of this list
	 *                   should be in ascending order by Badger size.
	 * @param current    - The current Badger that is the root of a (sub) tree that
	 *                   we are collecting all contained Badgers within, into the
	 *                   allBadgers List.
	 */
	private void getAllHelper(Badger current, java.util.List<Badger> allBadgers) {
		if (current == null) // base case, end recursive
			return;
		getAllHelper(current.getLeftLowerNeighbor(), allBadgers);
		allBadgers.add(current); // this is in-order, add each node to the given list
		getAllHelper(current.getRightLowerNeighbor(), allBadgers);
	}

	/**
	 * Computes the height of the Sett, as the number of nodes from root to the
	 * deepest leaf Badger node.
	 *
	 * @return The depth of this Sett.
	 */
	public int getHeight() {
		return getHeightHelper(topBadger); // use private helper method
	}

	/**
	 * This recursive helper method is used to help compute the height of this Sett.
	 * 
	 * @param current - The current Badger that is the root of a (sub) tree that we
	 *                are calculating the height of.
	 * @return The height of the (sub) tree that we are calculating.
	 */
	private int getHeightHelper(Badger current) {
		if (current == null) // base case, end recursive
			return 0;
		// use max function to choose the larger height from left subtree or right
		// subtree
		return java.lang.Math.max(1 + getHeightHelper(current.getLeftLowerNeighbor()),
				1 + getHeightHelper(current.getRightLowerNeighbor()));
	}

	/**
	 * Retrieves the largest Badger living in this Sett.
	 * 
	 * @return The largest Badger living in this Sett.
	 */
	public Badger getLargestBadger() {
		Badger current = topBadger;
		if (current == null) // base case, end recursive
			return null;
		while (current.getRightLowerNeighbor() != null) // not a recursive method, so use iteration
			current = current.getRightLowerNeighbor();
		return current;
	}

	/**
	 * Empties this Sett, to no longer contain any Badgers.
	 */
	public void clear() {
		topBadger = null;
	}
}
