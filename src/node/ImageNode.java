package node;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
/***
 * image node
 * @author zhuyali
 * drawimage
 */
public class ImageNode extends Node {
	public Image image;
	public ImageNode() {
		// TODO Auto-generated constructor stub
	}
	public ImageNode(Image img){
		image = img;
	}
	
	@Override
	public void paintNode(Graphics2D graphics2d) {
		// TODO Auto-generated method stub
		graphics2d.drawImage(image,0,0,null);
	}
	
	@Override
	public Rectangle getNodeBounds(){
		return new Rectangle(0,0,image.getWidth(null),image.getHeight(null));
	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}







