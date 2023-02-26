import java.util.ArrayList;
public class Main {//Cristescu Cristian 2A6
    public static void main(String[] args) {

        System.out.println("Hello world!");
        String[] languages = new String[]{"C", "C++", "C#", "Python","Go", "Rust","JavaScript", "PHP", "Swift", "Java"};
        int n=(int)(Math.random()*1_000_000);
        n=n*3;
        String b="10101";
        String f="FF";
        n+=Integer.parseInt(b,2);
        n+=Integer.parseInt(f,16);
        n*=6;
        int result;
        result=0;
        while(n!=0){
            result+=n%10;
            n=n/10;
        }
        int sum=0;
        while(result!=0){
            sum+=result%10;
            result=result/10;
            if(result==0 && sum>9){
                result=sum;
            }
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[sum]);

    }
}
