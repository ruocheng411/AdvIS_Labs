import java.awt.Rectangle;

public class ContainerNode extends Node{
	
	public ContainerNode() {
		// TODO Auto-generated constructor stub
		visible = false;
	}
	@Override
	public Rectangle getBounds(){
		Rectangle rectangle = new Rectangle(0,0,-1,-1);
		Rectangle rec;
		for(int i=0;i<children.size();i++){
			rec = children.get(i).getBounds();
			rectangle.union(rec);
		}
		
		return rectangle;
	}
}
