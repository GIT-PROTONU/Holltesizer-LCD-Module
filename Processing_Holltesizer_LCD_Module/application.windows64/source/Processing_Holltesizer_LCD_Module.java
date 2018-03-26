import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import oscP5.*; 
import netP5.*; 
import processing.serial.*; 
import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Processing_Holltesizer_LCD_Module extends PApplet {

/**
 * Processing LCD module code to get the data from Ableton 10 to the Arduino.
 * Modified Code from the oscP5plug by andreas schlegel
 * example shows how to use the plug service with oscP5.
 * the concept of the plug service is, that you can
 * register methods in your sketch to which incoming 
 * osc messages will be forwareded automatically without 
 * having to parse them in the oscEvent method.
 * that a look at the example below to get an understanding
 * of how plug works.
 * oscP5 website at http://www.sojamo.de/oscP5
 */






Serial myPort; 
ControlP5 cp5;
DropdownList d1;

OscP5 oscP5;
NetAddress myRemoteLocation;

String portName;
int serialListIndex;

int trackid=99;
int sceneid=99;
int lastTimeCheck;
int timeIntervalFlag = 1;
PImage protonu_logo;
PImage hollt_logo;

public void setup() {
  clear();
  cp5 = new ControlP5(this);
  frame.setResizable(true); 
  lastTimeCheck = millis();
  
  /* start oscP5, listening for incoming messages at port 12000 */
  oscP5 = new OscP5(this,9000);
  protonu_logo = loadImage("protonu_logo.png");
  hollt_logo = loadImage("hollt_logo.jpg");
  /* myRemoteLocation is a NetAddress. a NetAddress takes 2 parameters,
   * an ip address and a port number. myRemoteLocation is used as parameter in
   * oscP5.send() when sending osc packets to another computer, device, 
   * application. usage see below. for testing purposes the listening port
   * and the port of the remote location address are the same, hence you will
   * send messages back to this sketch.
   */
  myRemoteLocation = new NetAddress("127.0.0.1",9001);
  
  /* osc plug service
   * osc messages with a specific address pattern can be automatically
   * forwarded to a specific method of an object. in this example 
   * a message with address pattern /test will be forwarded to a method
   * test(). below the method test takes 2 arguments - 2 ints. therefore each
   * message with address pattern /test and typetag ii will be forwarded to
   * the method test(int theA, int theB)
   */
  oscP5.plug(this,"test","/test");
  
  PFont pfont = createFont("Arial",10,true); //Create a font
  ControlFont font = new ControlFont(pfont,9); //font, font-size
 
  d1 = cp5.addDropdownList("myList-d1")
          .setPosition(20, 100)
          .setSize(400, 80)
          .setHeight(400)
          .setItemHeight(30)
          .setBarHeight(30)
          .setFont(font)
          .setColorBackground(color(60))
          .setColorActive(color(255, 128))
          ;
          
      d1.getCaptionLabel().set("PORT"); //set PORT before anything is selected
      portName = Serial.list()[0]; //0 as default
      myPort = new Serial(this, portName, 9600);
}

public void test(int theA, int theB) {
  println("### plug event method. received a message /test.");
  println(" 2 ints received: "+theA+", "+theB);  
}

public void draw() {
  background(0);
  image(protonu_logo, 20, 20, 50, 50);
  image(hollt_logo, 80, 20, 50, 50);
  protonu_logo.resize(50, 50);
  hollt_logo.resize(50, 50);
  textSize(14);
fill(255);
text("select port to (re)start", 20, 90);

  if(d1.isMouseOver()) {
   d1.clear(); //Delete all the items
   for (int i=0;i<Serial.list().length;i++) {
     d1.addItem(Serial.list()[i], i); //add the items in the list
   }
  }
}

public void controlEvent(ControlEvent theEvent) { //when something in the list is selected
    myPort.clear(); //delete the port
    myPort.stop(); //stop the port
    if (theEvent.isController() && d1.isMouseOver()) {
    portName = Serial.list()[PApplet.parseInt(theEvent.getController().getValue())]; //port name is set to the selected port in the dropDownMeny
    myPort = new Serial(this, portName, 9600); //Create a new connection
    println("Serial index set to: " + theEvent.getController().getValue());
    }
}
    
/* incoming osc message are forwarded to the oscEvent method. */
public void oscEvent(OscMessage theOscMessage) {
  /* with theOscMessage.isPlugged() you check if the osc message has already been
   * forwarded to a plugged method. if theOscMessage.isPlugged()==true, it has already 
   * been forwared to another method in your sketch. theOscMessage.isPlugged() can 
   * be used for double posting but is not required.
  */  
  //if(theOscMessage.isPlugged()==false) {
  /* print the address pattern and the typetag of the received OscMessage */
   if(theOscMessage.checkAddrPattern("/live/track/select")) {
     trackid=theOscMessage.get(1).intValue();
      // print("Trackid: ");
      // println(trackid);
       oscP5.send("/live/track/name", new Object[] { new Integer(trackid)}, myRemoteLocation);
   }
      if(theOscMessage.checkAddrPattern("/live/track/name")) {
       print("Track: ");
       println(theOscMessage.get(1).stringValue());
       myPort.write("#"+theOscMessage.get(1).stringValue()+'\n');
   }
   
   if(theOscMessage.checkAddrPattern("/live/scene/state")) {
     sceneid=theOscMessage.get(0).intValue();
     //print("Sceneid: ");
     //println(sceneid);
     oscP5.send("/live/scene/name", new Object[] { new Integer(sceneid)}, myRemoteLocation);
   }
         if(theOscMessage.checkAddrPattern("/live/scene/name")) {
       print("Scene: ");
       println(theOscMessage.get(1).stringValue());
       myPort.write('$'+theOscMessage.get(1).stringValue()+'\n'); 
   }
 //  }
//theOscMessage.print();
 }
  public void settings() {  size(440,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Processing_Holltesizer_LCD_Module" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
