package components;

public class Hilo extends Thread{

    public Hilo(String name){
        setName(name);
    }
    @Override
    public void run(){
        super.run();
        for (int i = 1; i <= 10; i++){
            System.out.println("Vuelta: "+i+ "--->"+ getName());
            try {
                sleep((long)(Math.random()*5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(getName()+"Llego a la meta");
    }

}
