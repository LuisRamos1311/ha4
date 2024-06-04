package htw.berlin.wi.prog2.service.parsing;

import htw.berlin.wi.prog2.parsing.ExtendableInputParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartInputParser implements ExtendableInputParser {

    @Override
    public Map<Long, Integer> idsAndCountFromInput(String inputLine, Map<String, Long> keywordsToIds) {


        Map<Long, Integer> map = new HashMap<>();

        List<String> wordList = new ArrayList<>(List.of(inputLine.split(" ")));
        List<String> wordList2 = new ArrayList<>(List.of(inputLine.split(" ")));



        int index = 0;
        for (String word : wordList) {
            if(word.equals("doppelt")){
                wordList2.add(wordList.get(index + 1));
            }

            if(word.equals("dreifach")){
                wordList2.add(wordList.get(index + 1));
                wordList2.add(wordList.get(index + 1));
            }
            index++;
        }

        for(Map.Entry<String, Long> keywordToIdEntry : keywordsToIds.entrySet()) {
            for(String word : wordList2) {
                word = word.replace(",","" );
                word = word.replace(".","" );

                if(keywordToIdEntry.getKey().equals(word)) {
                    if(map.containsKey(keywordToIdEntry.getValue())) {
                        map.put(keywordToIdEntry.getValue(), map.get(keywordToIdEntry.getValue()) + 1);
                    }
                    else {
                        map.put(keywordToIdEntry.getValue(), 1);}
                }
            }
        }
        return map; // TODO hier implementieren und die korrekte Map zurückgeben
    }
}
