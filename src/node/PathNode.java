package node;
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

import javax.sound.midi.VoiceStatus;

import org.omg.CORBA.Bounds;
/***
 * path node
 * @author zhuyali
 * paint path2d
 */
public class PathNode extends Node {
	

	@Override
	public void paintNode (Graphics2D g2d) {
		g2d.setPaint(boundsColor);
		g2d.setStroke(new BasicStroke((float) thickness, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);
		g2d.draw(path2d);

	}

	@Override
	public Rectangle getNodeBounds(){
		return getNodeBounds();
	}
	@Override
	public void setBoundsColor(Color c){
		boundsColor = c;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}




}
