# Dactylo-game
Bienvenue dans notre jeu Dactylo-Game !

## Compilation
Il s'agit d'un projet maven. Il vous faudra donc avoir installé maven au préalable sur votre machine. Pour l'installer vous pouvez suivre les instructions décrites ici:
- https://maven.apache.org/download.cgi
- https://maven.apache.org/install.html

Vous pouvez ensuite compiler le projet avec la commande suivante:
```bash
mvn package
```

## Execution
Après compilation, le jar du projet se situe dans le dossier **./target**. Pour le lancer, vous pouvez executer la commande suivante:
```bash
java -jar target/dactylo-game*.jar
```

## Compilation et execution
Pour compiler et executer le programme plus facilement, vous pouvez executer le fichier `run.sh`. Il compile puis execute le projet :
```bash
./run.sh
```