package matrices;


import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Elke instantie van deze klasse stelt een matrix van
 * kommagetallen voor.
 * 
 * @immutable
 * 	
 * @invar | numberOfRows() >= 0
 * @invar | numberOfColumns() >= 0
 * 
 */
public class Immutable_Matrix {
	
	/**
	 * 
	 * @invar | RMarray != null
	 * @invar | rows >= 0
	 * @invar | columns >= 0
	 * @invar | RMarray.length == rows*columns
	 * 
	 */
	/**
	 * @representationObject
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
	 * @post | IntStream.range(1, numberOfRows()).allMatch(i ->
	 * 		 |			IntStream.range(1, numberOfColumns()).allMatch(j -> result[((i-1)*numberOfColumns())+ (j-1)] == getElement(i,j)))
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
	 * @creates | result, ...result
	 * @post | result != null
	 * @post | result.length == numberOfRows()
	 * @post | Arrays.stream(result).allMatch(rij -> rij != null && rij.length == numberOfColumns())
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
	 * @pre | 0 <= numberOfRows
	 * @pre | 0 <= numberOfColumns
	 * @pre | elementen.length == numberOfRows * numberOfColumns
	 * 
	 * @throws IllegalArgumentException
	 * 	| elementen == null
	 * @inspects | elementen
	 * @post | this.numberOfRows() == numberOfRows
	 * @post | this.numberOfColumns() == numberOfColumns
	 * @post | Arrays.equals(this.toRowMajorArray(), elementen)
	 * 
	 */
	public Immutable_Matrix(int numberOfRows, int numberOfColumns, double[] elementen) {
		if (elementen == null)
			throw new IllegalArgumentException("`elementen` is null");
		this.RMarray = elementen.clone();
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
		double[] scaled_array = new double[RMarray.clone().length];
		for (int i = 0; i < scaled_array.length; i++) {
			scaled_array[i] = RMarray.clone()[i] * multiplier;
		}
		return new Immutable_Matrix(rows, columns, scaled_array);
	}
	
	/**
	 * @pre | M1.numberOfRows() == M2.numberOfColumns()
	 * @pre | M1.numberOfRows() == M2.numberOfColumns()
	 * 
	 * @throws IllegalArgumentException
	 * 	| M1 == null
	 * @throws IllegalArgumentException
	 * 	| M2 == null
	 * 
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
