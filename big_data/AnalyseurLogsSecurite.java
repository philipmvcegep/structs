import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class AnalyseurLogsSecurite {

    // Tâche : Filtrer les requêtes provenant d'une liste d'IPs bloquées
    public static List<String> filtrerRequetesSuspectes(List<String> tousLesLogs, 
        Set<String> ipsBloquees) {
        List<String> logsSuspects = new ArrayList<>();
        
        for (String log : tousLesLogs) {
            // PIÈGE : .contains() sur une List parcourt toute la liste à chaque tour de boucle !
            String ipCourante = extraireIp(log);
            if (ipsBloquees.contains(ipCourante)) {
                logsSuspects.add(log);
            }
        }
        return logsSuspects;
    }

    private static String extraireIp(String log) {
        return log.split(" ")[0]; // Simule l'extraction d'une IP
    }

    public static void main(String[] args) {
        int n = 1_000_000;
        System.out.println("Génération de " + n + " lignes de logs...");
        List<String> logs = new ArrayList<>();
        Set<String> bloquees = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            logs.add("192.168.1." + (i % 5000) + " GET /index.html");
            if (i < 100000) bloquees.add("192.168.1." + i);
        }

        long debut = System.currentTimeMillis();
        List<String> resultat = filtrerRequetesSuspectes(logs, bloquees);
        long fin = System.currentTimeMillis();

        System.out.println("Logs suspects filtrés : " + resultat.size());
        System.out.println("Temps d'exécution : " + (fin - debut) + " ms");
    }
}
