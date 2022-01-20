class App {

    public static void main(String []args){

        business.services.StoreManager manager=new business.services.StoreManager();
        
        business.furnitures.Chair chair =new business.furnitures.Chair();
        business.furnitures.Table table1=new business.furnitures.Table();


        utils.data.Table table2=new utils.data.Table();

        utils.data.List list=new utils.data.List();
        
        utils.presentation.Input input =new utils.presentation.Input();
        
        utils.presentation.Printer printer=new utils.presentation.Printer();

        utils.data.QuickSort sort=new utils.data.QuickSort();

        System.out.println(table1);
        System.out.println(table1.getPrice());

        System.out.println(table2);
        System.out.println(table2.getColumnCount());


        System.out.println(manager);
        System.out.println(chair);
        System.out.println(list);
        System.out.println(input);
        System.out.println(printer);
        System.out.println(sort);



       


    }
    
}
