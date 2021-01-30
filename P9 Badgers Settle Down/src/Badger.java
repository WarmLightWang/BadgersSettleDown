/**
 * This class represents a Badger which is designed to live in a Sett. Each
 * Badger object represents a single node within a BST (known as a Sett).
 */
public class Badger {

	private Badger leftLowerNeighbor; // leftLowerNeighbor
	private Badger rightLowerNeighbor; // rightLowerNeighbor
	private final int size; // size of the badger

	/**
	 * Creates a new Badger with specified size.
	 * 
	 * @param size - The size of the newly constructed Badger object.
	 */
	public Badger(int size) {
		this.size = size;
	}

	/**
	 * Retrieves neighboring badger that is smaller than this one.
	 * 
	 * @return the left lower neighbor of current badger.
	 */
	public Badger getLeftLowerNeighbor() {
		return leftLowerNeighbor;
	}

	/**
	 * Retrieves neighboring badger that is larger than this one.
	 * 
	 * @return the right lower neighbor of current badger.
	 */
	public Badger getRightLowerNeighbor() {
		return rightLowerNeighbor;
	}

	/**
	 * Retrieves the size of this badger.
	 * 
	 * @return The size of current badger.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Retrieves the size of this badger.
	 * 
	 * @param badger - The new left lower neighbor of current badger.
	 */
	public void setLeftLowerNeighbor(Badger badger) {
		this.leftLowerNeighbor = badger;
	}

	/**
	 * Changes this badger's lower right neighbor.
	 * 
	 * @param badger - The new right lower neighbor of current badger.
	 */
	public void setRightLowerNeighbor(Badger badger) {
		this.rightLowerNeighbor = badger;
	}
}
