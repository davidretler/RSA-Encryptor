#!/bin/sh
javac RSA/**/*.java
jar cfe RSA_Demo.jar RSA.encryptor.GUIDriver RSA/

javac -source 1.5 -target 1.5 RSA/**/*.java
jar cfe RSA_Demo_Legacy.jar RSA.encryptor.GUIDriver RSA/
