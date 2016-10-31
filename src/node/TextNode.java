package node;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class TextNode extends Node {

//	private Rectangle bounds;
	private Font font;
	protected int width;
	protected int height;
//	public Color boundColor;
//	public double thickness;
	
	public TextNode(int w, int h) {
		// TODO Auto-generated constructor stub
//		s1 = "";
		width = w;
		height = h;
		System.out.println("TextNode line 19");
	}

	public TextNode(String t,int w, int h){
		s1 = t;	
		width = w;
		height = h;
	}
	
	public void setTextFont(String string) {
		s2 = string;
		font  = new Font(s2, Font.PLAIN, ((int) thickness) * 18);
		
	}
	
	public void setThickness(double thickenss) {
		this.thickness = thickenss;
		System.out.println("thickness "+ thickness);
	}

	@Override 
	public void paintNode(Graphics2D graphics2d){

		int x1 = initPosition.x;
		int y1 = initPosition.y;
		int x2 = finalPosition.x;
		int y2 = finalPosition.y;
		
		Graphics2D g2d = (Graphics2D) graphics2d.create();

		g2d.setPaint(boundsColor);
//		g2d.setFont(font);
		g2d.setFont(new Font(s2, Font.PLAIN, ((int) thickness) * 18));
//		g2d.setTransform(affineTransform);
		if (s1 != null){
			FontMetrics fontMetrics = g2d.getFontMetrics();
			int stringH = fontMetrics.getHeight();
			int stringW = fontMetrics.stringWidth(s1);
//			System.out.println(x1 + stringW);
			//			System.out.println(width);

			int space = width-x1-5;
			String string = s1;

			if(stringW<=space){
				int index = 0;
				boolean hasTab = false;
				while (fontMetrics.stringWidth(string)>0) {
					for(int i=0; i<string.length();i++){
						hasTab = false;
						if((string.charAt(i) == '\n')){
							hasTab = true;
							String sPrint = string.substring(0, i+1);
							g2d.drawString(sPrint, x1, y1+stringH*index);
							string = string.substring(i+1, string.length());
//							System.out.println("press return");
							index++;
							break;
						}
						//					g2d.drawString(s1, x1, y1);
					}
					if(!hasTab){
						g2d.drawString(string, x1, y1+stringH*index);
//						System.out.println("did not have return");
						break;
					}
				}
			}else{
				string = s1;
				int index = 0;
				while (fontMetrics.stringWidth(string)> 0) {
					int pos1 = 0;
					int pos2 = 0;
					int pos3 = 0;

					System.out.println(fontMetrics.stringWidth(string)+" string " +string);
					for(int i=0;i<=string.length();i++){

						if((i<string.length())&&(string.charAt(i) == '\n')){
							//							String sPrint = string.substring(0, i+1);
							//							g2d.drawString(sPrint, x1, y1+stringH*index);
							//							string = string.substring(i+1, string.length());
							pos3 = i;
							System.out.println("tab or return "+pos3);
							break;
						}

						String line = string.substring(0, i);
						if(fontMetrics.stringWidth(line)>space){
							while((i<string.length())&&(string.charAt(i)==' ')){
								i++;
							}
							pos1 = i;
							break;
						}
					}

					for(int i=pos1-1;i>0;i--){
						if(string.charAt(i)== ' '){
							pos2 = i;
							break;
						}
					}
					if (pos3 != 0) {
						String sPrint = string.substring(0, pos3+1);
						g2d.drawString(sPrint, x1, y1+stringH*index);
						string = string.substring(pos3+1, string.length());
						index++;
					}
					else if (pos2 != 0) {
						String sPrint = string.substring(0, pos2+1);
						g2d.drawString(sPrint, x1, y1+stringH*index);
						string = string.substring(pos2+1, string.length());
						index++;
						//						System.out.println("pos2 "+sPrint);
					}else if(pos1 !=0){
						String sPrint = string.substring(0,pos1-1);
						g2d.drawString(sPrint, x1, y1+stringH*index);
						string = string.substring(pos1-1, string.length());
						index++;
					}else {
						break;
					}



				}
				g2d.drawString(string, x1, y1+stringH*index);
			}



		}
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}


}
