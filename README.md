# AdvIS_Labs

PhotoBrowser let you organise and annotate photos.

PhotoBrowser.java is the main function. Select it to build this application.

the complete feature lists:

. Load multiple photos

--It can load several photos at one time but just show the last one. You can finish the one displayed then delete it to show the next one.

-- limit only to image files

Use the fileChooser to select file

. Add a photo frame to the photo.

. Photo display.
Shows a background image when the photo doesn’t cover the entire extent of the component.

Enable scrolling if photo is  too big (drag to scroll)

Flipping to annotate
Double-clicking on the photo to choose the “flipped” mode to add some annotates

. In flipped state:
A control bar is at the top

For the menus of drawn and text strokes Hover/Pressed/Selected states of menu icon show different appearances

. Support for drawn strokes.
user can be able to draw freehand strokes by dragging the mouse on the back of the photo with the button pressed.

while it is in the process of being drawn, to give appropriate feedback to the user by different cursor

Drag to draw strokes(if straight pencil tool is selected).
Drag to draw a straight line (if straight line tool is selected).
Drag to draw a rectangle (if rectangle tool is selected).
Drag to draw an ellipse (if ellipse tool is selected).
Drag to draw an circle (if circle tool is selected).
Drag to draw an round rectangle (if round rectangle tool is selected).
Drag to delete strokes (if erase tool is selected).
Presss the tool of Border Color/ Fill Color/ Thickness to change the stroke's look like

. Support for typed text

Click one time, and type to add annotation when text tool is selected.
Automatic line break when reaching the right end.
Backspace to delete text.
Press “Enter” to jump line
Color / stroke width / text size in the control bar. Influence next created elements.

. Node
All the paintings are done using scene-graph nodes.




