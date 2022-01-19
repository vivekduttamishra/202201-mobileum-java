class App {

    public static void main(String []args){

        StoreManager manager=new StoreManager();
        
        Chair chair =new Chair();
        
        List list=new List();
        
        Input input =new Input();
        
        Printer printer=new Printer();

        QuickSort sort=new QuickSort();


        System.out.println(manager);
        System.out.println(chair);
        System.out.println(list);
        System.out.println(input);
        System.out.println(printer);
        System.out.println(sort);

        Table table=new Table(); //which Table is this?

        System.out.println(table);
        System.out.println(table.getPrice());
        System.out.println(table.getColumnCount());


    }
    
}
