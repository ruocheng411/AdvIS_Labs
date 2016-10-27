import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import org.omg.CORBA.Bounds;
import org.w3c.dom.css.Rect;

public class Node {
	public  Node parent;
	public ArrayList<Node> children = new ArrayList<Node>();
	public Point initPosition;
	public Point finalPosition;
	public AffineTransform affineTransform;
	public boolean visible = true;
	public Color boundsColor = new Color(0, 0, 0);
	public Color filledColor = new Color(255, 255, 255);
	public float thickness;
	public Path2D path2d;
	public Shape clip = null;
	private Rectangle bounds = null;

	public String s1;
	public String s2 = "TimesRoman";
	
	public Node() {
		// TODO Auto-generated constructor stub
		initPosition = new Point();
		finalPosition = new Point();
	}
	
	public void setVisible(boolean b) {
		visible = b;
	}
	//	paint node and paint children
	public void paint(Graphics2D graphics2d) {
		paintNode(graphics2d);
		for(int i=0; i<children.size();i++){
			children.get(i).paint(graphics2d);
		}
	}
	//	fill And Stroke Colors, etc.
	public void setContext(){
		
	}

	//	returns the bounds of this node
	public Rectangle getBounds() {

		return bounds;
	}

	public void addChild(Node node){
		node.parent = this;
		children.add(node);	
	}

	public void removeChild(int index){
		children.get(index).parent = null;
		children.remove(index);
		
	}

	public void removeChild(Node node){
		node.parent = null;
		children.remove(node);
		
	}

	public void paintNode(Graphics2D graphics2d){

	}
	
	public void setBoundsColor(Color c) {
		boundsColor = c;
	}

	public Color getBoundsColor() {
		return boundsColor;
	}
	public void setFillColor(Color c) {
		filledColor = c;
	}

	public Color getColor() {
		return filledColor;
	}
	
	public void setPosition(Point p1, Point p2) {
		initPosition = p1;
		finalPosition = p2;
	}
	
	public Shape getShape(){
		return null;
	}
	
	public void setShape(Shape s){

	}
	
	public void setShape(){

	}
	

}

