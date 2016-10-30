import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

import node.PathNode;
import node.ShapeNode;
import node.TextNode;

public class GraphicComponent {

}

class PencilNode extends PathNode {

	public PencilNode() {
		// TODO Auto-generated constructor stub
		super();
		path2d = new Path2D.Float();
	}


}

class EraseNode extends PathNode {
	public EraseNode() {
		// TODO Auto-generated constructor stub
		path2d = new Path2D.Float();
	}
}

class Text extends TextNode {

	public Text(int w, int h) {
		super(w, h);
		// TODO Auto-generated constructor stub
	}

}


class LineNode extends ShapeNode { 

	@Override
	public void paintNode(Graphics2D g2d){
		g2d.setPaint(boundsColor);
		g2d.setStroke(new BasicStroke((float) thickness, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		g2d.drawLine(initPosition.x, initPosition.y, finalPosition.x, finalPosition.y);

	}
	@Override
	public Shape getShape() {
		return new Line2D.Double(initPosition.x, initPosition.y, finalPosition.x, finalPosition.y);
	}
}

class RectNode extends ShapeNode { 
	@Override
	public void paintNode(Graphics2D g2d){
		g2d.setPaint(boundsColor);
		g2d.setStroke(new BasicStroke((float) thickness, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		g2d.drawRect(Math.min(initPosition.x,finalPosition.x), Math.min(initPosition.y,finalPosition.y),
				Math.abs(initPosition.x-finalPosition.x), Math.abs(initPosition.y-finalPosition.y));
		if(fillInside){
			g2d.setPaint(filledColor);
			g2d.fillRect(initPosition.x, initPosition.y,
					Math.abs(initPosition.x-finalPosition.x),Math.abs(initPosition.y-finalPosition.y));
		}
	}
	@Override
	public Shape getShape() {
		int x = Math.min(initPosition.x, finalPosition.x);
		int y = Math.min(initPosition.y, finalPosition.y);
		int w = Math.abs(initPosition.x - finalPosition.x);
		int h = Math.abs(initPosition.y - finalPosition.y);
		return new Ellipse2D.Double(x, y, w, h);
	}
}

class CircleNode extends ShapeNode {
	@Override
	public void paintNode(Graphics2D g2d) {
		g2d.setPaint(boundsColor);
		g2d.setStroke(new BasicStroke((float) thickness));
		int x1 = initPosition.x;
		int y1 = initPosition.y;
		int x2 = finalPosition.x;
		int y2 = finalPosition.y;
		g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2),
				Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)),
				Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));
		
		if(fillInside){
			g2d.setPaint(filledColor);
			g2d.fillOval(initPosition.x, initPosition.y,
					Math.abs(initPosition.x-finalPosition.x),Math.abs(initPosition.y-finalPosition.y));
		}
	}
	@Override
	public Shape getShape() {
		int x = Math.min(initPosition.x, finalPosition.x);
		int y = Math.min(initPosition.y, finalPosition.y);
		int w = Math.abs(initPosition.x - finalPosition.x);
		int h = Math.abs(initPosition.y - finalPosition.y);
		return new Ellipse2D.Double(x, y, Math.max(w, h),Math.max(w, h));
	}
}

class OvalNode extends ShapeNode {
	@Override
	public void paintNode(Graphics2D g2d) {
		g2d.setPaint(boundsColor);
		g2d.setStroke(new BasicStroke((float) thickness));
		int x1 = initPosition.x;
		int y1 = initPosition.y;
		int x2 = finalPosition.x;
		int y2 = finalPosition.y;
		g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
				Math.abs(y1 - y2));
		if(fillInside){
			g2d.setPaint(filledColor);
			g2d.fillOval(initPosition.x, initPosition.y,
					Math.abs(initPosition.x-finalPosition.x),Math.abs(initPosition.y-finalPosition.y));
		}
	
	}
	@Override
	public Shape getShape() {
		int x = Math.min(initPosition.x, finalPosition.x);
		int y = Math.min(initPosition.y, finalPosition.y);
		int w = Math.abs(initPosition.x - finalPosition.x);
		int h = Math.abs(initPosition.y - finalPosition.y);
		return new Ellipse2D.Double(x, y, w, h);
	}
}

class RoundRectNode extends ShapeNode{
	public int cornerRadius = 30;
	
	@Override
	public void paintNode(Graphics2D g2d) {
		g2d.setPaint(boundsColor);
		g2d.setStroke(new BasicStroke((float) thickness));
		int x1 = initPosition.x;
		int y1 = initPosition.y;
		int x2 = finalPosition.x;
		int y2 = finalPosition.y;
		g2d.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2),
				Math.abs(x1 - x2), Math.abs(y1 - y2), cornerRadius, cornerRadius);
		if(fillInside){
			g2d.setPaint(filledColor);
			g2d.fillRect(initPosition.x, initPosition.y,
					Math.abs(initPosition.x-finalPosition.x),Math.abs(initPosition.y-finalPosition.y));
		}
	}
	@Override
	public Shape getShape() {
		int x = Math.min(initPosition.x, finalPosition.x);
		int y = Math.min(initPosition.y, finalPosition.y);
		int w = Math.abs(initPosition.x - finalPosition.x);
		int h = Math.abs(initPosition.y - finalPosition.y);
		return new RoundRectangle2D.Double(x, y, w, h, cornerRadius, cornerRadius);
	}	
}

class PolygonNode extends ShapeNode {
	public ArrayList<Point> corners;
	public PolygonNode(ArrayList<Point> cornersList){
		corners = cornersList;
	}
	
	@Override
	public Shape getShape(){
		Polygon polygon = new Polygon();
		for(Point p : corners){
			polygon.addPoint(p.x, p.y);
		}
		return polygon;
	}
	
	
	
	
	
	
	
}