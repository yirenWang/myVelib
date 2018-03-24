package core.test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.station.IDGenerator;

/*
 * Test Id generation 
 * ensure the IDgenerator class allows only creation of a unique object
 */
public class IDGeneratorTest {

	/**
	 * Ensure that only a single instance of the generator is created and used
	 * gen1 increment should effect gen2 
	 */
	@Test
	public void testGenerator() {
		IDGenerator gen1 = IDGenerator.getInstance();
		IDGenerator gen2 = IDGenerator.getInstance();

		for (int i = 1; i < 5; i++) {
			assertEquals(gen1.getNextIDNumber(), i * 2 - 1);
			assertEquals(gen2.getNextIDNumber(), i * 2);
		}
	}
}
