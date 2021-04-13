package com.DMAX101.nomade;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;


public final class App {
    private App() {
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<String> text = new ArrayList<>();
        ArrayList<String> textChanged = new ArrayList<>();
        long n = 0;

        String abc[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

        HashMap<String, String> dictionary = new HashMap<String, String>();
        HashMap<String, Integer> biggest = new HashMap<String, Integer>();
        HashMap<String, Integer> repetitions = new HashMap<String, Integer>();

        while (st.hasMoreTokens()) {
            text.add(st.nextToken());
        }

        for (String letter : abc) {
            dictionary.put(letter, "");
            biggest.put(letter, 0);
        }

        text
            .stream()
            .forEach(map -> {
                if (repetitions.containsKey(map)) {
                    repetitions.put(map, repetitions.get(map) + 1);
                } else {
                    repetitions.put(map, 1);
                }
            });

        repetitions
            .forEach((palavra, quant) -> {
                if ((palavra.length() > 2) && (palavra != ".")) {
                    String letter = String.valueOf(palavra.charAt(0));
                    int currentSize = (palavra.length() - 2) * quant;
                    int actualSize = biggest.get(letter);

                    if (currentSize > actualSize) {
                        biggest.put(letter, currentSize);
                        dictionary.put(letter, palavra);
                    }
                }
            });

        n = text
            .stream()
            .filter(dictionary::containsValue)
            .count();

        text
            .stream()
            .forEach(map -> {
                if (dictionary.containsValue(map)) {
                    textChanged.add(String.valueOf(map.charAt(0) + "."));
                } else {
                    textChanged.add(map);
                }
            });

        System.out.println(textChanged
            .stream()
            .map(Object::toString)
            .collect(Collectors.joining(" ")));

        System.out.println(n);

        text
            .stream()
            .filter(dictionary::containsValue)
            .collect(Collectors.toCollection(LinkedHashSet::new))
            .forEach(map -> {
                if (map != ".") {
                    System.out.println(map.charAt(0) + ". = " + map);
                }
            });
    }
}
