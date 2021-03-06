import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testNewPosition() throws Exception {

		Position position = new Position();
		assertEquals('x', position.turn);
		assertEquals("         ", position.toString());
	}

	@Test
	public void testMove() throws Exception {
		Position position = new Position().move(1);
		assertEquals('o', position.turn);
		assertEquals(" x       ", position.toString());
	}

	@Test
	public void testUnmove() throws Exception {
		Position position = new Position().move(1).unmove(1);
		assertEquals('x', position.turn);
		assertEquals("         ", position.toString());
	}
	@Test
	public void testpossibleMoves() throws Exception {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < Position.SIZE; i++) {
			list.add(i);
		}
		list.remove(new Integer(1));
		list.remove(new Integer(2));
		assertEquals(list, new Position().move(1).move(2).possibleMoves());
	}
	@Test
	public void testIsWinFor() throws Exception {
		assertFalse(new Position ().isWinFor('x'));
		assertTrue(new Position ("xxx      ", 'x').isWinFor('x'));
		assertTrue(new Position (
				  "x  "
				+ "x  "
				+ "x  ", 'x').isWinFor('x'));
		assertTrue(new Position (
				  "o  "
				+ " o "
				+ "  o", 'x').isWinFor('o'));
		assertTrue(new Position (
				  "  o"
				+ " o "
				+ "o  ", 'x').isWinFor('o'));
	}
	
	@Test
	public void testMinimax() throws Exception {
		assertEquals( 6, new Position("xxx      ", 'x').Minimax());
		assertEquals(-6, new Position("ooo      ", 'o').Minimax());
		assertEquals( 0, new Position("xoxxoxoxo", 'x').Minimax());
		assertEquals( 6, new Position("xx       ", 'x').Minimax());
		assertEquals(-6, new Position("oo       ", 'o').Minimax());
		assertEquals( 0, new Position().Minimax());

	}
	
	@Test
	public void testBestMove() throws Exception {
		assertEquals(2, new Position("xx      ", 'x').bestmove());
		assertEquals(2, new Position("oo      ", 'o').bestmove());
	}
	
	@Test
	public void testIsGameEnd() throws Exception {
		assertFalse(new Position().isGameEnd());
		assertTrue(new Position("xxx      ", 'x').isGameEnd());
		assertTrue(new Position("ooo      ", 'o').isGameEnd());
		assertTrue(new Position("xoxxoxoxo", 'x').isGameEnd());
	}
}
