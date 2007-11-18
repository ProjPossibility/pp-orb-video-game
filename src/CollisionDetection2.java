
public class CollisionDetection2 {
Player player;
	
	public CollisionDetection2(Player p){
		player = p;
	}
	
	public boolean collideWithLine (Line line){
		double a = 0;
		double hypotenuse = 0;
		double distance = 0;
		double incomingAngle = 0;
		
		Line hypo = new Line(line.getEndP(), new DPosition(player.getMainPosition().x.doubleValue(), player.getMainPosition().y.doubleValue()));
		
		a = line.length();
		hypotenuse = hypo.length();
		distance = Math.sqrt(Math.pow(hypotenuse,2)- Math.pow(a,2)/4);
		
		if (distance > player.getRadius())//make sure we have radius for player
			return false;
		else {
			try {
			//calculate the incoming angle
				incomingAngle = Math.atan(player.getSpeed().x/player.getSpeed().y);
			} catch (ArithmeticException e)
			{
				incomingAngle = Math.PI/2;
			}
			//change the speed of the player according to the new coordinate.
			double newSpeedX = 0;
			double newSpeedY = 0;
			
			newSpeedX = (double)player.getSpeed().x * Math.cos(incomingAngle)+ (double)player.getSpeed().y * Math.sin(incomingAngle);
			newSpeedY = (double)player.getSpeed().x * (-1) * Math.sin(incomingAngle) + (double)player.getSpeed().y * Math.cos(incomingAngle);
			
			//bouncing speed
			double bouncingSpeedX = 0;
			double bouncingSpeedY = 0;
			
			//when it bounce, only the Y speed change the direction, but the X Speed does not change
			bouncingSpeedX = newSpeedX;
			bouncingSpeedY = (-1)* newSpeedY;
			
			//set the new speed of the ball
			player.setSpeedX((int)Math.round(bouncingSpeedX));
			player.setSpeedY((int)Math.round(bouncingSpeedY));
			
			player.update(true,false);
			player.update(true, false);
			
			
			return true;
		}
	}
	public Boolean genericCollision(Position pos, int size){
		if(Math.abs(player.getMainPosition().x - pos.x) < size/2 + player.getRadius() && Math.abs(player.getMainPosition().y - pos.y) < size/2 + player.getRadius() ){
			return true;
		}
		else{
			return false;
		}
	}
			

}