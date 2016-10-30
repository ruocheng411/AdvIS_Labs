import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import node.Node;
import node.PathNode;
import node.RootNode;


public class PhotoComponent extends JComponent {
	BufferedImage image = null;
	int parentSizex;
	int parentSizey;
	int offsetx;
	int offsety;
	Image background;
	int photoIndexCurrent;
	//	String tipText[] = { "Line", "Rect",  "Circle", "Oval", 
	//			"Roundrect", "Pencil","Erase","Text","Color", "Thinck" };
	ArrayList<Photo> photoList = new ArrayList<Photo>();;
	Boolean flipped = false;
	public File backgroundPath = FileSystems.getDefault().getPath("image", "background.jpg").toFile();
	PhotoBrowser photoBrowser = null;

	ArrayList<Node> strokeList = new ArrayList<Node>();
	Node stroke;
	int currentChoice;
	int index;
	Color colorDefault = Color.black;
	Color boundsColor = new Color(0, 0, 0);
	Color filledColor = null;
	double thickness = 1.0f;
	boolean Filled = false;

	int textPosition;

	RootNode rootNode;

	public PhotoComponent(RootNode rNode){
		rootNode = rNode;
	}

	public PhotoComponent(PhotoBrowser pb) {
		// TODO Auto-generated constructor stub
		photoBrowser = pb;
		try{
			background = ImageIO.read(backgroundPath);
			revalidate();
			repaint();
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("Resources loading error");
		}
		offsetx = 0;
		offsety = 0;
		photoIndexCurrent = -1;
		PhotoMouseAdapter adapter = new PhotoMouseAdapter(this,photoBrowser);
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
		addKeyListener(new PhotoKeyAdapter(this,photoBrowser));
		setFocusable(true);

		revalidate();
		repaint();
	}

	public void init() {
		System.out.println("Parent size = "+parentSizex+"  "+parentSizey);
		offsetx = 0;
		offsety = 0;
		updateComponentSize();
		revalidate();
		repaint();

	}

	public void updateComponentSize() {
		int w;
		int h;
		System.out.println("Photo "+photoIndexCurrent);
		if(photoIndexCurrent>=0){
			image = photoList.get(photoIndexCurrent).img;
			int imgW = image.getWidth();
			int imgH = image.getHeight();
			this.setSize(imgW, imgH);
			System.out.println("photo component size "+imgW+" * "+imgH);
			if((imgW>parentSizex)&&(imgH>parentSizey)){
				offsetx = 0;
				offsety = 0;
				w = imgW;
				h = imgH;
			}else if (imgW>parentSizex) {
				offsetx = 0;
				offsety = (parentSizey-imgH)/2;
				w = imgW;
				h = parentSizey;			
			}else if (imgH>parentSizey) {
				offsety = 0;
				offsetx = (parentSizex-imgW)/2;
				w = parentSizex;
				h = imgH;
			}else {
				offsetx = (parentSizex-imgW)/2;
				offsety = (parentSizey-imgH)/2;
				w = parentSizex;
				h = parentSizey;
			}
		}else{
			w = 0;
			h = 0;
		}
		setPreferredSize(new Dimension(w, h));
	}

	public void deletePhoto() {
		// TODO Auto-generated method stub
		if (photoIndexCurrent>-1) {
			if(photoIndexCurrent<=photoList.size()-1){
				strokeList.clear();
				photoList.remove(photoIndexCurrent);
				photoBrowser.setStatusMes(" photo "+photoIndexCurrent+" has been deleted");
				photoIndexCurrent --;
				if(photoIndexCurrent>=0){;
				photoList.get(photoIndexCurrent).loadPhoto();
				}
				init();
			}else {
				System.out.println("photo choose error");
			}
			System.out.println("Opened photo number "+photoList.size());			
		}else {
			System.out.println("There is no photo.");
		}
	}

	public void addPhotos(File[] paths) {
		// TODO Auto-generated method stub
		for (int i=0; i<paths.length;++i) {
			Photo photo = new Photo(paths[i]);
			photoList.add(photo);
		}
		photoIndexCurrent = photoList.size()-1;
		loadPhoto(photoIndexCurrent);
		flipped = false;
		init();
		photoBrowser.setStatusMes(photoList.size()+" photo(s) has been opened");
	}

