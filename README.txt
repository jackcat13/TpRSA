Code fait en Java. Tous les fichiers sources sont dans le même dossier et peuvent donc être compilé comme demandé dans le sujet.

4. Quelques fonctions de base pour RSA



5. Mise en oeuvre de RSA

-Gencle (5.1 Génération de la paire de clé)

Pour tester Gencle, il faut executer la classe MainGencle. Il s'agit d'un script interactif qui demande des input à l'utilisaeur :
    -La taille des blocs
    -Le nom du destinataire
Ce script permet donc de générer les clés publiques et privées pour un utilisateur (en fonction du nom de destinataire choisi) et de les sauvegarder dans des fichiers:
    -<name>.pub
    -<name>.priv
Il reste un problème dans ce script, c'est qu'il ne fonctionne pas à chaque execution. Nous pensions avoir réglé le problème mais nous avons toujours l'exception suivante:
Exception in thread "main" java.lang.ArithmeticException: BigInteger not invertible.
Nous avons essayé la génération aléatoire pour la clé b avec un modulo phi(n), mais l'exception est toujours présente. Il faut donc executer les script jusqu'a son fonctionnement pour générer une paire de clé.



-RSA (5.2 Programme de chiffrement / 5.3 Programme de déchifrement / 5.4 Programme de génération de signature / 5.5 Programme de vérification de signature)

Pour tester RSA, il faut executer la classe MainRSA. Il s'agit à nouvrau d'un script interactif qui demande des input à l'utilisateur:
    -le nom du destinataire
    -le message à crypter
Ce script permet de :
    -chiffrer un message passé en input
    -De signer ce message
    -De déchiffrer le message une fois crypté
    -De vérifier la signature du message crypté

Toutes ces fonctions ont été codées dans la même classe, ainsi il a été possibles de tester toutes les fonctions en un seul script en utilisant un seul message en entrée.