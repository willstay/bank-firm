package com.willstay.atm;

import com.willstay.banknote.BankNote;
import com.willstay.banknotecontainer.BankNoteContainer;

import java.util.*;
import java.util.stream.Collectors;

public class Atm {
    private final Map<Integer, BankNoteContainer> containerTreeMap = new TreeMap<>(Collections.reverseOrder());
    //private final Map<Integer, BankNoteContainer> defaultContainerTreeMap = new TreeMap<>(Collections.reverseOrder());
    private final List<BankNote> defaultContainerList;

    public Atm(List<BankNote> defaultContainerList) {
        this.defaultContainerList = defaultContainerList;
        addBankNotes(defaultContainerList, containerTreeMap);
        // addBankNotes(defaultContainerList, defaultContainerTreeMap);
    }

    public void setContainersValueToDefault() {
        containerTreeMap.clear();
        addBankNotes(defaultContainerList, containerTreeMap);
        //containerTreeMap.putAll(defaultContainerTreeMap);
    }

    public void addBankNotes(List<BankNote> bankNoteList) {
        addBankNotes(bankNoteList, containerTreeMap);
    }

    private void addBankNotes(List<BankNote> bankNoteList, Map<Integer, BankNoteContainer> containerTreeMap) {
        for (BankNote bankNote : bankNoteList) {
            if (containerTreeMap.containsKey(bankNote.getValue())) {
                containerTreeMap.get(bankNote.getValue()).addBankNote(bankNote);
            } else {
                BankNoteContainer bankNoteContainer = new BankNoteContainer();
                bankNoteContainer.addBankNote(bankNote);
                containerTreeMap.put(bankNote.getValue(), bankNoteContainer);
            }
        }
    }

    public List<BankNote> getBankNotes(int money) {
        List<BankNote> bankNotes = new ArrayList<>();
        for (Integer nominal : containerTreeMap.keySet()) {
            while (nominal <= money && !containerTreeMap.get(nominal).empty()) {
                bankNotes.add(containerTreeMap.get(nominal).getBankNote());
                money -= nominal;
                if (money == 0) {
                    return bankNotes;
                }
            }
        }
        throw new NoMoneyException();
    }

    public List<BankNote> getAllBankNotes() {
        List<BankNote> bankNoteList = new ArrayList<>();
        containerTreeMap.values().stream()
                .forEach(bankNoteContainer -> {
                    while (!bankNoteContainer.empty()) {
                        bankNoteList.add(bankNoteContainer.getBankNote());
                    }
                });
//        for(BankNoteContainer bankNoteContainer : containerTreeMap.values()){
//            while (!bankNoteContainer.empty()){
//                bankNoteList.add(bankNoteContainer.getBankNote());
//            }
//        }
        return bankNoteList;
    }

    public int getATMBalance() {
        int balance = 0;
        List<Integer> allMoneyList = containerTreeMap.values().stream()
                .flatMap(bankNoteContainer -> bankNoteContainer.readBankNotes().stream())
                .map(bankNote -> bankNote.getValue())
                .collect(Collectors.toList());
        for (Integer money : allMoneyList) {
            balance += money;
        }
        return balance;
    }
}
