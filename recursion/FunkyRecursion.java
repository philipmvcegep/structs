import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunkyRecursion {
    public static List<Object> flattenNestedList(List<Object> nestedList) {
        List<Object> flattenedResult = new ArrayList<>();
        
        for (Object item : nestedList) {
            if (item instanceof List) {
                // @SuppressWarnings évite l'avertissement de cast non vérifié
                @SuppressWarnings("unchecked")
                List<Object> subList = (List<Object>) item;
                flattenedResult.addAll(flattenNestedList(subList));
            } else {
                flattenedResult.add(item);
            }
        }
        
        return flattenedResult;
    }

    public static void main(String[] args) {
        // Test avec une liste imbriquée : [1, [2, [3, 4], 5], 6]
        List<Object> nested = Arrays.asList(
            1, 
            Arrays.asList(2, Arrays.asList(3, 4), 5), 
            6
        );
        
        List<Object> flat = flattenNestedList(nested);
        System.out.println("Résultat aplati : " + flat);
    }
}