package Utilitare;

public class Token {
    private final int number;
    public Token(int number){
        this.number = number;
    }
    public int getToken(){
        return this.number;
    }

    @Override
    public String toString() {
        return ((Integer) number).toString();
    }
    public int getNumber(){
        return this.number;
    }
}
