import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class L1Z3_Map {
    public static void main(String[] args) {

        Map<String, String> PanStoMapHash = new HashMap<>();
        Map<String, String> PanStoMapTree = new TreeMap<>();
        doublePut(PanStoMapHash,PanStoMapTree,"Włochy", "Rzym");
        doublePut(PanStoMapHash,PanStoMapTree,"Niemcy", "Berlin");
        doublePut(PanStoMapHash,PanStoMapTree,"Polska", "Warszawa");
        doublePut(PanStoMapHash,PanStoMapTree,"Lichtenstein", "Lichtenstein");
        doublePut(PanStoMapHash,PanStoMapTree,"Sierra Leone", "Freetown");
        doublePut(PanStoMapHash,PanStoMapTree,"Trynidad", "Port of Spain");

        System.out.println("Hashmap: ");
        printMap(PanStoMapHash);

        System.out.println("\nTreemap: ");
        printMap(PanStoMapTree);

        doublePut(PanStoMapHash,PanStoMapTree,"Andora", "Andorra la Vella");
        doublePut(PanStoMapHash,PanStoMapTree,"Grecja", "Ateny");
        doublePut(PanStoMapHash,PanStoMapTree,"Hiszpania", "Madryt");
        doublePut(PanStoMapHash,PanStoMapTree,"Francja", "Paryż");

        System.out.println("\nHashmap: ");
        printMap(PanStoMapHash);

        System.out.println("\nTreemap: ");
        printMap(PanStoMapTree);


    }


    public static void doublePut(Map<String, String> map1, Map<String, String> map2, String key, String value){
        map1.put(key,value);
        map2.put(key,value);
    }

    public static void printMap(Map<String, String> map) {
        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}