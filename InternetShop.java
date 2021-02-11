
import java.util.*;
class Shop {

    private String name;
    private ArrayList<Customer> customers;
    private HashMap<Integer,Customer>customerHashMap;
    private ArrayList<Respiratory> respiratories;
    private HashMap<Integer,Respiratory>respiratoryHashMap;
    private HashMap<Integer,Good> goodHashMap;
    private ArrayList<Good>goods;
    private HashMap<Integer,Discount> discountHashMap;
    private ArrayList<Discount>discounts;
    private HashMap<Good, Integer> goodsSold;
    private int income;

    public Discount getdiscount(int id){

        return this.discountHashMap.get(id);

    }

    public int getincome() {
        int income = 0;
        for (int i = 0; i < this.goods.size(); i++) {
            if (this.goodsSold.containsKey(this.goods.get(i))) {
                income += this.goodsSold.get(this.goods.get(i)) * this.goods.get(i).getPrice();
            }
        }
        return income;
}

    public int getIncome() {
        return income;
    }

    public Good getgood(int id){

        return this.goodHashMap.get(id);
    }

    public void addGoodsSold(Good good,int amount) {
        if (this.goodsSold.containsKey(good)) this.goodsSold.put(good, this.goodsSold.get(good) + amount);
        else this.goodsSold.put(good,amount);

    }

    public Customer getCustomer(int id) {
        return this.customerHashMap.get(id);
    }

    public Shop(String name){

        this.name = name;
        this.customers = new ArrayList<>();
        this.goods = new ArrayList<>();
        this.respiratories = new ArrayList<>();
        this.discounts = new ArrayList<>();
        this.goodsSold = new HashMap<>();
        this.customerHashMap=new HashMap<>();
        this.discountHashMap=new HashMap<>();
        this.goodHashMap=new HashMap<>();
        this.respiratoryHashMap=new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
        this.customerHashMap.put(customer.getId(),customer);
    }

    public ArrayList<Customer> customerList() {
        return this.customers;
    }

    public void addRespiratory(Respiratory respiratory) {
        this.respiratories.add(respiratory);
        this.respiratoryHashMap.put(respiratory.getId(),respiratory);
    }

    public ArrayList<Respiratory> respiratoryList() {
        return this.respiratories;

    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void addGood(Good good) {
        this.goods.add(good);
        this.goodHashMap.put(good.getId(),good);
    }

    public void incrementGood(Good good, int ammount) {
        ArrayList<Respiratory> shopRespiratories = this.respiratoryList();
        Respiratory min = null;
        for (int i = 0, minimum = 0; i < shopRespiratories.size(); i++) {
            if (minimum == 0 && shopRespiratories.get(i).getCapacity() >= ammount) {
                min = shopRespiratories.get(i);
                minimum++;
            }
            if (minimum != 0 && shopRespiratories.get(i).getCapacity() >= ammount && shopRespiratories.get(i).getCapacity() < min.getCapacity())
                min = shopRespiratories.get(i);

        }
        if (min != null) {
            min.addgood(good, ammount);
            min.setCapacity(min.getCapacity() - ammount);
        }
        //else System.out.println("Not enough space");
    }

    public ArrayList<Good> getgoods() {

        return this.goods;

    }

    public HashMap<Good, Integer> getitemsSold() {
        return this.goodsSold;
    }

    public void addDisount(Discount discount) {
        this.discounts.add( discount);
        this.discountHashMap.put(discount.getId(),discount);
    }

}

class Good{
    private String name;
    private int id;
    private int price;
    private Shop shop;

    public Good(String name,int id,int price){
        this.name=name;
        this.id=id;
        this.price=price;

    }

    public Good(String name,int id,int price,Shop shop){
        this.name=name;
        this.id=id;
        this.price=price;
        this.shop = shop;
        this.shop.addGood(this);

    }

    public Shop getShop() {
        return shop;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

}
class Respiratory{

