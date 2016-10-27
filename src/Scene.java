import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

public class Scene {

}

class Pencil extends PathNode {

	public Pencil() {
		// TODO Auto-generated constructor stub
		path2d = new Path2D.Float();
	}

	
}

class Erase extends PathNode {
	public Erase() {
		// TODO Auto-generated constructor stub
		path2d = new Path2D.Float();
		boundsColor = new Color(255, 255, 255);
		thickness +=4; 
	}
}

class Text extends TextNode {

	public Text(int w, int h) {
		super(w, h);
		// TODO Auto-generated constructor stub
	}
}

class Line extends ShapeNode{ 

	private Shape lineShape= new Line2D.Float(initPosition, finalPosition);
	@Override
	public void setShape(Shape s) {
		// TODO Auto-generated method stub
		super.setShape(lineShape);
	}

	
}

class Rect extends ShapeNode { 
	Dimension dimension = new Dimension(Math.abs(initPosition.x-finalPosition.x), Math.abs(initPosition.y-finalPosition.y));
	private Shape rectShape= new Rectangle(initPosition, dimension);
	
	
	@Override
	public void setShape(){
		super.setShape(rectShape);
		shape = rectShape;
	}
}

class Circle extends Drawing {
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(thickness));
		g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2),
				Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)),
				Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));
	}
}

class Oval extends Drawing {
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(thickness));
		g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
				Math.abs(y1 - y2));
	}
}

class RoundRect extends Drawing {
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(thickness));
		g2d.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2),
				Math.abs(x1 - x2), Math.abs(y1 - y2), 50, 50);
	}
}