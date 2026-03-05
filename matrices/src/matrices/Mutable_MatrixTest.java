package matrices;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Mutable_MatrixTest {

Mutable_Matrix M1 = new Mutable_Matrix( 2, 2, new double[] {1,2,3,4});
	

	@Test
	void test() {
		assertEquals(M1.numberOfRows(), 2);
		assertEquals(M1.numberOfColumns(), 2);
		assertEquals(M1.getElement(1,1) ,1);
		assertEquals(M1.getElement(1,2) ,2);
		assertEquals(M1.getElement(2,1) ,3);
		assertEquals(M1.getElement(2,2) ,4);

		assertArrayEquals(M1.toRowMajorArray(), new double[] {1,2,3,4});
		assertArrayEquals(M1.toColumnMajorArray(), new double[] {1,3,2,4});
		assertArrayEquals(M1.toArrayOfRows(), new double[][] {{1,2},{3,4}});
		
		M1.scale(2);
		assertEquals(M1.getElement(1,1) ,2);
		assertEquals(M1.getElement(1,2) ,4);
		assertEquals(M1.getElement(2,1) ,6);
		assertEquals(M1.getElement(2,2) ,8);
		assertArrayEquals(M1.toRowMajorArray(), new double[] {2,4,6,8});

		
		M1.add(M1);
		assertArrayEquals(M1.toRowMajorArray(), new double[] {4,8,12,16});
		assertEquals(M1.getElement(1,1) ,4);
		assertEquals(M1.getElement(1,2) ,8);
		assertEquals(M1.getElement(2,1) ,12);
		assertEquals(M1.getElement(2,2) ,16);
		
		// test op representationexposure 
		// test 1
		double[] myArray = new double[] {1,2,3,4};
		Mutable_Matrix M4 = new Mutable_Matrix(2,2,myArray);
		myArray = new double[] {5,6,7,8};
		assertEquals(M4.numberOfRows(), 2);
		assertEquals(M4.numberOfColumns(), 2);
		assertEquals(M4.getElement(1,1) ,1);
		assertEquals(M4.getElement(1,2) ,2);
		assertEquals(M4.getElement(2,1) ,3);
		assertEquals(M4.getElement(2,2) ,4);

		assertArrayEquals(M4.toRowMajorArray(), new double[] {1,2,3,4});
		assertArrayEquals(M4.toColumnMajorArray(), new double[] {1,3,2,4});
		assertArrayEquals(M4.toArrayOfRows(), new double[][] {{1,2},{3,4}});
						
		//test 2
		M4.add(M1);
		M1 = M4;
		assertEquals(M4.getElement(1,1) ,5);
		assertEquals(M4.getElement(1,2) ,10);
		assertEquals(M4.getElement(2,1) ,15);
		assertEquals(M4.getElement(2,2) ,20);
						


		
	}
}