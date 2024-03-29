package statement.action;

import be.kuleuven.cs.som.annotate.Basic;
import expression.DoubleExpression;
import expression.E;
import statement.Action;
import worms.gui.game.IActionHandler;
import worms.model.Worm;

public class Turn extends Action{

	public Turn(int line, int column, IActionHandler handler, E angle) {
		super(line, column, handler);
		this.angle = angle;
	}

	@Basic
	public E getAngle(){
		return this.angle;
	}
	
	private final E angle;
	
	@Override
	protected void executeAction(Worm worm) {
		double angle = ((DoubleExpression) getAngle()).getValue().getDouble();
		if(worm.canTurn(angle)){
			System.out.println("Turned");
			getActionHandler().turn(worm, ((DoubleExpression) getAngle()).getValue().getDouble());
		}
		else{
			worm.getProgram().setIsExecuting(false);
			worm.getWorld().startNextTurn();
		}
	}
	
}
