package drugi;
import java.util.*;
import java.util.Map.Entry;


public class MinDka {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

	      //1. redak = skup stanja odvojenih zarezom, leksikografski poredana
	        String prviRedak = scanner.nextLine().trim();
	        String[] arr = prviRedak.split(",");

	        ArrayList<String> skupStanja = new ArrayList<>();

	        for (String s : arr) {
	            String[] subArrayStrings = s.split(",");

	            for (String subStr : subArrayStrings) {
	                skupStanja.add(subStr);
	            }
	        }

	      //2. redak = skup simbola abecede odvojenih zarezom, leksikografski poredana.
	        String drugiRedak = scanner.nextLine().trim();
	        String[] array = drugiRedak.split(",");

	        ArrayList<String> abecedaSimbola = new ArrayList<>();

	        for (String s : array) {
	            String[] subArrayStrings = s.split(",");

	            for (String subStr : subArrayStrings) {
	            abecedaSimbola.add(subStr);
	            }

	        }
	        


	      //3. redak = skup prihvatljivih stanja odvojenih zarezom, leksikografski poredana.
	        String treciRedak = scanner.nextLine().trim();
	        String[] array4 = treciRedak.split(",");

	        ArrayList<String> prihvatljivaStanja = new ArrayList<>();

	        for (String s : array4) {
	            String[] subArrayStrings = s.split(",");

	            for (String subStr : subArrayStrings) {
	            prihvatljivaStanja.add(subStr);
	            }

	        }

	      //4. redak: Pocetno stanje.
	        String pocStanje = scanner.nextLine().trim();

	      // 5. redak i svi ostali retci: Funkcija prijelaza u formatu trenutnoStanje,simbolAbecede->iduceStanje, , pri cemu su prijelazi leksikografski poredani.
	        Map<Map<String, String>, String> prijelazi = new LinkedHashMap<>();
	        String input;
	        String[] parts;
	        String[] stateSymbol;
	        String nextState;

	        while (scanner.hasNextLine()) {
	            input = scanner.nextLine().trim();

	            if (input.isEmpty()) {
	                break;
	            }

	            parts = input.split("->");
	            stateSymbol = parts[0].split(",");
	            nextState = parts[1];

	            String trenutnoStanje = stateSymbol[0];
	            String simbolAbecede = stateSymbol[1];

	            Map<String, String> trenISimbol = new HashMap<>();
	            trenISimbol.put(trenutnoStanje, simbolAbecede);

	            String iduceStanje = nextState;

	            prijelazi.put(trenISimbol, iduceStanje);
	        }

/*
	        // Ispis svih podataka koje je korisnik unio
	        System.out.println(skupStanja);
	        System.out.println(abecedaSimbola);
	        System.out.println(prihvatljivaStanja);
	        System.out.println(pocStanje);
	        for (Map.Entry<Map<String, String>, String> entry : prijelazi.entrySet()) {
	            Map<String, String> trenISimbol = entry.getKey();
	            String iduceStanje = entry.getValue();

	            String kljuc = "";
	            for (Map.Entry<String, String> entry2 : trenISimbol.entrySet()) {
	                kljuc = entry2.getKey() + "," + entry2.getValue();
	            }

	            System.out.print(kljuc + " -> ");
	            System.out.println(iduceStanje);
	        }

	   */     
	        scanner.close();
	        
	        //trazimo koja su stanja dostizna
	        LinkedHashSet<String> dostStanja = new LinkedHashSet<>();
	        dostStanja.add(pocStanje);
	        for (int i = 0; i < dostStanja.size(); ++i) {
	            for (int j = 0; j < abecedaSimbola.size(); ++j) {
	                for (Map.Entry<Map<String, String>, String> entry : prijelazi.entrySet()) {
	                    Map<String, String> innerMap = entry.getKey();
	                    String value = entry.getValue();
	                    if (innerMap.size() == 1 && innerMap.containsKey(dostStanja.toArray()[i]) && innerMap.containsValue(abecedaSimbola.get(j))) {
	                        dostStanja.add(value);
	                    }
	                }
	            }
	        }
	        
	        
	        List<String> sortedList = new ArrayList<>(dostStanja);
	        Collections.sort(sortedList);
	        dostStanja = new LinkedHashSet<>(sortedList);
	        
	       //System.out.println(dostStanja);
	       
	       /*
	       ArrayList<ArrayList<ArrayList<String>>> tablica = new ArrayList<>();
	       ArrayList<ArrayList<String>> redak = new ArrayList<>();
	       ArrayList<String> celija = new ArrayList<>();
	       
	       ArrayList<LinkedHashSet<String>> promatranaStanja = new ArrayList<>();
	       LinkedHashSet<String> parPromatranihStanja = new LinkedHashSet<>();	       
	       
	       
	       for(int i = 0; i < dostStanja.size(); ++i) {
	    	    for(int j = i + 1; j < dostStanja.size(); ++j) {
	    	        parPromatranihStanja.add(dostStanja.toArray()[i]);
	    	        parPromatranihStanja.add(dostStanja.toArray()[j]);
	    	    }
	    	}
	        
	        */
	        

