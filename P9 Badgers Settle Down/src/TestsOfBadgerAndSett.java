import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The class is used to test the functions of Badger and Sett class. This class
 * has multiple test methods, each test focuses on a specific method, check
 * whether the result is exactly what we expect.
 */
public class TestsOfBadgerAndSett {

	/**
	 * @return true when all Badger tests pass, false otherwise.
	 */
	public static boolean runAllBadgerTests() {
		boolean passed = true; // to mark the status of the test
		if (!testBadgerConstructor()) {
			System.out.println("testBadgerConstructor failed");
			passed = false;
		}
		if (!testGetLeftLowerNeighbor()) {
			System.out.println("testGetLeftLowerNeighbor failed");
			passed = false;
		}
		if (!testGetRightLowerNeighbor()) {
			System.out.println("testGetRightLowerNeighbor failed");
			passed = false;
		}
		return passed;
	}

	/**
	 * test the functionality of constructor of Sett class
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testBadgerConstructor() {
		Badger badger = new Badger(1);
		if (badger.getSize() != 1) // size should be 1
			return false;
		return true;
	}

	/**
	 * test the functionality of getLeftLowerNeighbor
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testGetLeftLowerNeighbor() {
		Badger badger1 = new Badger(1);
		Badger badger3 = new Badger(3);
		Badger badger5 = new Badger(5);
		badger5.setLeftLowerNeighbor(badger3);
		badger3.setLeftLowerNeighbor(badger1);
		// left child of the left child of the root should be 1
		if (badger5.getLeftLowerNeighbor().getLeftLowerNeighbor().getSize() != 1)
			return false;
		return true;
	}

	/**
	 * test the functionality of getRightLowerNeighbor
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testGetRightLowerNeighbor() {
		Badger badger5 = new Badger(5);
		Badger badger7 = new Badger(7);
		Badger badger9 = new Badger(9);
		badger5.setRightLowerNeighbor(badger7);
		badger7.setRightLowerNeighbor(badger9);
		// right child of the right child of the root should be 9
		if (badger5.getRightLowerNeighbor().getRightLowerNeighbor().getSize() != 9)
			return false;
		return true;
	}

	/**
	 * @return true when all Sett tests pass, false otherwise.
	 */
	public static boolean runAllSettTests() {
		boolean passed = true; // to mark the status of the test
		if (!testSettConstructor()) {
			System.out.println("testSettConstructor failed");
			passed = false;
		}
		if (!testGetTopBadger()) {
			System.out.println("testGetTopBadger failed");
			passed = false;
		}
		if (!testIsEmpty()) {
			System.out.println("testIsEmpty failed");
			passed = false;
		}
		if (!testSettleBadger()) {
			System.out.println("testSettleBadger failed");
			passed = false;
		}
		if (!testFindBadger()) {
			System.out.println("testFindBadger failed");
			passed = false;
		}
		if (!testCountBadger()) {
			System.out.println("testCountBadger failed");
			passed = false;
		}
		if (!testGetAllBadgers()) {
			System.out.println("testGetAllBadgers failed");
			passed = false;
		}
		if (!testGetHeight()) {
			System.out.println("testGetHeight failed");
			passed = false;
		}
		if (!testGetLargestBadger()) {
			System.out.println("testGetLargestBadger failed");
			passed = false;
		}
		if (!testClear()) {
			System.out.println("testClear failed");
			passed = false;
		}
		return passed;
	}

