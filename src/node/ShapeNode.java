package node;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;

public class ShapeNode extends Node {

	Shape shape;
	
	@Override
	public void paintNode(Graphics2D graphics2d){
		
	}
		
//		Graphics2D g2d = (Graphics2D) graphics2d.create();
//			if(shape == null){
//				return;
//			}
//			if (drawBounds) {
//				g2d.setPaint(boundsColor);
//				g2d.setStroke(new BasicStroke((float) thickness, BasicStroke.CAP_ROUND,
//						BasicStroke.JOIN_BEVEL));
//				g2d.draw(shape);
//				System.out.println("shapeNode "+shape+" "+boundsColor);
//			}
//			if(fillInside){
//				g2d.setPaint(filledColor);
//				g2d.setStroke(new BasicStroke((float) thickness, BasicStroke.CAP_ROUND,
//						BasicStroke.JOIN_BEVEL));
//				g2d.fill(shape);
//			}
			
//	}
	
	@Override
	public Rectangle getNodeBounds(){
		return getShape(). getBounds();
	}

	public Shape getShape(){
		return shape;
		
	}

	public void setBoundsColor(Color c){
		boundsColor = c;
	}

	
}
