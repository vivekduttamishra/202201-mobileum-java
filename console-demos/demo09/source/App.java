import business.furnitures.Table;
import business.furnitures.Chair;
import utils.data.List;
import utils.data.QuickSort;
import business.services.StoreManager;
import utils.presentation.Input;
import utils.presentation.Printer;
import utils.data.BinarySearch;


class App {

    public static void main(String []args){

       StoreManager manager=new StoreManager();
        
       Chair chair =new Chair();
       Table table1=new Table();

        //ambigous classes can be used with fully qualified name.
        utils.data.Table table2=new utils.data.Table();

        List list=new List();
        
        Input input =new Input();
        
        Printer printer=new Printer();

        QuickSort sort=new QuickSort();

        System.out.println(chair);
        System.out.println(table1);
        System.out.println(table1.getPrice());

        System.out.println(table2);
        System.out.println(table2.getColumnCount());


        System.out.println(manager);
        
        System.out.println(list);
        System.out.println(input);
        System.out.println(printer);
        System.out.println(sort);


        BinarySearch search=new BinarySearch();
        System.out.println(search);

       
        System.out.println(list.about());

    }
    
}
