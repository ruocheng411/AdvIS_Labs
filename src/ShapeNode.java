import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;

public class ShapeNode extends Node {

	public Shape shape;
	public boolean drawBounds = true;
	public boolean fillInside = true;
	
	
	@Override
	public void paintNode(Graphics2D graphics2d){
		
		Graphics2D g2d = (Graphics2D) graphics2d.create();
			if(shape == null){
				return;
			}
			if (drawBounds) {
				g2d.setPaint(boundsColor);
				g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_BEVEL));
				g2d.draw(shape);
			}
			if(fillInside){
				g2d.setPaint(filledColor);
				g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_BEVEL));
				g2d.fill(shape);
			}
			
	}
	
	@Override
	public Rectangle getBounds(){
		return getShape(). getBounds();
	}
	@Override
	public Shape getShape(){
		return shape;
	}
	@Override
	public void setShape(Shape s){
		shape = s;
	}
	@Override
	public void setShape(){
		
	}

	
}
