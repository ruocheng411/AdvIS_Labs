package node;


import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
/***
 * save the contxet infotmation of node
 * @author zhuyali
 *
 */
public class ContextInfo {
	private AffineTransform transform;
	private double rotation;
	private double scaleX ;
	
	public Point initPosition;
	public Point finalPosition;	
	public boolean visible ;
	public Color boundsColor;
	public Color filledColor;
	public double thickness;
	public Path2D path2d;
	public Shape clip ;
	public boolean drawBounds;
	public boolean fillInside;

	public String s1;
	public String s2 ;
	
	public ContextInfo() {
		// TODO Auto-generated constructor stub
		initPosition = new Point();
		finalPosition = new Point();
		transform = new AffineTransform();	
	}
}
