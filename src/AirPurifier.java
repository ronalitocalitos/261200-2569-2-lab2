
/*1 & 2. Description of AirPurifier class & Implementation Plan
* instance variables
isOn:boolean true = เปิดอยู่
fanSpeed:int มี4ระดับ 1,2,3,4
model:String ชื่อรุ่น
price:double ราคา
pm25density:int ค่าฝุ่น
serialNo:String เลขรุ่น
dimension:String ขนาด cm x cm x cm
make:String แบรนด์
mode:Mode โหมดมี sleep power  manual auto
color:String สี
weight:String น้ำหนัก
madeIn:String ผลิตที่ไหน
voltConsumption:int แรงดันไฟฟ้าที่ใช้

* instance method
//turnOn():void เมื่อเรียก isOn = true
//turnOff():void เมื่อเรียก isOn = false
//setFanSpeed():void เช็คก่อนว่าเครื่องเปิดไหมหรือmode == manualรึป่าวถ้าไม่ return; ถ้าใช่ fanSpeed ++;ถ้าเยอะกว่า4ให้วนกลับเป็น1
//setMode(Mode mode):void เช็คก่อนว่าเครื่องเปิดไหมถ้าไม่ return;รับinput Modeมาแล้ว
ถ้าmode == sleep fanSpeed = 1และเปลี่ยนค่าthis.mode,
mode == power fanSpeed = 4และเปลี่ยนค่าthis.mode,
mode == auto ก็เปลี่ยนค่าthis.mode และปรับ fanSpeed ตามค่าฝุ่น,
mode == manual เปลี่ยนค่าthis.mode,
//showPm25():void เช็คก่อนว่าเครื่องเปิดไหมถ้าไม่ return;
ถ้าmode == sleep ให้แสดงแค่Mode,
mode == power แสดงModeและค่าpm25และfanSpeed,
mode == manualมีเลขบอกตามfanSpeedและแสดงค่าpmและMode,
mode == auto แสดงค่าpmและแสดงMode
//changePm25(int pm):void เช็คว่าpmค่าใหม่เท่าค่าเดิมให้return ถ้าไม่เท่าให้เซ็ตค่าthis.pm25Density = ค่าที่รับมาแล้วถ้าเป็นMode AUTOให้คำนวนfanSpeedใหม่

* class variables
totalNumber:static int จำนวนเครื่องทั้งหมด
totalNumberEach:static HashMap<String, Integer> จำนวนเครื่องในแต่ละรุ่นเก็บในรูปแบบhashmap

* class method
mostPopularModel():static String เช็คว่ารุ่นไหนในHashMapมีจำนวนมากสุดแล้วreturn bestModel
*/
import java.util.HashMap;
public class AirPurifier {
    boolean isOn;
    int fanSpeed;
    String model;
    double price;
    int pm25density;
    String serialNo;
    String dimension;
    String make;
    Mode mode;
    String color;
    String weight;
    String madeIn;
    int voltConsumption;
    static int totalNumber;
    static HashMap<String, Integer> totalNumberEach = new HashMap<>();

    AirPurifier(boolean isOn,
    int fanSpeed, String model, double price, int pm25density, String serialNo, String dimension,
    String make, Mode mode, String color, String weight, String madeIn, int voltConsumption){
        this.isOn = isOn;
        this.fanSpeed = fanSpeed;
        this.model = model;
        this.price = price;
        this.pm25density = pm25density;
        this.serialNo = serialNo;
        this.dimension = dimension;
        this.make = make;
        this.mode = mode;
        this.color = color;
        this.weight = weight;
        this.madeIn = madeIn;
        this.voltConsumption = voltConsumption;

        totalNumber ++;
        if (totalNumberEach.containsKey(model)) {
            int currentCount = totalNumberEach.get(model);
            totalNumberEach.put(model, currentCount + 1);
        } else {
            totalNumberEach.put(model, 1);
        }
    }

    public void turnOn(){
        isOn = true;
        IO.println("Turn on");
    }
    public void turnOff(){
        isOn = false;
        IO.println("Turn off");
    }

    public void setFanSpeed() {
        if(!isOn || this.mode != Mode.MANUAL){
            return;
        }else{
            this.fanSpeed ++;
            if(this.fanSpeed > 4){
                this.fanSpeed = 1;
            }
            IO.println("Set fan speed");
        }
    }
    public void setMode(Mode mode){
        if(!isOn){
            return;
        }
        if(mode == Mode.SLEEP){
            this.fanSpeed = 1;
            this.mode = Mode.SLEEP;
            IO.println("Set mode to sleep");
        }else if(mode == Mode.MANUAL){
            this.mode = Mode.MANUAL;
            IO.println("Set mode to manual");
        }else if(mode == Mode.POWER){
            this.fanSpeed = 4;
            this.mode = Mode.POWER;
            IO.println("Set mode to power");
        }else{
            if(pm25density < 50){
                this.fanSpeed = 1;
            }else if(pm25density < 100){
                this.fanSpeed = 2;
            }else if(pm25density < 150){
                this.fanSpeed = 3;
            }else{
                this.fanSpeed = 4;
            }
            this.mode = Mode.AUTO;
            IO.println("Set mode to auto");
        }
    }
    public void showPm25(){
        if(!isOn){
            IO.println("This device is off");
            return;
        }
        if(mode == Mode.SLEEP){
            IO.println("Mode:Sleep");
        } else if (mode == Mode.POWER) {
            IO.println("Mode:Power / PM2.5: "+this.pm25density+" / FanSpeed:"+this.fanSpeed);
        } else if (mode == Mode.MANUAL) {
            IO.println("Mode:Manual / PM2.5: "+this.pm25density+" / FanSpeed:"+this.fanSpeed);
        } else {
            IO.println("Mode:Auto / PM2.5: "+this.pm25density+" / FanSpeed:"+this.fanSpeed);
        }
    }
    public void changePm25(int pm){
        if(this.pm25density == pm)return;
        this.pm25density = pm;
        if(this.mode == Mode.AUTO){
            if(pm25density < 50){
                this.fanSpeed = 1;
            }else if(pm25density < 100){
                this.fanSpeed = 2;
            }else if(pm25density < 150){
                this.fanSpeed = 3;
            }else{
                this.fanSpeed = 4;
            }
        }
    }
    public static String mostPopularModel(){
        if(totalNumberEach.isEmpty()){
            return null;
        }
        String bestModel = "";
        int maxCount = -1;
        for(String model: totalNumberEach.keySet()){
            if(maxCount < totalNumberEach.get(model)){
                bestModel = model;
                maxCount = totalNumberEach.get(model);
            }
        }
        return bestModel;
    }

}
