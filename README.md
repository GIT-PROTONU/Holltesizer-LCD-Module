#Holltesizer-LCD-Module
Arduino and accompanying Processing code to get the currently selected Scene and Track in Ableton Live 10 on a LCD display.

Works by having a Processing program talk to Ableton 10 using the OSC message protocol to view the data on a 16 character display driven by an Arduino Uno. The two are connected using a serial port and the OSC protocol withing Processing is handeld by the OSCP5 Library from Andreas, thanks on you mate! Coincidentally the libary for the buttons and the dropdown list is also by him (ControlP5).

##How to Use
First upload the supplied Arduino Code to your Arduino Uno, Connect the 16 character LCD as shown in the diagram below. The code could be easely modefied to use different screens or devices, as is just intereperts the serial data coming from the computer.

