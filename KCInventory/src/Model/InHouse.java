package Model;

public class InHouse extends Part{
    int machineId = -1;
    
    public InHouse(){
        super();
        System.out.println("InHouse Part Created.");
    }

   

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId() {
        return this.machineId;
    }

}