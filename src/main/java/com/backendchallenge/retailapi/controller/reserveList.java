package com.backendchallenge.retailapi.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Ekue created on 6/28/2021
 */


public class reserveList {

    //= new List<String>();
    public static void main(String args[]){
        ArrayList<String> list = new ArrayList<>();
        list.add("Good");
        list.add("Morning");
        list.add("Test");

        System.out.println(list.toString());
        reverseList(list);
    }
    public static void reverseList(List<String> list){
        int listSize = list.size();
//        for (String s: list){
//            s.
//        }
//
        for(int i= listSize; i--; i)


    }


}
