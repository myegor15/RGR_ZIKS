package com.melnichuk.rgr_ziks.cryptography;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

public class MessageSignature implements Serializable {
    private KeyPairGenerator keyPairGenerator;   // Генератор ключевых пар
    private KeyPair keyPair;					 // Пара ключей
    private PrivateKey privateKey;				 // Приватный ключ
    private PublicKey publicKey;                 // Открытый ключ
    private Signature signature;                 // Цифровая подпсь
    private byte[] realSign;

    public MessageSignature(String keyAlg, int keyLenght, String signAlg, String provName) throws
            NoSuchAlgorithmException, NoSuchProviderException
    {

        if ((keyAlg == null) || (signAlg == null)) {
            throw new NullPointerException();
        } else {
            keyPairGenerator = KeyPairGenerator.getInstance(keyAlg);
            keyPairGenerator.initialize(keyLenght, new SecureRandom());
            keyPair = keyPairGenerator.generateKeyPair();
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();
            if (provName == null) {
                signature = Signature.getInstance(signAlg);
            } else {
                signature = Signature.getInstance(signAlg, provName);
            }
        }
    }

    public void signingMessage(FileInputStream msgPath, FileOutputStream sgnPath) throws IOException,
            NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
    {
        if ((msgPath == null) || (sgnPath == null)) {
            throw new NullPointerException();
        }

        //Set private key
        if (privateKey == null) {
            throw new IllegalArgumentException();
        }
        signature.initSign(privateKey);

        //Reading open text and signing message
        BufferedInputStream bufRead = new BufferedInputStream(msgPath);
        byte[] byteMsg = new byte[bufRead.available()];
        bufRead.read(byteMsg);
        signature.update(byteMsg);

        bufRead.close();

        realSign = signature.sign();
        sgnPath.write(realSign);

    }
}