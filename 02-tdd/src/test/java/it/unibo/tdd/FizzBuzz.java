package test.java.it.unibo.tdd;


public class FizzBuzz {

   public int[] createList() {
        int[] list = new int[101];
        for (int i=0; i < list.length; i++) {
            int count = 0;
            list[i] = count;
            count++;
        }
        return list;
    }

    public void replaceNumbers(int[] list) {
        for(int i=0; i < list.length; i++) {
            if (list[i]%3 == 0)
                list[i] = -1;

            else if (list[i]%5 == 0)
                list[i] = -2;
        }
    }
}
