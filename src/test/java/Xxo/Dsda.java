package Xxo;

import org.junit.jupiter.api.Test;

public class Dsda {

//    метод, который принимает два числа и возвращает их сумму
//    метод, который принимает на вход имя, выводит его в консоль и ничего не возвращает
//    метод, который принимает на вход слово, проверяет, равно ли это слово кодовому слову.Если да - возвращает true, иначе - false
//    кодовое слово это сосиска, оно должно лежать в переменной внутри метода

//    метод, который принимает на вход 2 числа и сравнивает их
//    если 1е число меньше 2го - возвращает их разность
//    если 1е число больше 2го - возвращает их сумму

    int int1 = 5;
    int int2 = 4;
    String name = "ОЧКО";

    public int jopa(int int1, int int2) {
        if (int1 < int2) {
            return int1 - int2;
        }
            return int1 + int2;
    }



    public boolean checkPassword(String slovo) {
        String password = "сосиска";
        if (slovo == password) {
            return true;
        }
        return false;
    }


    public int summa(int int1, int int2) {
        return int1 + int2;
    }

    public int minus(int int1, int int2) {
        return int1 - int2;
    }

    public void showName(String name) {
        System.out.println(name);
    }


    @Test
    void cm() {
        int a = summa(int1, int2);
        System.out.println(a);
    }


}
