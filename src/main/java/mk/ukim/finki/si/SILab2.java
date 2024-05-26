package mk.ukim.finki.si;
import java.util.List;

public class SILab2 {
    public static boolean checkCart(List<Item> allItems, int payment){
        if (allItems == null){//1
            throw new RuntimeException("allItems list can't be null!");//2
        }

        float sum = 0;//3

        for (int i = 0; i < allItems.size(); i++){//4
            Item item = allItems.get(i);//5
            if (item.getName() == null || item.getName().length() == 0){//6
                item.setName("unknown");//7
            }
            if (item.getBarcode() != null){//8
                String allowed = "0123456789";//9
                char chars[] = item.getBarcode().toCharArray();//10
                for (int j = 0; j < item.getBarcode().length(); j++){//11
                    char c = item.getBarcode().charAt(j);//12
                    if (allowed.indexOf(c) == -1){//13
                        throw new RuntimeException("Invalid character in item barcode!");//14
                    }
                }
                if (item.getDiscount() > 0){//15
                    sum += item.getPrice()*item.getDiscount();//16
                }
                else {//17
                    sum += item.getPrice();//18
                }
            }
            else {//19
                throw new RuntimeException("No barcode!");//20
            }
            if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'){//21
                sum -= 30;//22
            }
        }
        if (sum <= payment){//23
            return true;//24
        }
        else {//25
            return false;//26
        }
    }//27
}
