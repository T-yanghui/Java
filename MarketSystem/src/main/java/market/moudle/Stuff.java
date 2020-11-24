package market.moudle;

public class Stuff {
    private int ID;
    private String name;
    private String password;
    private  int priority;

    //构造函数
    public Stuff(String name,String password,int priority){
        this.name = name;
        this.password = password;
        this.priority = priority;
    }
    public Stuff(int id,String password,int priority){
        this.ID = id;
        this.password = password;
        this.priority = priority;
    }
   public Stuff(int id,String name,String password){
        this.ID = id;
        this.name = name;
        this.password = password;
   }
   public Stuff(int id,String name,String password,int priority){
        this.ID = id;
        this.name = name;
        this.password = password;
        this.priority = priority;
   }
//get,set函数

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