	       	ArrayList<String> dostStanjaArray = new ArrayList<>();
	       	dostStanjaArray.addAll(dostStanja);
	       	
	       	for(int j = 0; j < skupStanja.size();) {
	       		boolean nasao = false;
	       		for(int i = 0; i < dostStanjaArray.size(); ++i) {
	       			if((skupStanja.get(j).equals(dostStanjaArray.get(i)))) {
	       			nasao = true;	       		
	       			}
	       		}
	       		if(nasao == false) {
       			skupStanja.remove(skupStanja.get(j));
	       		}
       			else {
       			++j;
       			}
	       		}
	       	
	       	for(int j = 0; j < prihvatljivaStanja.size();) {
	       		boolean nasao = false;
	       		for(int i = 0; i < dostStanjaArray.size(); ++i) {
	       			if((prihvatljivaStanja.get(j).equals(dostStanjaArray.get(i)))) {
	       			nasao = true;	       		
	       			}
	       		}
	       		if(nasao == false) {
	       			prihvatljivaStanja.remove(prihvatljivaStanja.get(j));
	       		}
       			else {
       			++j;
       			}
	       		}
	       	
	       	//System.out.println(prihvatljivaStanja);

	       	
	       	Iterator<Map.Entry<Map<String, String>, String>> iterator = prijelazi.entrySet().iterator();
	       	while (iterator.hasNext()) {
	       	    Map.Entry<Map<String, String>, String> entry = iterator.next();
	       	    Map<String, String> trenISimbol = entry.getKey();
	       	    boolean nasao = false;
	       	 for(int i = 0; i < dostStanjaArray.size(); ++i) {
	       	    if ((trenISimbol.containsKey(dostStanjaArray.get(i)))) {
	       	    	nasao = true;
	       	    }
	       	    }
	       	 	if(!nasao)
	       	        iterator.remove();
	       	    }
	       	
	       	/*
	       	System.out.println(skupStanja);
	        System.out.println(prihvatljivaStanja);
	        
	        for (Map.Entry<Map<String, String>, String> entry : prijelazi.entrySet()) {
	            Map<String, String> trenISimbol = entry.getKey();
	            String iduceStanje = entry.getValue();

	            String kljuc = "";
	            for (Map.Entry<String, String> entry2 : trenISimbol.entrySet()) {
	                kljuc = entry2.getKey() + "," + entry2.getValue();
	            }

	            System.out.print(kljuc + " -> ");
	            System.out.println(iduceStanje);
	        }
	        */
	       	
	       	// minimiziranje DKA po algoritmu 2 s predavanja
	        List<LinkedHashSet<String>> podudarnaStanja = new LinkedList<>();
	        LinkedHashSet<String> prihvatljStanja = new LinkedHashSet<>(prihvatljivaStanja);
	        podudarnaStanja.add(prihvatljStanja); // kao prvi element u podudarna stanja stavljamo prihvatljiva stanja

	        
	        ArrayList<String> nePrihvatljivaStanja = new ArrayList<>();

	        for(int i = 0; i < skupStanja.size(); ++i) {
	        
	       		boolean prihvStUDost = false;
	       		for(int j = 0; j < prihvatljivaStanja.size();++j) {
	       			if((prihvatljivaStanja.get(j).equals(skupStanja.get(i)))) {
	       				prihvStUDost = true;	       		
	       			}
	       		}
	       		if(prihvStUDost == false) {
	       			nePrihvatljivaStanja.add(skupStanja.get(i));
	       		}
	       		}
	        
	        
	        LinkedHashSet<String> nePrihvatljStanja = new LinkedHashSet<>(nePrihvatljivaStanja);

	        
	        
	        podudarnaStanja.add(nePrihvatljStanja); // kao drugi element u podudarna stanja stavljamo neprihvatljiva stanja
	        //System.out.println(nePrihvatljStanja);
	        
	        List<String> prihvatljStanja2 = new ArrayList<>(); // treba nam jer kad brisemo elemente iz podudarnih stanja micu se i iz prihv. st.
	        prihvatljStanja2.addAll(prihvatljStanja);


