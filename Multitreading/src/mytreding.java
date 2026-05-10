public class mytreding  extends  Thread {
   public void run () {
        System.out.println("i am ruring in the main thred ");
    }

    public static void main(String[] args) {
      mytreding my = new mytreding() ;
        my.start();
    }

}
