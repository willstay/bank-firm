package com.willstay.getbanknotesstrategy;

import com.willstay.atm.NoMoneyException;
import com.willstay.banknote.BankNote;
import com.willstay.banknotecontainer.BankNoteCell;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class GetMaxBankNotesStrategy implements GetBankNotesStrategy {
    @Override
    public List<BankNote> getBankNotes(int money, TreeMap<Integer, BankNoteCell> nominalCellTreeMap) {
        List<BankNote> bankNotes = new ArrayList<>();
        for (Integer nominal : nominalCellTreeMap.descendingKeySet()) {
            while (nominal <= money && !cellHasBankNote(nominalCellTreeMap, nominal)) {
                bankNotes.add(nominalCellTreeMap.get(nominal).getBankNote());
                money -= nominal;
                if (money == 0) {
                    return bankNotes;
                }
            }
        }
        throw new NoMoneyException();
    }

    private boolean cellHasBankNote(TreeMap<Integer, BankNoteCell> nominalCellTreeMap, Integer nominal) {
        return nominalCellTreeMap.get(nominal).empty();
    }
}
