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
	 * @invar | AOR != null
	 * @invar | rows >= 0
	 * @invar | columns >= 0
	 * @invar | AOR.length == rows
	 * @invar | IntStream.range(0, rows).allMatch(i -> AOR[i].length == columns)
	 * 
	 */
	private double[][] AOR;
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
		return (AOR.clone())[row-1][column-1];
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
		double[] result = new double[rows*columns];
		for (int i = 0; i < AOR.length; i++) {
			for (int j = 0; j < columns; j++) {
				result[(i*columns) + j] = AOR[i][j];
			}
		}
		return result;
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
		double[] result = new double[rows*columns];
		for (int i = 0; i < AOR.length; i++) {
			for (int j = 0; j < columns; j++) {
				result[(j*rows) + i] = AOR[i][j];
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
		return AOR.clone();
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
		
		// elementen omzetten in een ArrayOfRows
		double[][] AOR_dummy = new double[numberOfColumns][numberOfRows];
		double[] result = elementen.clone();
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				AOR_dummy[i][j] = result[(i*numberOfColumns) + j];
			}
		}
	
		this.AOR = AOR_dummy;
		this.rows = numberOfRows;
		this.columns = numberOfColumns;
	}
	
	/**
	 * @mutates | this
	 * 
	 * @post | numberOfRows() == old(numberOfRows())
	 * @post | numberOfColumns() == old(numberOfColumns())
	 * @post | IntStream.range(0, (numberOfColumns()* numberOfRows()) -1).allMatch(i -> toRowMajorArray()[i] == old(toRowMajorArray().clone())[i] * multiplier)
	 */
	public void scale(double multiplier) {
		double[][] AOR_dummy = AOR.clone();
		double[][] scaled_array = new double[numberOfColumns()][numberOfRows()];
		for (int i = 0; i < numberOfRows(); i++) {
			for (int j = 0; j < numberOfColumns(); j++) {
				scaled_array[i][j] = AOR_dummy[i][j] * multiplier;
			}
		}
		AOR = scaled_array;
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
		
		double[][] thisArray = AOR.clone();
	    double[][] otherArray = other.toArrayOfRows().clone();

		
		double[][] result_elementen = new double[numberOfColumns()][numberOfRows()];
		for (int i = 0; i < numberOfRows(); i++) {
			for (int j = 0; j < numberOfColumns(); j++) {
				result_elementen[i][j] = thisArray[i][j] + otherArray[i][j];
			}
		}
		AOR = result_elementen;
		
	}
	

}
