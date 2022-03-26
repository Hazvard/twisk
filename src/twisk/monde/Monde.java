package twisk.monde;

import java.util.HashMap;
import java.util.Iterator;

public class Monde implements Iterable<Etape>{

    protected GestionnaireEtape lesEtapes;
    protected SasEntree entree ;
    protected SasSortie sortie ;

    public Monde(){
        lesEtapes = new GestionnaireEtape();
        entree = new SasEntree();
        sortie = new SasSortie();
    }

    @Override
    public String toString() {
        return "Monde : \n" +
                "gstEtape=" + lesEtapes.toString() + " ;\n" +
                "entree = " + entree.toString() + " ;\n" +
                "sortie = " + sortie.toString() + " ;\n";
    }

    public void ajouter(Etape ... etapes){
        lesEtapes.ajouter(etapes);
    }

    public void aCommeEntree(Etape ... etapes){
        for( Etape etape : etapes){
            etape.setEtapeEntree(true);
        }
        entree.ajouterSuccesseur(etapes);
        ajouter(etapes);
    }

    public void aCommeSortie(Etape ... etapes){
        for( Etape etape : etapes){
            etape.ajouterSuccesseur(sortie);
            etape.setEtapeSortie(true);
        }
        ajouter(etapes);
    }

    public int nbEtapes() {
        return  lesEtapes.nbEtapes();
    }

    public String constantePourC() {
        //HashMap<Integer, String> hash = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Etape> iterator = iterator();
        int n = 0;
        while (iterator.hasNext()) {
            Etape etapeTempo = iterator().next();
            if (etapeTempo.isEtapeEntree()) {
                //Cas entrée
                //hash.put(iterator.next().getNumEtape(), iterator.next().getNom());
                stringBuilder.append("#define ");
                stringBuilder.append(etapeTempo.getNom());
                stringBuilder.append(" " + n + "\n");
                n++;
            } else if (!etapeTempo.isEtapeSortie()) {
                //hash.put(iterator.next().getNumEtape(), iterator.next().getNom());
                //Cas pas sortie
                stringBuilder.append("#define ");
                stringBuilder.append(etapeTempo.getNom());
                stringBuilder.append(" " + n + "\n");
                n++;
            } else {
                //Cas sortie
                stringBuilder.append("#define ");
                stringBuilder.append(etapeTempo.getNom());
                stringBuilder.append(" " + n + "\n");
                n++;
            }
        }
        return stringBuilder.toString();
    }

    public String toC(){
        return "#include \"def.h\"\n\nvoid simulation(int ids){\n"+ entree.toC() + "}";
    }

    public void setNumSortie(int numSortie){
        sortie.setNumEtape(numSortie);
    }

    public int nbGuichets(){//Marche pas ________
        int n = 0;
        if (lesEtapes.nbEtapes() > 0){
            Iterator<Etape> iterator = iterator() ;
            while(iterator.hasNext()) {
                Etape etape = iterator.next();
                if(etape.estUnGuichet())
                    n++  ;
            }
        }
        return n;
    }


    @Override
    public Iterator<Etape> iterator() {
        return lesEtapes.iterator();
    }
}