	public void loadPhoto(int index) {
		try
		{
			photoList.get(index).loadPhoto();
			System.out.println("Image: "+photoList.get(index).filePath);
		}catch ( Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override  
	public void paintComponent(Graphics graphics) { 
		super.paintComponent(graphics);
		paintBackground(graphics);
		if(photoIndexCurrent>-1){
			image = photoList.get(photoIndexCurrent).img;
			if ((image != null)&&(flipped == false))  {
				paintPhoto(graphics);
			}
			if ((image != null)&&flipped) {
				paintPhotoBack(graphics);
			}
		}
	}  

	private void paintBackground(Graphics graphics){
		graphics.drawImage(background, 0, 0, null);
	}

	private void paintPhoto(Graphics graphics) { 
		super.paintComponent(graphics);
		int x = offsetx;  
		int y = offsety;  
		Graphics g = (Graphics) graphics;  
		if (null == image) { 
			System.out.println("error");
			return;  
		}  
		g.drawImage(image, x, y, image.getWidth(null), image.getHeight(null),null); 
	}

	private void paintPhotoBack(Graphics graphics) { 
		super.paintComponent(graphics);
		int x = offsetx;  
		int y = offsety;  
		photoList.get(photoIndexCurrent).imgBack = (BufferedImage) createImage(image.getWidth(),image.getHeight());
		Graphics2D g2d = (Graphics2D) photoList.get(photoIndexCurrent).imgBack.getGraphics();  
		g2d.setColor(Color.white);
		g2d.fillRect(x, y, image.getWidth(), image.getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);		

		int j = 0;
		while (j < strokeList.size()) {
			strokeList.get(j).paintNode(g2d);
//			System.out.println("photoComponent "+strokeList.get(j).boundsColor+thickness);
			j++;
		}

		graphics.drawImage(photoList.get(photoIndexCurrent).imgBack, x, y, null); 

	}

	//	public void draw(Graphics2D g2d, Drawing i) {
	//		i.draw(g2d);
	//	}
	//	

	public void setDrawCursor(int i){
		currentChoice = i;
		if ((i>=0)&&(i<6)) {
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));// mouse change to cross

		}else if (i==7) {
			setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));	
		}else {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}


	public void chooseColor() {
		//		setDrawCursor(8);
		Color c = JColorChooser.showDialog(photoBrowser, "Border Color", colorDefault);
		try {
			boundsColor = c;

		} catch (Exception e) {
			// TODO: handle exception
			boundsColor = new Color(0, 0, 0);
		}
	}
	
	public void chooseFillColor() {
		//		setDrawCursor(8);
		Color c = JColorChooser.showDialog(photoBrowser, "Fill Color", colorDefault);
		try {
			filledColor = c;

		} catch (Exception e) {
			// TODO: handle exception
			filledColor = null;
			Filled = false;
		}
	}

	public void setThickness() {
		//		currentChoice = 9;
		String input;
		input = JOptionPane.showInputDialog("Please set the thickness of pen (>0)");
		try {
			thickness = Double.parseDouble(input);
		} catch (Exception e) {
			// TODO: handle exception
			thickness = 1.0f;
			System.out.println(e);
		}
		stroke.thickness =thickness;

	}


	public void createNewStroke() {

		if (flipped) {
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));// mouse change to cross
			switch (currentChoice) {
			case 0:
				stroke = new LineNode();

				break;
			case 1:
				stroke = new RectNode();
				break;
			case 2:
				stroke = new CircleNode();
				break;
			case 3:
				stroke = new OvalNode();
				break;
			case 4:
				stroke = new RoundRectNode();
				break;
			case 5:
				//				stroke = new Pencil();
				stroke = new PathNode();
				stroke.path2d = new Path2D.Double();
				stroke.setBoundsColor(new Color(0, 0, 0));
				break;
			case 6:
				stroke = new PathNode();
				stroke.path2d = new Path2D.Double();
				stroke.setBoundsColor(new Color(255, 255, 255));
				break;
			case 7:
				setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));	
				stroke = new Text(photoList.get(photoIndexCurrent).imgBack.getWidth(),photoList.get(photoIndexCurrent).imgBack.getHeight());
			default:
				break;
			}
			if(Filled){
				stroke.fillInside = true;
			}
			repaint();
		}
	}



	public void updateStrokeParameters() {
		stroke.boundsColor = boundsColor;
		//		//		stroke.type = currentChoice;
		//\
		if(Filled){
			stroke.filledColor = filledColor;
		}
		stroke.thickness = thickness;
		repaint();
	}		
}


