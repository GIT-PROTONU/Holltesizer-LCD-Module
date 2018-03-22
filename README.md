# Holltesizer-LCD-Module
Arduino and accompanying Processing code to get the currently selected Scene and Track in Ableton Live 10 on a LCD display.

Works by having a Processing program talk to Ableton 10 using the OSC message protocol to view the data on a 16 character display driven by an Arduino Uno. The two are connected using a serial port and the OSC protocol withing Processing is handeld by the OSCP5 Library from Andreas, thanks on you mate! Coincidentally the libary for the buttons and the dropdown list is also by him (ControlP5).

## How to Use
First upload the supplied Arduino Code to your Arduino Uno, Connect the 16 character LCD as shown in the diagram below. The code could be easely modefied to use different screens or devices, as is just intereperts the serial data coming from the computer.

<img src="https://camo.githubusercontent.com/2bb8f4688269b6e60599c802cd834f8d6cd36f5f/687474703a2f2f312e62702e626c6f6773706f742e636f6d2f2d615372334d577a454c4b632f5648436e485168527168492f41414141414141415342592f4b66314f50567a6f43784d2f73313630302f6c636425324261726475696e6f5f776974686f75745f706f74656e74696f6d65746572312e6a7067" alt="alt text" width="auto" height="400">
