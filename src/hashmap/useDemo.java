package hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

public class useDemo {
    public static void main(String[] args){
        Map<String,Double> hashMap1 = new HashMap();
        hashMap1.put("xiaoming",79.0);
        hashMap1.put("xiaohong",88.0);
        hashMap1.put("dafei",87.0);
        hashMap1.put("cc",99.0);
        System.out.println(hashMap1.get("cc"));
        hashMap1.putIfAbsent("cc",0.0);
        System.out.println(hashMap1.get("cc"));
        hashMap1.forEach((s, aDouble) -> System.out.printf("Key: %s Value: %f%n",s,aDouble));
        Iterator<Map.Entry<String,Double>> ita = hashMap1.entrySet().iterator();
        while (ita.hasNext()){
            Map.Entry<String,Double> entry = ita.next();
            System.out.printf("Key: %s Value: %f%n",entry.getKey(),entry.getValue());
        }
    }
}
