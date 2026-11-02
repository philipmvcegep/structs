// =====================================================
// 4. LAMBDA EXPRESSIONS ET CLASSES ANONYMES
// =====================================================
package threads;
public class LambdasAnonymous {

    public static void main(String[] args) {

        // =====================================================
        // 1. APPROCHE TRADITIONNELLE : CLASSE ANONYME
        // =====================================================

        // Runnable est une interface fonctionnelle.
        // Une classe anonyme permet de l'implémenter directement
        // sans créer un fichier de classe séparé manuellement.

        Runnable traditionalTask = new Runnable() {

            @Override
            public void run() {
                System.out.println("Exécution traditionnelle");
            }
        };

        new Thread(traditionalTask).start();


        // =====================================================
        // 2. APPROCHE MODERNE : EXPRESSION LAMBDA
        // =====================================================

        // Runnable possède une seule méthode abstraite : run().
        // Java peut donc déduire automatiquement son implémentation.
        // La lambda représente directement le comportement à exécuter.

        Runnable lambdaTask = () -> {
            System.out.println("Exécution avec Lambda");
        };

        new Thread(lambdaTask).start();


        // =====================================================
        // 3. COMPARAISON AVEC JAVASCRIPT
        // =====================================================

        // Java :
        Runnable javaTask = () ->
            System.out.println("Hello from Java");

        // JavaScript :
        //
        // const jsTask = () => {
        //     console.log("Hello from JavaScript");
        // };
        //
        // jsTask();

        // Les deux langages utilisent les fonctions fléchées
        // pour représenter du comportement de manière concise.


        // =====================================================
        // 4. PROGRAMMATION FONCTIONNELLE
        // =====================================================

        // Une interface fonctionnelle possède exactement
        // une méthode abstraite.
        //
        // Elle permet de traiter un comportement comme une valeur
        // que l'on peut transmettre à une autre méthode.

        java.util.function.Function<Integer, Integer> square =
            number -> number * number;

        System.out.println(square.apply(5)); // 25

        // Équivalent JavaScript :
        //
        // const square = number => number * number;
        // console.log(square(5)); // 25
    }
}

// =====================================================
// À RETENIR
// =====================================================
//
// Classe anonyme :
// - Implémentation explicite d'une interface.
// - Syntaxe plus longue.
// - Possède son propre contexte this.
//
// Lambda :
// - Syntaxe concise introduite en Java 8.
// - Fonctionne avec les interfaces fonctionnelles.
// - Utilise le contexte lexical de this.
// - Permet de transmettre du comportement comme une valeur.
//
// Programmation fonctionnelle :
// - Fonctions comme paramètres.
// - Transformation des données.
// - Composition des opérations.
// - Réduction du code répétitif.
//
// Comparaison Java / JavaScript :
// - Java : Runnable, Function, Consumer, Predicate.
// - JavaScript : fonctions fléchées et fonctions de première classe.
//
// Attention : une lambda Java n'est pas exactement une fonction
// JavaScript. Java reste un langage statiquement typé et les lambdas
// doivent correspondre à une interface fonctionnelle.
