import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

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
	public Rectangle getBounds(){
		return new Rectangle(0,0,image.getWidth(null),image.getHeight(null));
	}
}







