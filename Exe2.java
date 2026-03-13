//1.写这样的一个作业，先写大框架：
public class Exe2{
    public static void main(String[] args){
        //test
        Time time = new Time();
        System.out.println(time);
        for (int i=0; i<59; i++){
            time.incrementSecond();
        }
        System.out.println(time);
        time.incrementSecond();
        System.out.println(time);
        
        Time t2 = new Time(23,59,59);
        System.out.println(t2);
        t2.incrementSecond();
        System.out.println(t2);

        Time time1 = new Time(0,10,5);
        Time time2 = new Time(0,11,0);
        System.out.println(time2.differenceInSeconds(time1));
        System.out.println(time1.differenceInSeconds(time2));
    }  
}

class Time{
//2.储存需要的数据：小时，分钟，秒    
    private int hour;
    private int minute;
    private int second;

    //3.第一小题让我们先构建一个生成时间00：00：00的constructor
    public Time(){
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public Time(int hour, int minute, int second) {
        // 简单处理：把不合法值“归一化”到合法范围（也可以选择抛异常）
        int total = hour * 3600 + minute * 60 + second;
        total = ((total % 86400) + 86400) % 86400; // 保证在 0..86399
        setFromTotalSeconds(total);
    }

    //4.还有返回现在是小时，分钟，秒
    public int getHour(){
        return this.hour;
    }

    public int getMinute(){
        return this.minute;
    }

    public int getSecond(){
        return this.second;
    }
    //5.接着做时间进一

    public void incrementSecond(){
        int total = toTotalSeconds();
        total = (total+1)%86400;
        setFromTotalSeconds(total);
    }
    //6.接下来记住还要补全这两个函数

    public int toTotalSeconds(){
        return hour * 3600 + minute * 60 + second;
    }

    private void setFromTotalSeconds(int total){
        this.hour = total / 3600;
        total %= 3600;
        this.minute = total / 60;
        this.second = total % 60;
    }
    @Override
    public String toString() {
        return twoDigits(hour) + ":" + twoDigits(minute) + ":" + twoDigits(second);
    }

    public int differenceInSeconds(Time otherTime) {
        return this.toTotalSeconds() - otherTime.toTotalSeconds();
    }

    private String twoDigits(int n) {
        return (n < 10) ? ("0" + n) : String.valueOf(n);
    }
}
