package treci;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

//simulator deterministickog potisnog automata (DPA) koji nizove prihvaca prihvatljivim stanjem

public class SimPa {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//1. redak: Ulazni nizovi odvojeni znakom |. Simboli svakog pojedinog niza odvojeni su zarezom
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        String prviRedak = scanner.nextLine();
        String[] ulazniNizovi = prviRedak.split("\\|");

        for (String ulazniNiz : ulazniNizovi) {
            ArrayList<String> ulNizLista = new ArrayList<>();
            String[] simboli = ulazniNiz.split(",");
            for (String simbol : simboli) {
                ulNizLista.add(simbol);
            }
            list.add(ulNizLista);
        }
        
        //2. redak: Skup stanja odvojenih zarezom
        String drugiRedak = scanner.nextLine().trim();
        String[] arr = drugiRedak.split(",");

        ArrayList<String> skupStanjaList = new ArrayList<>();

        for (String s : arr) {
            String[] subArrayStrings = s.split(",");

            for (String subStr : subArrayStrings) {
                skupStanjaList.add(subStr);
            }

        }
        
        HashSet<String> skupStanja = new HashSet<>(skupStanjaList);
        
        //3. redak: Skup ulaznih znakova odvojenih zarezom
        String treciRedak = scanner.nextLine().trim();
        String[] arr2 = treciRedak.split(",");

        ArrayList<String> skupUlaznihZnakovaList = new ArrayList<>();

        for (String s : arr2) {
            String[] subArrayStrings = s.split(",");

            for (String subStr : subArrayStrings) {
            	skupUlaznihZnakovaList.add(subStr);
            }

        }
        
        HashSet<String> skupUlaznihZnakova = new HashSet<>(skupUlaznihZnakovaList);
        
        //4. redak: Skup znakova stoga odvojenih zarezom
        String cetvrtiRedak = scanner.nextLine().trim();
        String[] arr3 = cetvrtiRedak.split(",");

        ArrayList<String> skupZnakovaStogaList = new ArrayList<>();

        for (String s : arr3) {
            String[] subArrayStrings = s.split(",");

            for (String subStr : subArrayStrings) {
            	skupZnakovaStogaList.add(subStr);
            }

        }
        
        HashSet<String> skupZnakovaStoga = new HashSet<>(skupZnakovaStogaList);
        
        //5. redak: Skup prihvatljivih stanja odvojenih zarezom
        String petiRedak = scanner.nextLine().trim();
        String[] array4 = petiRedak.split(",");

        ArrayList<String> prihvatljivaStanjaList = new ArrayList<>();

        for (String s : array4) {
            String[] subArrayStrings = s.split(",");

            for (String subStr : subArrayStrings) {
            	prihvatljivaStanjaList.add(subStr);
            }

        }
        
        HashSet<String> prihvatljivaStanja = new HashSet<>(prihvatljivaStanjaList);

        
        //6. redak: Pocetno stanje
        String pocStanje1 = scanner.nextLine().trim();
        
        //7. redak: Pocetni znak stoga
        String znakStoga = scanner.nextLine().trim();
        