    private int id;
    private int capacity;
    private int maxcapacity;
    private HashMap<Good,Integer>goods;
    private Shop shop;

    public Respiratory(int id,int capacity,Shop shop){
        this.capacity=capacity;
        this.id=id;
        this.maxcapacity=capacity;
        this.goods=new HashMap<>();
        this.shop = shop;
        this.shop.addRespiratory(this);
    }

    public Shop getShop() {
        return shop;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getfreecapacity() {
        return maxcapacity;
    }

    public HashMap<Good,Integer>getgood(){
        return this.goods;
    }

    public void setGoods(Good good,Integer amount){
        this.getgood().put(good,amount);
    }

    public void removegood(Good good,int amount){
        this.goods.put(good,this.goods.get(good)-amount);
    }

    public void addgood(Good good,int amount){
        if(this.goods.containsKey(good)) {
            this.goods.put(good, this.goods.get(good) + amount);
        }
        else this.goods.put(good,amount);
    }

}

class Customer {
    private Shop shop;
    private int id;
    private String name;
    private int balance;
    private ArrayList<Discount> discounts;
    private HashMap<Integer,Discount>discountHashMap;
    private ArrayList<Order> orders;
    static private HashMap<Integer,Order>orderHashMap;
    private ArrayList<Order> pendingorders;
    private ArrayList<Order> submitedorders;

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public void addOrderHashmap(Order order){
        orderHashMap.put(order.getId(),order);
    }

    public Order getorder(int id) {
        if(orderHashMap.containsKey(id)) {
            //System.out.println("the id of order is"+orderHashMap.get(id).getId());
            return orderHashMap.get(id);
        }
        else{
            //System.out.println("couldn,t find the order");
            return null;
        }

    }


    public Discount getDiscount(int id) {
        return this.discountHashMap.get(id);
    }

    public Customer(int id, String name, Shop shop) {
        this.id = id;
        this.name = name;
        this.balance = 0;
        this.discounts = new ArrayList<>();
        this.discountHashMap=new HashMap<>();
        this.orders = new ArrayList<>();
        orderHashMap=new HashMap<>();
        this.pendingorders = new ArrayList<>();
        this.submitedorders = new ArrayList<>();
        this.shop = shop;
        this.shop.addCustomer(this);

    }

    public Shop getShop() {
        return shop;
    }

    public void addDiscout(Discount discount) {
        this.discounts.add(discount);
        this.shop.addDisount(discount);
        this.discountHashMap.put(discount.getId(),discount);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
        this.pendingorders.add(order);
        orderHashMap.put(order.getId(),order);

    }

    public ArrayList<Order> getPendingorders() {
        return this.pendingorders;
    }

    public ArrayList<Order> getSubmitedorders() {
        return this.submitedorders;
    }

    public void removediscount(Discount discount) {
        if(discount!=null && this.discounts.contains(discount) && this.discountHashMap.containsKey(discount.getId())) {
            this.discounts.remove(discount);
            this.discountHashMap.remove(discount.getId());
        }

    }

