package node;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class ContainerNode extends Node{
	
	public ContainerNode() {
		// TODO Auto-generated constructor stub
		visible = false;
	}
	@Override
	public Rectangle getNodeBounds(){
		Rectangle rectangle = new Rectangle(0,0,-1,-1);
		Rectangle rec;
		for(int i=0;i<children.size();i++){
			rec = children.get(i).getNodeBounds();
			rectangle.union(rec);
		}
		
		return rectangle;
	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void paintNode(Graphics2D graphics2d) {
		// TODO Auto-generated method stub
		
	}

}
