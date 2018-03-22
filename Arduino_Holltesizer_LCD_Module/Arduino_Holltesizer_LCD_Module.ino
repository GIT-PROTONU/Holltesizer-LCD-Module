#include <LiquidCrystal.h>
char ch;
int Contrast=100;
// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);
String incomingstring;
void setup() 
{
  Serial.begin(9600);
  Serial.println("LCD test with PWM contrast adjustment");
  pinMode(13,OUTPUT);
  analogWrite(6,Contrast);
  // set up the LCD's number of columns and rows: 
  lcd.begin(16, 2);
  // Print a message to the LCD.
  lcd.print("HOLLTESIZER");
  analogWrite(9,99999);
    lcd.setCursor(0, 1);
  // print the number of seconds since reset:
  lcd.print("ABLETON LIVE 10");
}

void loop() 
{
  
 if (Serial.available() > 0) {
  char incoming;
  
  incoming = Serial.read(); // read the incoming byte:
  incomingstring+=incoming;
  
  if(incoming=='\n')
  {
    Serial.print("Recieved: ");
    incomingstring.remove(incomingstring.length()-1,1);
    Serial.println(incomingstring);

    if(incomingstring.charAt(0)=='#')
    {
      Serial.print("Recieved Track");
      incomingstring.remove(0,1);
      lcd.setCursor(0, 0);
      lcd.print("                ");
      lcd.setCursor(0, 0);
      lcd.print(incomingstring);
    }

    if(incomingstring.charAt(0)=='$')
    {
      Serial.print("Recieved Scene");
      incomingstring.remove(0,1);
      lcd.setCursor(0, 1);
      lcd.print("                ");
      lcd.setCursor(0, 1);
      lcd.print(incomingstring);
    }
    incomingstring="";
  }

}
}