    public int submitorder(Order order) {
        int minimum;
        int i;
        int j;
        int k;
        int f;
        HashMap<Good, Integer> orderGoodsAmount = order.getGoodsAmount();
        ArrayList<Good> shopGoods = this.shop.getgoods();
        ArrayList<Respiratory> shopRespiratories = this.shop.respiratoryList();
        Respiratory min = null;
        //System.out.println("the precentage of discount is"+order.getDiscount().getPrecent());
        //System.out.println("the price of order is"+order.calculatePrice());
        if (this.balance < order.calculatePrice()){
            //System.out.println("order not sufficient");
            return 0;
        }
        else {
            for (i = 0; i < shopGoods.size(); i++) {
                if (orderGoodsAmount.containsKey(shopGoods.get(i))) {
                    //System.out.println("passed contains key level");
                    for (k = 0, minimum = 0; k < shopRespiratories.size(); k++) {
                        /*if (minimum > 0 && shopRespiratories.get(k).getgood().containsKey(shopGoods.get(i)) && shopRespiratories.get(k).getgood().get(shopGoods.get(i)) >= orderGoodsAmount.get(shopGoods.get(i))) {
                            if (shopRespiratories.get(k).getgood().get(shopGoods.get(i)) < min.getgood().get(shopGoods.get(i))) {
                                min = shopRespiratories.get(k);
                                //System.out.println("founded better minimum");
                            }
                        }*/
                        if (minimum == 0 && shopRespiratories.get(k).getgood().containsKey(shopGoods.get(i)) && shopRespiratories.get(k).getgood().get(shopGoods.get(i)) >= orderGoodsAmount.get(shopGoods.get(i))) {
                            min = shopRespiratories.get(k);
                            minimum++;
                            //System.out.println("founded minimum");
                        }

                        if (minimum!=0)break;
                    }
                    if (minimum == 0) {
                        //System.out.println("we don,t have enough item to support your order");
                        return 0;
                    }
                    min.addgood(shopGoods.get(i), -order.getGoodsAmount().get(shopGoods.get(i)));
                    min.setCapacity(min.getCapacity() + order.getGoodsAmount().get(shopGoods.get(i)));
                }
            }

        }
        this.pendingorders.remove(order);
        submitedorders.add(order);
        order.setStatus("passed");
        this.setBalance(this.getBalance() - order.calculatePrice());
        this.shop.setIncome(this.shop.getIncome()+order.calculatePrice());
        Discount discardedDiscount=order.getDiscount();
        Customer customer=order.getCustomer();
        customer.removediscount(discardedDiscount);
        for( i=0;i<shopGoods.size();i++){
            if (orderGoodsAmount.containsKey(shopGoods.get(i))) {
                this.shop.addGoodsSold(shopGoods.get(i),orderGoodsAmount.get(shopGoods.get(i)));
            }

        }
        return 1;

    }

}

class Order{

    private int id;
    private Customer customer;
    private String status;
    private HashMap<Good,Integer>goodsAmount;
    private Discount discount;

    public Discount getDiscount() {
        return discount;
    }

    public HashMap<Good, Integer> getGoodsAmount() {
        return goodsAmount;
    }

    public Order(int id,Customer customer){
        this.id=id;
        this.status="pending";
        this.goodsAmount=new HashMap<>();
        this.discount=null;
        customer.addOrderHashmap(this);

    }

    public Order(int id,Customer basecustomer,Customer customer){
        this.id=id;
        this.status="pending";
        this.goodsAmount=new HashMap<>();
        this.discount=null;
        basecustomer.addOrderHashmap(this);
        customer.addOrder(this);
        this.customer=customer;

    }

    public Integer getgoodamount(Good good){
        return this.goodsAmount.get(good);
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void additem(Good good,int ammount) {

        if (this.goodsAmount.containsKey(good)) {
            this.goodsAmount.put(good, this.goodsAmount.get(good)+ammount);
            }
        else {
            this.goodsAmount.put(good, ammount);
        }
    }

    public void removeitem(Good good){
        //System.out.println(this.goodsAmount);
        if(this.goodsAmount.containsKey(good)){
            this.goodsAmount.remove(good);
            //System.out.println("remove was succesful");
        }
        /*ArrayList<Good>shopgoods=this.customer.getShop().getgoods();
        for(int i=0;i<shopgoods.size();i++){
            if(this.goodsAmount.containsKey(shopgoods.get(i)))System.out.println(shopgoods.get(i).getName());
        }*/
        //System.out.println(this.goodsAmount);

    }

    public HashMap<Good, Integer> getItems() {
        return goodsAmount;
    }

    public int calculatePrice(){

        //HashMap<Good,Integer>goodsamount=this.goodsAmount;
        int sum=0;
        ArrayList<Good>shopgoods=this.customer.getShop().getgoods();
        for(int i=0;i<shopgoods.size();i++){
            if(this.goodsAmount.containsKey(shopgoods.get(i)))sum+=this.goodsAmount.get(shopgoods.get(i))*shopgoods.get(i).getPrice();
        }
        if(discount!=null)sum=(int)(sum-(sum*((float)this.discount.getPrecent()/100)));
        return sum;
    }

    public void adddiscount(Discount discount){
        if(this.discount==null)
        this.discount=discount;
    }

}
class Discount{
    private int id;
    private int precent;
    private Order order;
    private Customer customer;