        //8. redak i svi ostali retci: Funkcija prijelaza u formatu: trenutnoStanje,ulazniZnak,znakStoga->novoStanje,nizZnakovaStoga
        Map<ArrayList<String>, ArrayList<String>> prijelazi = new LinkedHashMap<>();
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break; 
            }

            String[] parts = line.split("->"); 
            String[] leftSide = parts[0].split(","); 
            String[] rightSide = parts[1].split(","); 

            ArrayList<String> keyList = new ArrayList<>();
            ArrayList<String> valueList = new ArrayList<>();

            for (String s : leftSide) {
                keyList.add(s.trim());
            }
            for (String s : rightSide) {
                valueList.add(s.trim());
            }

            prijelazi.put(keyList, valueList);
        }

        
        /*
        // Ispis svih podataka koje je korisnik unio
        System.out.println(list);
        System.out.println(skupStanja);
        System.out.println(skupUlaznihZnakova);
        System.out.println(skupZnakovaStoga);
        System.out.println(prihvatljivaStanja);
        System.out.println(pocStanje1);
        System.out.println(znakStoga);

        for (Map.Entry<ArrayList<String>, ArrayList<String>> entry : prijelazi.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
        */
        
		scanner.close();
        
        String sviZnakoviStoga = null;
        //sviZnakoviStoga = sviZnakoviStoga.concat(znakStoga);
    	sviZnakoviStoga = znakStoga;
        
        ArrayList<String> prijelaziValue = null;
        boolean naden = false;
        boolean naden2 = false;
        boolean gotovo = false;
        
        for(ArrayList<String> ulNizLista : list) {
        	gotovo = false;
        	//while (gotovo != true) {
    		String pocStanje = pocStanje1;
        	sviZnakoviStoga = " ";
            //sviZnakoviStoga = sviZnakoviStoga.concat(znakStoga);
        	sviZnakoviStoga = znakStoga;
        
        	System.out.printf("%s#%s", pocStanje1, znakStoga);
        	//outer:
        	int velicina = ulNizLista.size();
        	for (int i = 0; i < velicina; ++i) {
        		//gotovo = false;
        		
                String simbUlNiza = ulNizLista.get(i).toString();
                //System.out.println(sviZnakoviStoga.get(0).charAt(0));
                
                //char pom = sviZnakoviStoga.get(0).charAt(0);
                //String zn = "" + pom;
                
                
                for (Map.Entry<ArrayList<String>, ArrayList<String>> entry : prijelazi.entrySet()) {
                	naden = false;
                    ArrayList<String> key = entry.getKey();
                    ArrayList<String> value = entry.getValue();
                    String firstArg = key.get(0);
                    String secondArg = key.get(1);
                    String thirdArg = key.get(2);
                    String pom = Character.toString(sviZnakoviStoga.charAt(0));
                    if (firstArg.equals(pocStanje) && secondArg.equals(simbUlNiza) && thirdArg.equals(pom)) {
                        prijelaziValue = value;
                        naden = true;
                        break;  
                    }
                }
                
                
                if (naden == false) {
                	if(gotovo == false) {
                	for (Map.Entry<ArrayList<String>, ArrayList<String>> entry : prijelazi.entrySet()) {
                    	naden2 = false;
                        ArrayList<String> key = entry.getKey();
                        ArrayList<String> value = entry.getValue();
                        String firstArg = key.get(0);
                        String secondArg = key.get(1);
                        String thirdArg = key.get(2);
                        String pom = Character.toString(sviZnakoviStoga.charAt(0));
                        if (firstArg.equals(pocStanje) && secondArg.equals("$") && thirdArg.equals(pom)) {
                            prijelaziValue = value;
                            naden2 = true;
                            break;  
                        }
                    }
                	
                	--i;
                	
                	if(naden2 == false) {
                		System.out.printf("|%s|%d%n", "fail", 0);
                		gotovo = true;
                		//break outer;
                	}
                }
                }
                
                if(gotovo == false) {

                pocStanje = prijelaziValue.get(0);
                
                //char pom2 = sviZnakoviStoga.get(0).charAt(0);
                //zn = "" + pom;
                
                
                sviZnakoviStoga = sviZnakoviStoga.replaceFirst(".", "");
                
                //sviZnakoviStoga.remove(pom);
                
                sviZnakoviStoga = sviZnakoviStoga.isEmpty() ? "$" : sviZnakoviStoga;

                if (prijelaziValue.get(1).equals("$") == false) {
                    sviZnakoviStoga = sviZnakoviStoga.equals("$") ? prijelaziValue.get(1) : prijelaziValue.get(1) + sviZnakoviStoga;
                }
                
                
                //Collections.reverse(sviZnakoviStoga);
                 System.out.printf("|%s#%s", pocStanje, sviZnakoviStoga);
        	//}
                
                //int a = 0;
                    
                 boolean found = prihvatljivaStanja.contains(pocStanje);
                 
                 if ((found == true) && (gotovo == false) && (i == ulNizLista.size() - 1)) {
                     System.out.printf("|%d%n", 1);
                     gotovo = true;
                 }
        	}
                if(gotovo == false) {
                    
                    //pom = sviZnakoviStoga.get(0).charAt(0);
                    //zn = "" + pom;
                    
                    
                    for(int k = 0; k < sviZnakoviStoga.length(); ++k) {
                    	if(gotovo == false) {
                    	ArrayList<String> prijelaziValue2 = null; 
                    	boolean naden3 = false;
                    	for (Map.Entry<ArrayList<String>, ArrayList<String>> entry : prijelazi.entrySet()) {
                        	naden3 = false;
                            ArrayList<String> key = entry.getKey();
                            ArrayList<String> value = entry.getValue();
                            String firstArg = key.get(0);
                            String secondArg = key.get(1);
                            String thirdArg = key.get(2);
                            String pom = Character.toString(sviZnakoviStoga.charAt(0));
                            if (firstArg.equals(pocStanje) && secondArg.equals("$") && thirdArg.equals(pom)) {
                                prijelaziValue2 = value;
                                naden3 = true;
                                break;  
                            }
                        }
                    	
                    	if(naden3 == false) {
                    		break;
                    	}
                    	
                        pocStanje = prijelaziValue2.get(0);
                        
                        //char pom2 = sviZnakoviStoga.get(0).charAt(0);
                        //zn = "" + pom;
                        
                        sviZnakoviStoga = sviZnakoviStoga.replaceFirst(".", "");
                        
                        //sviZnakoviStoga.remove(pom);
                        
                        sviZnakoviStoga = sviZnakoviStoga.isEmpty() ? "$" : sviZnakoviStoga;

                        if (prijelaziValue2.get(1).equals("$") == false) {
                            sviZnakoviStoga = sviZnakoviStoga.equals("$") ? prijelaziValue2.get(1) : prijelaziValue2.get(1) + sviZnakoviStoga;
                        }
                        
                        
                         System.out.printf("|%s#%s", pocStanje, sviZnakoviStoga);
                        
                        
                         boolean found = prihvatljivaStanja.contains(pocStanje);
                         
                         if ((found == true) && (gotovo == false) && (i == ulNizLista.size() - 1)) {
                             System.out.printf("|%d%n", 1);
                             gotovo = true;
                         }
                         
                    } 
        	} 
                    
        	}


        		} // zagrada od simbola
        	if(gotovo == false) {
        		System.out.printf("|%d%n", 0); 
            }
        	}
            
	}
        
        
}
