#!/bin/bash

cp -r ./donnees/ ./bin/

echo Ca compile...
javac -encoding utf8 @compile.list


echo Lancement de l\'interface graphique...
java -cp "./bin:$CLASSPATH" controleur.Controleur

echo Fin de l\'execution.

