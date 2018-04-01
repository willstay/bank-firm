package com.willstay.banknotecontainer;

import com.willstay.banknote.BankNote;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BankNoteContainer {
    private final Stack<BankNote> bankNoteStack = new Stack<>();

    public void addBankNote(BankNote bankNote) {
        bankNoteStack.push(bankNote);
    }

    public BankNote getBankNote() {
        if (bankNoteStack.empty()) {
            return null;
        }
        return bankNoteStack.pop();
    }

    public List<BankNote> readBankNotes() {
        return new ArrayList<>(bankNoteStack);
    }

    public boolean empty() {
        return bankNoteStack.empty();
    }
}
