package tmge.game.base;

import util.tokens.Coordinate;
import util.tokens.CoordinateGroup;

public abstract class FallingEngine<E> extends GameEngine {
	
	public static final Coordinate GRAVITY_VECTOR = new Coordinate(1,0);
	
	protected TiledBoard<E> state;
	
	public FallingEngine(TiledBoard<E> initialState) {
		this.state = initialState;
	}

	@Override
	public boolean tick() {
		if( !gravity() ) {
			match();
		}
		return true;
	}
	
	protected CoordinateGroup unstable() {
		CoordinateGroup selected = new CoordinateGroup();
		for( int f=state.getHeight()-1; f>=0; f-- ) {
			final int y = f;
			selected.addAll(
					getFalling().getAll(c -> c.y == y).getAll(c -> {
						Coordinate target = c.plus(GRAVITY_VECTOR);
						return state.inBounds(target) && (state.get(target) == state.getDefault() || selected.contains(target));
					})
				);
		}
		return selected;
	}
	
	protected boolean gravity() {
		CoordinateGroup selected = unstable();
		
		if( selected.size() > 0 ) {
			state.shiftSelected(selected, GRAVITY_VECTOR);
			return true;
		}
		return false;
	}
	
	protected abstract CoordinateGroup getFalling();
		
	protected abstract boolean match();
	
	protected abstract CoordinateGroup getMatches();
}