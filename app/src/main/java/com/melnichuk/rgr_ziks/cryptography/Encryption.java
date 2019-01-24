package com.melnichuk.rgr_ziks.cryptography;

public class Encryption {

    private String inputText;
    private String outputText;

    public Encryption(String inputText) {
        this.inputText = inputText.toLowerCase();
    }

    public String getOutputText() {
        return outputText;
    }

    public void encryptText() {
        outputText = "";
        EncryptionTable encryptionTable = new EncryptionTable();
        for (int i = 0; i < inputText.length(); i++) {
            for (int j = 0; j < 51; j++) {
                if (String.valueOf(inputText.charAt(i)).equals(encryptionTable.ALPHABET[j][0])) {
                    switch (encryptionTable.counters[j]) {
                        case 0:
                            outputText += encryptionTable.ALPHABET[j][1];
                            encryptionTable.counters[j]++;
                            break;
                        case 1:
                            outputText += encryptionTable.ALPHABET[j][2];
                            encryptionTable.counters[j]++;
                            break;
                        case 2:
                            outputText += encryptionTable.ALPHABET[j][3];
                            encryptionTable.counters[j] = 0;
                            break;
                    }
                }
            }
        }
    }

    public void decryptText() {
        outputText = "";
        char[] inputTextArray = inputText.toCharArray();
        String str = "";
        EncryptionTable encryptionTable = new EncryptionTable();
        for (int i = 0; i < inputTextArray.length; i++) {
            str += inputTextArray[i];
            if (str.length() == 3) {
                for (int j = 0; j < 51; j++) {
                    if (str.equals(encryptionTable.ALPHABET[j][1]) ||
                            str.equals(encryptionTable.ALPHABET[j][2]) ||
                            str.equals(encryptionTable.ALPHABET[j][3])) {
                        outputText += encryptionTable.ALPHABET[j][0];
                        break;
                    }
                }
                str = "";
            }
        }
    }
}