	/**
	 * test the functionality of constructor of Sett class
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testSettConstructor() {
		Sett sett = new Sett();
		if (!sett.isEmpty()) // isEmpty should return true
			return false;
		return true;
	}

	/**
	 * test the functionality of getTopBadger()
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testGetTopBadger() {
		Sett sett = new Sett();
		if (sett.getTopBadger() != null) // getTopBadger should return null
			return false;
		return true;
	}

	/**
	 * test the functionality of isEmpty()
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testIsEmpty() {
		Sett sett = new Sett();
		if (!sett.isEmpty()) // isEmpty should return true
			return false;
		return true;
	}

	/**
	 * test the functionality of settleBadger()
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testSettleBadger() {
		Sett sett = new Sett();
		sett.settleBadger(5);
		if (sett.getTopBadger().getSize() != 5)
			return false;
		boolean passed = false;
		try {
			sett.settleBadger(5);
		} catch (IllegalArgumentException e) { // we should get and catch this exception
			if (e.getMessage().equals("WARNING: failed to settle the badger with size 5, as there is "
					+ "already a badger with the same size in this sett"))
				passed = true;
		} catch (Exception e) {
			passed = false;
		}
		return passed;
	}

	/**
	 * test the functionality of findBadger()
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testFindBadger() {
		Sett sett = new Sett();
		sett.settleBadger(5);
		boolean passed = false;
		try {
			sett.findBadger(10);
		} catch (NoSuchElementException e) { // we should get and catch this exception
			if (e.getMessage().equals("WARNING: failed to find a badger with size 10 in the sett"))
				passed = true;
		} catch (Exception e) {
			passed = false;
		}
		try {
			if (sett.findBadger(5).getSize() != 5) // found size should be 5 and no exception is thrown
				passed = false;
		} catch (Exception e) {
			passed = false;
		}
		return passed;
	}

	/**
	 * test the functionality of countBadger()
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testCountBadger() {
		Sett sett = new Sett();
		sett.settleBadger(5);
		sett.settleBadger(1);
		sett.settleBadger(3);
		if (sett.countBadger() != 3) // count should be 3
			return false;
		return true;
	}

	/**
	 * test the functionality of getAllBadgers()
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testGetAllBadgers() {
		Sett sett = new Sett();
		sett.settleBadger(5);
		sett.settleBadger(1);
		sett.settleBadger(3);
		sett.settleBadger(9);
		sett.settleBadger(7);
		ArrayList<Badger> allBadgers = (ArrayList<Badger>) sett.getAllBadgers();
		if (allBadgers.get(0).getSize() != 1 || allBadgers.get(1).getSize() != 3 || allBadgers.get(2).getSize() != 5
				|| allBadgers.get(3).getSize() != 7 || allBadgers.get(4).getSize() != 9) // array should contain exactly
																							// these elements
			return false;
		return true;
	}

	/**
	 * test the functionality of getHeight()
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testGetHeight() {
		Sett sett = new Sett();
		sett.settleBadger(5);
		sett.settleBadger(1);
		sett.settleBadger(3);
		sett.settleBadger(9);
		sett.settleBadger(7);
		if (sett.getHeight() != 3) // height should be 3
			return false;
		return true;
	}

	/**
	 * test the functionality of getLargestBadger()
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testGetLargestBadger() {
		Sett sett = new Sett();
		sett.settleBadger(5);
		sett.settleBadger(1);
		sett.settleBadger(3);
		sett.settleBadger(9);
		sett.settleBadger(7);
		if (sett.getLargestBadger().getSize() != 9) // largest should be 9
			return false;
		return true;
	}

	/**
	 * test the functionality of clear()
	 * 
	 * @return true if pass the test, false otherwise.
	 */
	private static boolean testClear() {
		Sett sett = new Sett();
		sett.settleBadger(5);
		sett.settleBadger(1);
		sett.settleBadger(3);
		sett.settleBadger(9);
		sett.settleBadger(7);
		sett.clear();
		if (sett.getTopBadger() != null) // sett should be empty
			return false;
		return true;
	}

	/**
	 * Testing main. Runs each test and prints which (if any) failed. If no problem
	 * occurs, print a single line showing "All tests passed!".
	 */
	public static void main(String[] args) {
		boolean passed = true;
		if (!runAllBadgerTests()) {
			System.out.println("runAllBadgerTests failed");
			passed = false;
		}
		if (!runAllSettTests()) {
			System.out.println("runAllSettTests failed");
			passed = false;
		}
		// If no problem occurs, print a single line showing "All tests passed!".
		if (passed)
			System.out.println("All tests passed!");
	}
}