    public Discount(int id,int precent,Customer customer){
        this.id=id;
        this.precent=precent;
        customer.getShop().addDisount(this);


    }

    public int getId() {
        return id;
    }

    public int getPrecent(){
        return precent;
    }

    public void setorder(Order order){
        this.order=order;
        order.adddiscount(this);
    }

}

public class Digikala{

    public static void main(String[] args) {
        Shop shop=new Shop("shop");
        Customer customer=new Customer(0,"customer",shop);
        String command=new String();
        Scanner scanner=new Scanner(System.in);
        int i=0;
        //System.out.println("passed initiation");
        while(i==0){
            //System.out.println("enter your command");
            command=scanner.nextLine();


            switch (command){
                case "add":
                    //System.out.println("add command selected,which one do you add");
                    String commandAdd;
                    commandAdd=scanner.nextLine();
                    switch (commandAdd){
                        case"customer":
                            int customerId;
                            String customerName;
                            customerId=scanner.nextInt();
                            scanner.nextLine();
                            customerName=scanner.nextLine();
                            customer=new Customer(customerId,customerName,shop);
                            //System.out.println("customer added" );
                            break;
                        case"good":
                            int goodId;
                            String goodName;
                            int goodPrice;
                            int goodamount;
                            goodId=scanner.nextInt();
                            scanner.nextLine();
                            goodName=scanner.nextLine();
                            goodPrice=scanner.nextInt();
                            scanner.nextLine();
                            goodamount=scanner.nextInt();
                            scanner.nextLine();
                            Good good=new Good(goodName,goodId,goodPrice,shop);
                            shop.incrementGood(good,goodamount);
                            //System.out.println("good added" );
                            break;
                        case "repository":
                            int respiratoryid;
                            int respiratroyCapacity;
                            respiratoryid=scanner.nextInt();
                            scanner.nextLine();
                            respiratroyCapacity=scanner.nextInt();
                            scanner.nextLine();
                            Respiratory respiratory=new Respiratory(respiratoryid,respiratroyCapacity,shop);
                            //System.out.println("respiratory added" );
                            break;
                        case"order":
                            int orderId;
                            int orderCustomerId;
                            orderId=scanner.nextInt();
                            scanner.nextLine();
                            orderCustomerId=scanner.nextInt();
                            scanner.nextLine();
                            Customer customerOrder=shop.getCustomer(orderCustomerId);
                            //System.out.println("the balance of customer is"+customerOrder.getBalance());
                            Order order=new Order(orderId,customer,customerOrder);
                            order.setStatus("pending");
                            //System.out.println("order added" );
                            break;
                        case "balance":
                            int balanceCustomerId;
                            int balanceAdd;
                            balanceCustomerId=scanner.nextInt();
                            scanner.nextLine();
                            balanceAdd=scanner.nextInt();
                            scanner.nextLine();
                            Customer customerBalance=shop.getCustomer(balanceCustomerId);
                            //System.out.println("the balance of customer is"+customerBalance.getBalance());
                            customerBalance.setBalance(customerBalance.getBalance()+balanceAdd);
                            //System.out.println("the balance of customer is"+customerBalance.getBalance());
                            //System.out.println("balance added" );
                            break;
                        case "item":
                            int itemOrderId;
                            int itemGoodId;
                            int itemGoodAmount;
                            itemOrderId=scanner.nextInt();
                            scanner.nextLine();
                            itemGoodId=scanner.nextInt();
                            scanner.nextLine();
                            itemGoodAmount=scanner.nextInt();
                            scanner.nextLine();
                            Good itemGood=shop.getgood(itemGoodId);
                            Order orderItem=customer.getorder(itemOrderId);
                            orderItem.additem(itemGood,itemGoodAmount);
                            //System.out.println("item added" );
                            break;
                        case "discount":
                            int discountId;
                            int discountPrecent;
                            discountId=scanner.nextInt();
                            scanner.nextLine();
                            discountPrecent=scanner.nextInt();
                            scanner.nextLine();
                            Discount discount=new Discount(discountId,discountPrecent,customer);
                            //System.out.println("discount added" );
                            break;
                    }
                    break;
                case "report":
                    //System.out.println("report command selected,which one do you want to report" );
                    String reportCommand;
                    reportCommand=scanner.nextLine();
                    switch (reportCommand){
                        case"customers":
                            for(int j=1;j<shop.customerList().size();j++){
                                System.out.print(shop.customerList().get(j).getId()+",");
                                System.out.print(shop.customerList().get(j).getName()+",");
                                System.out.print(shop.customerList().get(j).getBalance()+",");
                                System.out.print(shop.customerList().get(j).getOrders().size()+",");
                                System.out.print(shop.customerList().get(j).getSubmitedorders().size());
                                System.out.println();
                            }
                            //System.out.println("customer reported" );
                            break;
                        case "repositories":
                            for(int j=0;j<shop.respiratoryList().size();j++){
                                System.out.print(shop.respiratoryList().get(j).getId()+",");
                                System.out.print(shop.respiratoryList().get(j).getfreecapacity()+",");
                                System.out.print(shop.respiratoryList().get(j).getCapacity());

                                System.out.println();
                            }
                            //System.out.println("respiratories reported" );
                            break;
                        case "income":
                            System.out.println(shop.getIncome());
                            //System.out.println("income reported" );
                            break;

                    }
                    break;
                case"submit":
                    //System.out.println("submit selected,which one do you want to submit" );
                    String submitCommand;
                    submitCommand=scanner.nextLine();
                    switch (submitCommand) {
                        case "order":
                            int orderIdSubmit = scanner.nextInt();
                            scanner.nextLine();
                            Order SubmitOrder = customer.getorder(orderIdSubmit);
                            Customer orderCustomer = SubmitOrder.getCustomer();
                            orderCustomer.submitorder(SubmitOrder);
                            //System.out.println("order submited" );
                            break;
                        case"discount":
                            int OrderidDiscount=scanner.nextInt();
                            scanner.nextLine();
                            int discountIdDiscount=scanner.nextInt();
                            scanner.nextLine();
                            Order orderDiscount=customer.getorder(OrderidDiscount);
                            Discount discountDiscount=shop.getdiscount(discountIdDiscount);
                            discountDiscount.setorder(orderDiscount);
                            //System.out.println("discount submited" );
                            break;
                    }
                    break;
                case"terminate":
                    i = 1;
                    //System.out.println("goodbye" );
                    break;
                case"remove":
                    //System.out.println("remove selected,which one do you want to submit" );
                    String removeCommand=scanner.nextLine();
                    switch (removeCommand){
                        case"item":
                            int orderIdRemove=scanner.nextInt();
                            //System.out.println(orderIdRemove);
                            scanner.nextLine();
                            int orderItemIdRemove=scanner.nextInt();
                            //System.out.println(orderItemIdRemove);
                            scanner.nextLine();
                            Order orderRemove=customer.getorder(orderIdRemove);
                            //System.out.println("the id of order is"+orderRemove.getId());
                            Good orderItemRemove=shop.getgood(orderItemIdRemove);
                            //System.out.println(orderItemRemove.getName());
                            orderRemove.removeitem(orderItemRemove);
                            break;
                    }
                    break;

            }


        }

    }
}

