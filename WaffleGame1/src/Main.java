/**
 * Este programa recebe um numero e indica se
 *
 * this class uafailsfuhiaf
 *
 * compile: javac WaffleGame1.java
 * run: java WaffleGame1
 *
 * @author Miguel Simoes 60451, Tiago Lopes 60745
 *
 */
public  class WaffleGame1 {

    public static void main (String[] args) {
        game(-129302);
        game(9620);
        game(24380);
        game(44444);
        game(4528123);
        game(18336);
    }

    /**
     * Calcula o tamanho do numero dado
     *
     * @param num o numero
     * @return o tamanho do numero
     * @requires {@code num > 0}
     * @ensures {@code \result > 0}
     */
    public static int length(int num) {
        int cont = 0;
        while(num > 0)
        {
            num /= 10;
            cont++;
        }
        return (cont);

    }

    /**
     * Verifica se o numero e valido
     *
     * @param num o numero
     * @return boolean dependendo se e valido ou nao
     * @requires {@code num > 0}
     * @ensures {@code \result is boolean}
     */
    public static boolean is_valid(int num) {
        int length = length(num);
        int buff = num;
        if(num<0)
        {
            System.out.println("The puzzle " + buff + " is invalid: it does not verify condition C1.");
            return false;
        }
        if(10 < length || length < 2 || length % 2 == 0)
        {
            System.out.println("The puzzle " + buff + " is invalid: it does not verify condition C2.");
            return false;
        }

        for(int i = 0; i < length; i++)
        {
            int equal = num%10;
            int cont = 1;
            while(num/10 != 0)
            {
                if(num%10 == 0)
                {
                    System.out.println("The puzzle " + buff + " is invalid: it does not verify condition C3.");
                    return false;
                }
                else if(num%10 == equal)
                    cont++;
                num /= 10;
            }
            if(cont == length)
            {
                System.out.println("The puzzle " + buff + " is invalid: it does not verify condition C4.");
                return false;
            }
        }
        return(true);

    }

    /**
     * Devolve o valor do digito do numero na posicao dada
     *
     * @param num o numero
     * @param pos a posicao
     * @return valor do digito
     * @requires {@code num > 0 && pos < num && pos > 0}
     * @ensures {@code \result int de 1 a 9}
     */
    public static int return_digit(int num, int pos) {
        int length = length(num);
        int i;
        int val;
        for(i=0;i<length-pos;i++)
            num /= 10;
        val = num%10;
        return(val);
    }

    /**
     * Troca o valor de duas posicoes num numero
     *
     * @param num o numero
     * @param i primeira posicao
     * @param j segunda posicao
     * @return novo numero com as posicoes trocadas
     * @requires {@code num > 0 && 0<i<10 && 0<j<10}
     * @ensures {@code \result numero positivo}
     */
    public static int switch_digit(int num, int i, int j) {
        int vali = return_digit(num,i);
        int valj = return_digit(num,j);
        int new_num = 0;
        int expo = 1;
        int k;
        while(num/expo > 10 && num/expo != 0)
            expo *= 10;
        for(k=1; k < length(num)+1; k++)
        {
            if(k == i)
                new_num += valj * expo;
            else if(k == j)
                new_num += vali * expo;
            else
                new_num += return_digit(num,  k) * expo;
            expo /= 10;
        }
        return(new_num);
    }
    /**
     * Aplica o jogo a um numero dado
     *
     * @param num o numero
     * @return void
     * @requires {@code num > 0}
     * @ensures {@code \result }
     */
    public static void game(int num)
    {
        if(is_valid(num))
        {
            System.out.println("The puzzle " + num + " is valid.");
            System.out.println("	> after swapping the last two digits: " + (num = switch_digit(num, length(num), length(num)-1)));
            System.out.println("	> after swapping the first two digits: " + switch_digit(num, 1, 2));
        }
    }
}