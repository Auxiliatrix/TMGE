package tmge.game.bejeweled;

import tmge.game.base.GameEngine;
import tmge.game.base.Player;
import util.tokens.Coordinate;
import util.tokens.CoordinateGroup;

public class BejeweledEngine extends GameEngine {

	protected static final Coordinate GRAVITY_VECTOR = new Coordinate(1,0);
	
	protected BejeweledBoard state;
	protected Player player;
	
	public BejeweledEngine(BejeweledBoard initialState, Player player) {
		super();
		this.state = initialState;
		this.player = player;
	}

	@Override
	public boolean tick() {
		
		
		return false;
	}
	
	protected boolean gravity() {
		CoordinateGroup selected = new CoordinateGroup();
		for( int f=state.getHeight()-1; f>=0; f-- ) {
			selected.addAll(state.getAll(c -> {
				Coordinate target = c.plus(GRAVITY_VECTOR);
				return state.inBounds(target) && state.get(target) == state.getDefault();
			}));
		}
		
		if( selected.size() > 0 ) {
			state.shiftSelected(selected, GRAVITY_VECTOR);
		} else {
			
		}
	}

}