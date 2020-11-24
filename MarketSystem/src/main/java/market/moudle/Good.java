package market.moudle;

/**
 * @ClassName Good
 * @Description 商品类
 * @Author Yanghui
 * @Date 2020/11/23 21:33
 * @Version 1.0
 **/
public class Good {
    private int GID;
    private String Gname;
    private double price;
    private double Gnum;
    private double sales = -1;
    //构造函数
    public Good(int GID,String Gname,double price,double Gnum){
        this.GID =GID;
        this.Gname = Gname;
        this.price = price;
        this.Gnum = Gnum;
    }
    public Good(int GID,String Gname,double sales){
        this.GID =GID;
        this.Gname = Gname;
        this.sales = sales;
    }


    //get和set
    public int getGID() {
        return GID;
    }

    public void setGID(int GID) {
        this.GID = GID;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getGnum() {
        return Gnum;
    }

    public void setGnum(double gnum) {
        Gnum = gnum;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }
}
