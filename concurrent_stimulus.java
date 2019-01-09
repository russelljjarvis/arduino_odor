int bluePin=6;
int lightPulsePin=5;
int solenoidPin=10;
int odorPulsePin=11;

//int matlabData[]={0,0,0,0,0,0,0};

float matlabData2[]={0,0,0,0,0,0,0};
unsigned long previousMillis = 0;        // will store last time LED was updated
unsigned long interval_odor_on = 0;
unsigned long interval_odor_off = 0;
unsigned long interval_led_off = 0;

unsigned long interval_led_on = 0;
unsigned long temp = 0;

led_on = 1000; //ms
led_off = 4000; //ms
odor_on = 2000; //ms
odor_off = 4000; //ms 

odor_delta = odor_off - odor_on

led_delta = led_off - led_on
void setup()
{


  //Serial.begin(SERIAL,9600);



  pinMode(bluePin,OUTPUT);
  pinMode(lightPulsePin,OUTPUT);
  pinMode(solenoidPin,OUTPUT);
  pinMode(odorPulsePin,OUTPUT);
  
  /*digitalWrite(lightPulsePin,LOW);
  digitalWrite(bluePin,LOW);
  digitalWrite(odorPulsePin,LOW);
  digitalWrite(solenoidPin,LOW);
  */
 Serial.begin(9600);
 //while(!Serial || Serial.available() <= 0);
 while (Serial.available()==0)
 {
 }
 if (Serial.available()>0)
 {
  delay(2000);
 for (int i=0; i<7; i++)
  {
    
    matlabData2[i] = Serial.parseFloat();

    Serial.flush();
  }

  interval_led_on = matlabData2[0]*1000;
  interval_odor_on = matlabData2[3]*1000;
  interval_odor_off = matlabData2[4]*1000;
  interval_led_off = matlabData2[6]*1000;


 }
} 
/*
*/
void loop()
{
    //every iteration enter loop get time in milli seconds
    unsigned long currentMillis = millis();
    //unsigned long previousMillis = currentMillis;

    // gets updated every loop iteration
 
   
    if (currentMillis - previousMillis >= interval_led_on) {
      digitalWrite(lightPulsePin,HIGH);
      digitalWrite(bluePin,HIGH);
      previousMillis = currentMillis;
      temp = (previousMillis + interval_led_off);
    }
   
    // if current time minus the time led was turned on is greater than the interval
    if (currentMillis >= temp) {
      digitalWrite(lightPulsePin,LOW);
      digitalWrite(bluePin,LOW);
      previousMillis = currentMillis;
    }
        
    if (currentMillis - previousMillis >= interval_odor_on) {
      digitalWrite(odorPulsePin,HIGH);
      digitalWrite(odorPulsePin,HIGH);
      previousMillis = currentMillis;
    }
       if (currentMillis - previousMillis >= interval_odor_off) {
      digitalWrite(odorPulsePin,LOW);
      digitalWrite(odorPulsePin,LOW);
      previousMillis = currentMillis;
    }
}