	        for(int i = 0; i < podudarnaStanja.size(); ++i) {
	        	LinkedHashSet<String> podudarnaStanjaAtI = new LinkedHashSet<>(prihvatljivaStanja);
	        	podudarnaStanjaAtI = podudarnaStanja.get(i);
	        	LinkedList<String> velikaGrupa = new LinkedList<>(podudarnaStanjaAtI);
	            //System.out.println(velikaGrupa);
	            
	            if ((velikaGrupa.size() <= 1) == false) {
	            	
	                LinkedHashSet<String> malaGrupa = new LinkedHashSet<>();

	                boolean jesuIstaGrupa;
	            for (int j = 0; j < velikaGrupa.size(); ++j) {
	            	    for (int k = j + 1; k < velikaGrupa.size(); ++k) {
	            	       for (int a = 0; a < abecedaSimbola.size(); ++a) {
	            	           jesuIstaGrupa = false;
	            	            for (LinkedHashSet<String> currentGroup : podudarnaStanja) {
	            	                Map<String, String> prijelaziKey = new HashMap<>();
	            	                prijelaziKey.put(velikaGrupa.get(j), abecedaSimbola.get(a));
	            	                //System.out.println(prijelaziKey);
	            	                Map<String, String> prijelaziKey2 = new HashMap<>();
	            	                prijelaziKey2.put(velikaGrupa.get(k), abecedaSimbola.get(a));
	            	                //System.out.println(prijelaziKey2);
	            	                //System.out.println(prijelazi.get(prijelaziKey));
	            	                //System.out.println(prijelazi.get(prijelaziKey2));

	            	                if (currentGroup.contains(prijelazi.get(prijelaziKey)) && currentGroup.contains(prijelazi.get(prijelaziKey2))) {
	            	                    jesuIstaGrupa = true;
	            	                    break; 
	            	                }
	            	            }
	            	            
	            	            if (jesuIstaGrupa == false) {
	                                malaGrupa.add(velikaGrupa.get(k));
	                                //System.out.println(malaGrupa);
	                                podudarnaStanja.get(i).remove(velikaGrupa.remove(k));
	                                //System.out.println(podudarnaStanja);
	                                --k; 
	                                break;
	                            }
	            	       }
	            	    }
	            	  }
	            	
	            	if (!(malaGrupa.isEmpty())) {
	            		
	                    podudarnaStanja.add(malaGrupa);
	                }

	            	
	            }


	        }
	        
	        //System.out.println(podudarnaStanja);
	        
	        for (LinkedHashSet<String> podStanjaGrupa : podudarnaStanja) {
	        int velicinapodStanjaGrupa = podStanjaGrupa.size();
	            if (velicinapodStanjaGrupa > 1) {
	            	
	                LinkedList<String> podStanjaGrupaLista = new LinkedList<>(podStanjaGrupa);
	                
	                for (int i = 1; i < podStanjaGrupaLista.size(); ++i) {
	                    String minStanje = podStanjaGrupaLista.get(i);
	                   //System.out.println(minStanje);
	                    
	                    skupStanja.remove(minStanje);
	                    prihvatljivaStanja.remove(minStanje);
	                    
	                    if (pocStanje.equals(minStanje)) {
	                        pocStanje = podStanjaGrupaLista.get(0);
	                    }
	                    

	                    LinkedHashMap<Map<String, String>, String> prijelazi2 = new LinkedHashMap<>(Map.copyOf(prijelazi));
	                    
	                    for (Map.Entry<Map<String, String>, String> entry : prijelazi2.entrySet()) {
	                        Map<String, String> kljucMapa = entry.getKey();
	                        
	                        if (entry.getValue().equals(minStanje)) {
	                            prijelazi.put(kljucMapa, podStanjaGrupaLista.get(0));
	                        }
	                        
	                        if (kljucMapa.containsKey(minStanje)) {
	                            prijelazi.remove(kljucMapa);
	                        }
	                        
	                     }
	                }
	             }
	         }
	        
	        LinkedHashSet<String> stanjaSet = new LinkedHashSet<>(skupStanja);
	        LinkedHashSet<String> abecedaSet = new LinkedHashSet<>(abecedaSimbola);
	        
	        ArrayList<String> stanjaArray = new ArrayList<>();
	       	stanjaArray.addAll(stanjaSet);
	        
	        for(int j = 0; j < prihvatljStanja2.size();) {
	       		boolean nasao = false;
	       		for(int i = 0; i < stanjaArray.size(); ++i) {
	       			if((prihvatljStanja2.get(j).equals(stanjaArray.get(i)))) {
	       			nasao = true;	       		
	       			}
	       		}
	       		if(nasao == false) {
	       			prihvatljStanja2.remove(prihvatljStanja2.get(j));
	       		}
       			else {
       			++j;
       			}
	       		}
	        
	        LinkedHashSet<String> prihvatljStanja2Set = new LinkedHashSet<>(prihvatljStanja2);


	        
	        String spojeniElementi = String.join(",", stanjaSet);
	        System.out.println(spojeniElementi);
	        
	        String spojeniElementi2 = String.join(",", abecedaSet);
	        System.out.println(spojeniElementi2);
	        
	        String spojeniElementi3 = String.join(",", prihvatljStanja2Set);
	        System.out.println(spojeniElementi3);
	        
	        System.out.println(pocStanje);
	        
	        for (Map.Entry<Map<String, String>, String> entry : prijelazi.entrySet()) {
	            Map<String, String> trenISimbol = entry.getKey();
	            String iduceStanje = entry.getValue();

	            String kljuc = "";
	            for (Map.Entry<String, String> entry2 : trenISimbol.entrySet()) {
	                kljuc = entry2.getKey() + "," + entry2.getValue();
	            }

	            System.out.print(kljuc + "->");
	            System.out.println(iduceStanje);
	        }
	        
	        
	}

}
