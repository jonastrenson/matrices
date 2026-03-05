package matrices;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Immutable_MatrixTest {
	
	Immutable_Matrix M1 = new Immutable_Matrix( 2, 2, new double[] {1,2,3,4});
	

	@Test
	void test() {
		assertEquals(M1.numberOfRows(), 2);
		assertEquals(M1.numberOfColumns(), 2);
		assertArrayEquals(M1.toColumnMajorArray(), new double[] {1,3,2,4});

		assertEquals(M1.getElement(1,1) ,1);
		assertEquals(M1.getElement(1,2) ,2);
		assertEquals(M1.getElement(2,1) ,3);
		assertEquals(M1.getElement(2,2) ,4);

		assertArrayEquals(M1.toRowMajorArray(), new double[] {1,2,3,4});
		assertArrayEquals(M1.toColumnMajorArray(), new double[] {1,3,2,4});
		assertArrayEquals(M1.toArrayOfRows(), new double[][] {{1,2},{3,4}});
		
		Immutable_Matrix M2 = M1.scaled(2);
		assertEquals(M2.getElement(1,1) ,2);
		assertEquals(M2.getElement(1,2) ,4);
		assertEquals(M2.getElement(2,1) ,6);
		assertEquals(M2.getElement(2,2) ,8);
		
		Immutable_Matrix M3 = Immutable_Matrix.plus(M1, M2);
		assertEquals(M3.getElement(1,1) ,3);
		assertEquals(M3.getElement(1,2) ,6);
		assertEquals(M3.getElement(2,1) ,9);
		assertEquals(M3.getElement(2,2) ,12);

	}

}