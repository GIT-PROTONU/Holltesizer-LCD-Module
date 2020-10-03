# Holltesizer-LCD-Module
Arduino and accompanying Processing code to get the currently selected Scene and Track in Ableton Live 10 on a LCD display.

Works by having a Processing program talk to Ableton 10 using the OSC message protocol to view the data on a 16 character display driven by an Arduino Uno. The two are connected using a serial port and the OSC protocol withing Processing is handeld by the OSCP5 Library from Andreas, thanks on you mate! Coincidentally the libary for the buttons and the dropdown list is also by him (ControlP5).

## How to Use
First upload the supplied Arduino Code to your Arduino Uno, Connect the 16 character LCD as shown in the diagram below. The code could be easely modefied to use different screens or devices, as is just intereperts the serial data coming from the computer.

<img src="https://protonu.com/img/lcd_wiring.png" alt="Arduino LCD Wiring" width="auto" height="400">

Now upload the Arduino code. Now <a href="https://github.com/GIT-PROTONU/Holltesizer-LCD-Module/tree/master/Processing_Holltesizer_LCD_Module">download</a>  the exported Processing program (windows and mac), the source code is also supplied so you could edit it. Make sure you include the OSCP5 and ControlP5 library in Processing if you want to compile your own version.

The fields highlighted in red are the currently selected Track and Scene, this data will be sent to the LCD screen.
<img src="https://protonu.com/_img/screenshot_ableton.png" alt="Ableton Live 10 Screenshot" width="auto" height="400">
<img src="https://protonu.com/_img/lcd_photo.jpg" alt="LCD Screen with selected Track and Scene" width="auto" height="400">
<hr>

# Extra
The Holltesizer is a custom Ableton Live 10 controller made with two APC's from Akai, the LCD module described here and a keyboard. Holltesizer is a collaboration between Hollt, an upcoming DJ and Producer from the Netherlands and PROTONU a technical research and development company.

The Processing code was modified by Ibrahim Elfaramawy from an example of the OSCP5 libary.
