package node;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.spi.DirStateFactory.Result;

import org.omg.CORBA.Bounds;
import org.w3c.dom.css.Rect;


//注释

public abstract class Node {
	protected Node parent;
	protected ArrayList<Node> children;
	private AffineTransform transform;
	private double rotation = 0;
	private double scaleX = 1,scaleY = 1;
	private ContextInfo context;
	
	public Point initPosition;
	public Point finalPosition;	
	public boolean visible = true;
	public Color boundsColor = null;
	public Color filledColor = null;
	public double thickness;
	public Path2D path2d;
	public Shape clip = null;

	public String s1;
	public String s2 = "TimesRoman";
	public boolean drawBounds = true;
	public boolean fillInside = false;



	public Node() {
		// TODO Auto-generated constructor stub
		initPosition = new Point();
		finalPosition = new Point();
		transform = new AffineTransform();
		children = new ArrayList<>();
		context = new ContextInfo();
	}

	/**
	 * 
	 * @param b
	 */
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

	public abstract void paintNode(Graphics2D graphics2d);


	public void setParent(Node p){
		parent = p;
	}

	public Node getParent(){
		return parent;
	}

	public void addChild(Node node){
		node.parent = this;
		children.add(node);	
	}

	public  ArrayList<Node> getAllChildren(){
		return children;
	}

	public void removeChild(int index){
		children.get(index).parent = null;
		children.remove(index);

	}

	public void removeChild(Node node){
		node.parent = null;
		children.remove(node);	
	}

	public void removeAllChild(){
		for(Node child:children){
			child.parent = null;
		}
		children.clear();
	}


	//	returns the bounds of this node in the parent coordinate system
	public  Rectangle getBounds(){
		Rectangle rect = new Rectangle(0,0, -1, -1);
		Rectangle rectangle = transformBounds(rect, getTransform());
		for(Node child:children){
			rectangle = rectangle.union(transformBounds(child.getBounds(), getTransform()));
		}
		return rectangle;
	}


	public Rectangle getNodeBounds() {
		Rectangle rect = new Rectangle(0,0, -1, -1);
		for(Node child : children){
			rect = rect.union(child.getBounds());
		}
		return rect;
	}


	public Rectangle transformBounds(Rectangle bounds, AffineTransform transform){
		ArrayList<Point>list = new ArrayList<>(4);

		Point2D p2d = new Point2D.Double(bounds.x, bounds.y);
		Point2D ptDst = new Point2D.Double(); 
		transform.transform(p2d, ptDst);
		list.add((Point) ptDst);

		p2d = (new Point(bounds.x, bounds.y+bounds.height));
		transform.transform(p2d, ptDst);
		list.add((Point) ptDst);

		p2d = (new Point(bounds.x+bounds.width, bounds.y));
		transform.transform(p2d, ptDst);
		list.add((Point) ptDst);

		p2d = (new Point(bounds.x+bounds.width, bounds.y+bounds.height));
		transform.transform(p2d, ptDst);
		list.add((Point) ptDst);

		Rectangle rectangle = new Rectangle(0, 0, -1, -1);
		for(Point p:list){
			rectangle.add(p);
		}
		return rectangle;
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

	public Color getFilledColor() {
		return filledColor;
	}

	public void setPosition(Point p1, Point p2) {
		initPosition = p1;
		finalPosition = p2;
	}

	public double getRotation(){
		return rotation;
	}

	public void setRotation(double r){
		rotation = r;
		updateTransform();
	}

	public AffineTransform getTransform() {
		return transform;
	}

	public void setTransform(AffineTransform trans) {
		transform = trans;
	}

	private void updateTransform() {
		transform.concatenate(AffineTransform.getTranslateInstance(initPosition.x, initPosition.y));
		transform.concatenate(AffineTransform.getRotateInstance(rotation));
		transform.concatenate(AffineTransform.getScaleInstance(scaleX, scaleY));
	}

	public void setContext() {
		context.initPosition = initPosition;
		context.finalPosition = finalPosition;	
		context.visible  = visible;
		context.boundsColor = boundsColor;
		context.filledColor = filledColor;
		context.thickness = thickness;
		context.path2d = path2d;
		context.clip = clip;
		context.drawBounds = drawBounds;
		context.fillInside = fillInside;

		context.s1 = s1;
		context.s2 = s2;
	}
	
	public ContextInfo getContext() {
		return context;
	}
}

