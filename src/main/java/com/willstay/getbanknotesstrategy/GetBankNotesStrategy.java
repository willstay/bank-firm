package com.willstay.getbanknotesstrategy;

import com.willstay.banknote.BankNote;
import com.willstay.banknotecontainer.BankNoteCell;

import java.util.List;
import java.util.TreeMap;

public interface GetBankNotesStrategy {
    List<BankNote> getBankNotes(int money, TreeMap<Integer, BankNoteCell> containerTreeMap);
}
