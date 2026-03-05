package matrices;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Elke instantie van deze klasse stelt een matrix van
 * kommagetallen voor.
 * 	
 * @invar | numberOfRows() >= 0
 * @invar | numberOfColumns() >= 0
 * 
 */
public class Immutable_Matrix {
	
	
	/**
	 * 
	 * @inspects | this
	 * @post | result >= 0
	 * 
	 */
	public int numberOfRows() {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * @inspects | this
	 * @post | result >= 0
	 * 
	 */
	public int numberOfColumns() {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * @throws IllegalArgumentException
	 * 	| (row < 0 || row > numberOfRows())
	 * @throws IllegalArgumentException
	 * 	| (column < 0 || row > numberOfColumns())
	 * @inspects | this
	 * 
	 * 
	 */
	public double getElement(int row, int column) {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * 
	 * @creates | result
	 * @post | result != null
	 * @post | result.length == numberOfRows() * numberOfColumns()
	 * @post | IntStream.range(1, numberOfColumns()).allMatch(i ->
	 * 		 |			IntStream.range(1, numberOfRows()).allMatch(j -> result[((i-1)*numberOfColumns())+ (j-1)] == getElement(i,j)))
	 * 
	 */
	public double[] toRowMajorArray() {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * 
	 * @creates | result
	 * @post | result != null
	 * @post | result.length == numberOfRows() * numberOfColumns()
	 * @post | IntStream.range(1, numberOfRows()).allMatch(i ->
	 * 		 |			IntStream.range(1, numberOfColumns()).allMatch(j -> result[((i-1)*numberOfColumns())+ (j-1)] == getElement(j,i)))
	 * 
	 */
	public double[] toColumnMajorArray() {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * @creates | result
	 * @post | result != null
	 * @post | result.length == numberOfRows()
	 * @post | IntStream.range(1, numberOfRows()).allMatch(i -> 
	 * 		 | 		IntStream.range(1, numberOfColumns()).allMatch(j -> result[i-1][j-1] == getElement(i,j)))
	 * 
	 */
	public double[][] toArrayOfRows() {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * @throws IllegalArgumentException
	 * 	| elementen == null
	 * @post | this.numberOfRows() == numberOfRows
	 * @post | this.numberOfColumns() == numberOfColumns
	 * @post | Arrays.equals(toRowMajorArray(), elementen)
	 * 
	 */
	public Immutable_Matrix(int numberOfRows, int numberOfColumns, double[] elementen) {
		if (elementen == null)
			throw new IllegalArgumentException("`elementen` is null");
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * @creates | result
	 * 
	 * @post | result != null
	 * @post | result.numberOfRows() == this.numberOfRows()
	 * @post | result.numberOfColumns() == this.numberOfColumns()
	 * @post | IntStream.range(1, numberOfColumns()).allMatch(i ->
	 * 		 |		IntStream.range(1, numberOfRows()).allMatch(j -> result.getElement(i,j) == this.getElement(i,j) * multiplier))
	 */
	public Immutable_Matrix scaled(double multiplier) {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * @creates | result
	 * 
	 * @post | result != null
	 * @post | result.numberOfRows() == M1.numberOfRows()
	 * @post | result.numberOfRows() == M2.numberOfRows()
	 * @post | result.numberOfColumns() == M1.numberOfColumns()
	 * @post | result.numberOfColumns() == M2.numberOfColumns()
	 * @post | IntStream.range(1, result.numberOfColumns()).allMatch(i ->
	 * 		 |		IntStream.range(1, result.numberOfRows()).allMatch(j -> result.getElement(i,j) == M1.getElement(i,j) + M2.getElement(i,j)))
	 */
	static public Immutable_Matrix plus(Immutable_Matrix M1, Immutable_Matrix M2) {
		throw new RuntimeException("Not yet implemented");
	}
	
	
	

}
