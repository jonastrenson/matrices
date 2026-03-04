package matrices;

import java.util.stream.IntStream;

/**
 * Elke instantie van deze klasse slaat een matrix van
 * kommagetallen op.
 * 	
 * @invar | numberOfRows() >= 0
 * @invar | numberOfColumns() >= 0
 * 
 */
public class Mutable_Matrix {
	
	
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
	 * 		 |			IntStream.range(1, numberOfRows()).allMatch(j -> result[(i*j)+ (j-1)] == getElement(i,j)))
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
	 * 		 |			IntStream.range(1, numberOfColumns()).allMatch(j -> result[(i*j)+ (j-1)] == getElement(j,i)))
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
	 * @post | toRowMajorArray() == elementen
	 * 
	 */
	public Mutable_Matrix(int numberOfRows, int numberOfColumns, double[] elementen) {
		if (elementen == null)
			throw new IllegalArgumentException("`elementen` is null");
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * @mutates | this
	 * 
	 * @post | numberOfRows() == old(numberOfRows())
	 * @post | numberOfColumns() == old(numberOfColumns())
	 * @post | IntStream.range(0, (numberOfColumns()* numberOfRows()) -1).allMatch(i -> toRowMajorArray()[i] == old(toRowMajorArray().clone())[i] * multiplier)
	 */
	public void scale(double multiplier) {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * @mutates | this
	 * 
	 * @post | other != null
	 * @post | numberOfRows() == old(numberOfRows())
	 * @post | other.numberOfRows() == old(this.numberOfRows())
	 * @post | numberOfColumns() == old(numberOfColumns())
	 * @post | other.numberOfColumns() == old(this.numberOfColumns())
	 * @post | IntStream.range(1, numberOfColumns()).allMatch(i ->
	 * 		 |		IntStream.range(1, numberOfRows()).allMatch(j -> toRowMajorArray()[i] == other.toRowMajorArray()[i] + old(this.toRowMajorArray().clone())[i]))
	 */
	public void add(Mutable_Matrix other) {
		throw new RuntimeException("Not yet implemented");
	}

}
