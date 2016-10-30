import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

public class Context {

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
}
