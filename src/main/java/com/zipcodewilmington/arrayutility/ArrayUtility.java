package com.zipcodewilmington.arrayutility;

import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<Type> {

    ArrayList<Type> list;
    Map<Type, Integer> occurrences;
    public ArrayUtility(Type[] inputArray) {
        list = new ArrayList<>();
        occurrences = new HashMap<>();
        for (Type t : inputArray) {
            list.add(t);
            if (occurrences.containsKey(t)) { occurrences.put(t, occurrences.get(t) + 1); }
            else { occurrences.put(t, 1); }
        }
    }

    public Integer countDuplicatesInMerge(Type[] arrayToMerge, Type valueToEvaluate) {
        ArrayUtility<Type> temp = new ArrayUtility<>(arrayToMerge);
        Map<Type, Integer> matches = new HashMap<>();
        return this.occurrences.get(valueToEvaluate) + temp.getNumberOfOccurrences(valueToEvaluate);
    }

    public Type getMostCommonFromMerge(Type[] arrayToMerge) {
        ArrayUtility<Type> temp = new ArrayUtility<>(arrayToMerge);
        Map<Type, Integer> matches = new HashMap<>();
        for (Type t : arrayToMerge) {
            int totalAmt = temp.getNumberOfOccurrences(t) + this.occurrences.get(t);
            matches.put(t, totalAmt);
        }
        int highestOcc = 0;
        Type highestVal = null;
        for (Map.Entry<Type, Integer> entry : matches.entrySet()) {
            if (entry.getValue() > highestOcc) {
                highestVal = entry.getKey();
                highestOcc = entry.getValue();
            }
        }
        return highestVal;
    }

    public Integer getNumberOfOccurrences(Type valueToEvaluate) {
        return (occurrences.containsKey(valueToEvaluate)) ? occurrences.get(valueToEvaluate) : 0;
    }

    public Type[] removeValue(Type valueToRemove) {
        ArrayList<Type> list = new ArrayList<>();
        for (Type t : this.list) {  if (!t.equals(valueToRemove)) { list.add(t); }}
        this.list.clear();
        this.list.addAll(list);
        this.occurrences.remove(valueToRemove);
        Type[] toR = (Type[]) java.lang.reflect.Array.newInstance(list.get(0).getClass(), list.size());
        for (int i = 0; i < list.size(); i++) { toR[i] = list.get(i); }
        return toR;
    }
}
