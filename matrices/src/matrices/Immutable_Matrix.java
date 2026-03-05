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
	 * @representationObject
	 * @invar | CMarray != null
	 * @invar | rows >= 0
	 * @invar | columns >= 0
	 * @invar | CMarray.length == rows*columns
	 * 
	 */
	private double[] CMarray;
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
		return (CMarray.clone())[(((column-1)*rows) + row)-1];
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
		double[] result = new double[CMarray.clone().length];
		for (int j = 0; j < columns; j++) {
			for(int i = 0; i < rows; i++) {
				result[(i*columns) + j] = CMarray.clone()[(j*rows)+i];
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
		return CMarray.clone();
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
				result[i][j] = (toRowMajorArray().clone())[(i*columns) + j];
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
	public Immutable_Matrix(int numberOfRows, int numberOfColumns, double[] elementen) {
		if (elementen == null)
			throw new IllegalArgumentException("`elementen` is null");
		// transform elementen to ColumnMajor
		double[] RMA = elementen.clone();
		double[] result = new double[RMA.length];
		for (int j = 0; j < numberOfColumns; j++) {
			for(int i = 0; i < numberOfRows; i++) {
				result[(j*numberOfRows) + i] = RMA[(i*numberOfColumns)+j];
			}	
		}
		this.CMarray = result;
		this.rows = numberOfRows;
		this.columns = numberOfColumns;
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
		double[] scaled_array = new double[CMarray.clone().length];
		for (int i = 0; i < scaled_array.length; i++) {
			scaled_array[i] = CMarray.clone()[i] * multiplier;
		}
		// transform scaled_array to RowMajor
		double[] result = new double[CMarray.clone().length];
		for (int j = 0; j < columns; j++) {
			for(int i = 0; i < rows; i++) {
				result[(i*columns) + j] = scaled_array[(j*rows)+i];
			}	
		}
		return new Immutable_Matrix(rows, columns, result);
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
		if (M1 == null || M2 == null)
			throw new IllegalArgumentException("`M1` of `M2` is null");
		double[] result_elementen = new double[M1.toRowMajorArray().length];
		for (int i = 0; i < result_elementen.length; i++) {
			result_elementen[i] = M1.toRowMajorArray()[i] + M2.toRowMajorArray()[i];
		}
		return new Immutable_Matrix(M1.rows, M1.columns, result_elementen);	
	
	}
}
