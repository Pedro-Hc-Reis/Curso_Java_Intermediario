package aula74.exercicio;

public class ThreadSemaforo implements Runnable{

    private CorSemaforo cor;
    private boolean parar;
    private boolean corMudou;

    public ThreadSemaforo(){
        this.cor = CorSemaforo.VERMELHO;
        this.parar = false;
        this.corMudou = false;

        new Thread(this).start();
    }

    @Override
    public void run() {

        while(!parar){
            try {
                /*
                switch (this.cor) {
                    case VERMELHO:
                        Thread.sleep(2000);
                        break;
                    case AMARELO:
                        Thread.sleep(300);
                        break;
                    case VERDE:
                        Thread.sleep(1000);
                        break;
                }
                 */
                Thread.sleep(this.cor.getTempoEspera());
            }catch(InterruptedException e){
                 e.printStackTrace();
            }

            this.mudarCor();
        }

    }

    private synchronized void mudarCor(){
        switch (this.cor) {
            case VERMELHO:
                this.cor = CorSemaforo.VERDE;
                break;
            case AMARELO:
                this.cor = CorSemaforo.VERMELHO;
                break;
            case VERDE:
                this.cor = CorSemaforo.AMARELO;
                break;
        }
        this.corMudou = true;
        notify();
    }

    public synchronized void esperaCorMudar(){
        while (!this.corMudou){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.corMudou = false;
    }

    public synchronized void desligarSemaforo(){
        this.parar = true;
    }

    public CorSemaforo getCor() {
        return cor;
    }

    public void setCor(CorSemaforo cor) {
        this.cor = cor;
    }
}
