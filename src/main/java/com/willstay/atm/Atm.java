package com.willstay.atm;

import com.willstay.banknote.BankNote;
import com.willstay.banknotecontainer.BankNoteCell;
import com.willstay.getbanknotesstrategy.GetBankNotesStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class Atm {
    private final TreeMap<Integer, BankNoteCell> nominalCellTreeMap = new TreeMap<>();
    //private final TreeMap<Integer, BankNoteCell> defaultNominalCellTreeMap = new TreeMap<>();
    private final List<BankNote> defaultContainerList;

    public Atm(List<BankNote> defaultContainerList) {
        this.defaultContainerList = defaultContainerList;
        addBankNotes(defaultContainerList, nominalCellTreeMap);
        // addBankNotes(defaultContainerList, defaultContainerTreeMap);
    }

    public void setContainersValueToDefault() {
        nominalCellTreeMap.clear();
        addBankNotes(defaultContainerList, nominalCellTreeMap);
        //nominalCellTreeMap.putAll(defaultContainerTreeMap);
    }

    public void addBankNotes(List<BankNote> bankNoteList) {
        addBankNotes(bankNoteList, nominalCellTreeMap);
    }

    private void addBankNotes(List<BankNote> bankNoteList, Map<Integer, BankNoteCell> containerTreeMap) {
        for (BankNote bankNote : bankNoteList) {
            if (containerTreeMap.containsKey(bankNote.getValue())) {
                containerTreeMap.get(bankNote.getValue()).addBankNote(bankNote);
            } else {
                BankNoteCell bankNoteCell = new BankNoteCell();
                bankNoteCell.addBankNote(bankNote);
                containerTreeMap.put(bankNote.getValue(), bankNoteCell);
            }
        }
    }

    public List<BankNote> getBankNotes(GetBankNotesStrategy getBankNotesStrategy, int money) {
        return getBankNotesStrategy.getBankNotes(money, nominalCellTreeMap);
    }

    public List<BankNote> getAllBankNotes() {
        List<BankNote> bankNoteList = new ArrayList<>();
        nominalCellTreeMap.values().stream()
                .forEach(bankNoteCell -> {
                    while (!bankNoteCell.empty()) {
                        bankNoteList.add(bankNoteCell.getBankNote());
                    }
                });
//        for(BankNoteCell bankNoteContainer : nominalCellTreeMap.values()){
//            while (!bankNoteContainer.empty()){
//                bankNoteList.add(bankNoteContainer.getBankNote());
//            }
//        }
        return bankNoteList;
    }

    public int getATMBalance() {
        int balance = 0;
        List<Integer> allMoneyList = nominalCellTreeMap.values().stream()
                .flatMap(bankNoteCell -> bankNoteCell.readBankNotes().stream())
                .map(BankNote::getValue)
                .collect(Collectors.toList());
        for (Integer money : allMoneyList) {
            balance += money;
        }
        return balance;
    }
}
