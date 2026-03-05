package matrices;

import java.util.Arrays;
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
	 * @representationObject
	 * @invar | RMarray != null
	 * @invar | rows >= 0
	 * @invar | columns >= 0
	 * @invar | RMarray.length == rows*columns
	 * 
	 */
	private double[] RMarray;
	private int rows;
	private int columns;
	
	
	/**
	 * 
	 * @inspects | this
	 * @post | result >= 0
	 * 
	 */
	public int numberOfRows() {
		return rows;
	}
	
	/**
	 * @inspects | this
	 * @post | result >= 0
	 * 
	 */
	public int numberOfColumns() {
		return columns;
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
		if (row < 0 || row > numberOfRows())
			throw new IllegalArgumentException("`row` is geen geldige waarde");
		if (column < 0 || row > numberOfColumns())
			throw new IllegalArgumentException("`column` is geen geldige waarde");
		return (RMarray.clone())[(((row-1)*columns) + column)-1];
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
		return RMarray.clone();
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
		double[] result = new double[RMarray.clone().length];
		for (int j = 0; j < columns; j++) {
			for(int i = 0; i < rows; i++) {
				result[(j*rows) + i] = RMarray.clone()[(i*columns)+j];
			}	
		}
		return result;
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
		double[][] result = new double[columns][rows];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				result[i][j] = (RMarray.clone())[(i*columns) + j];
			}
		}
		return result;
	}
	
	/**
	 * @throws IllegalArgumentException
	 * 	| elementen == null
	 * @post | this.numberOfRows() == numberOfRows
	 * @post | this.numberOfColumns() == numberOfColumns
	 * @post | Arrays.equals(toRowMajorArray(), elementen)
	 * 
	 */
	public Mutable_Matrix(int numberOfRows, int numberOfColumns, double[] elementen) {
		if (elementen == null)
			throw new IllegalArgumentException("`elementen` is null");
		this.RMarray = elementen.clone();
		this.rows = numberOfRows;
		this.columns = numberOfColumns;	}
	
	/**
	 * @mutates | this
	 * 
	 * @post | numberOfRows() == old(numberOfRows())
	 * @post | numberOfColumns() == old(numberOfColumns())
	 * @post | IntStream.range(0, (numberOfColumns()* numberOfRows()) -1).allMatch(i -> toRowMajorArray()[i] == old(toRowMajorArray().clone())[i] * multiplier)
	 */
	public void scale(double multiplier) {
		double[] scaled_array = new double[RMarray.clone().length];
		for (int i = 0; i < scaled_array.length; i++) {
			scaled_array[i] = RMarray.clone()[i] * multiplier;
		}
		RMarray = scaled_array;
	}
	
	/**
	 * 
	 * @throws IllegalArgumentException
	 *  | other == null
	 *  
	 * @throws IllegalArgumentException
	 *  | other.numberOfRows() != this.numberOfRows()
	 *  
	 *  @throws IllegalArgumentException
	 *  | other.numberOfColumns() != this.numberOfColumns()
	 * 
	 * @mutates | this
	 * 
	 * @post | numberOfRows() == old(numberOfRows())
	 * @post | other.numberOfRows() == old(this.numberOfRows())
	 * @post | numberOfColumns() == old(numberOfColumns())
	 * @post | other.numberOfColumns() == old(this.numberOfColumns())
	 * @post | IntStream.range(0, toRowMajorArray().length).allMatch(i -> this.toRowMajorArray()[i] == old(this.toRowMajorArray().clone())[i] + old(other.toRowMajorArray())[i])
	 * 
	 */
	public void add(Mutable_Matrix other) {
		if (other == null)
			throw new IllegalArgumentException("`other` is null");
		if (other.numberOfColumns() != this.numberOfColumns())
			throw new IllegalArgumentException("`other` heeft niet hetzelfde aantal kolommen als `this`");
		if (other.numberOfRows() != this.numberOfRows())
			throw new IllegalArgumentException("`other` heeft niet hetzelfde aantal rijen als `this`");
		
		double[] thisArray = this.toRowMajorArray().clone();
	    double[] otherArray = other.toRowMajorArray().clone();

		
		double[] result_elementen = new double[other.toRowMajorArray().length];
		for (int i = 0; i < result_elementen.length; i++) {
			result_elementen[i] = thisArray[i] + otherArray[i];
		}
		RMarray = result_elementen;
		
	}
	

}
