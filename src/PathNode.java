import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.omg.CORBA.Bounds;

public class PathNode extends Node {

//	public PathNode() {
//		// TODO Auto-generated constructor stub
//		
//	}

	@Override
	public void paintNode (Graphics2D g2d) {
		g2d.setPaint(boundsColor);
		g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		g2d.draw(path2d);

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

	}

	@Override
	public Rectangle getBounds(){
		return getBounds();
	}


}
