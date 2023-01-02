# Dactylo-game

## Description du jeu
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
Pour compiler et executer le programme plus facilement, vous pouvez lancer le fichier `run.sh`. Il compile puis execute le projet :
```bash
./run.sh
```

## Tests
Plusieurs fichiers de tests sont présents dans le projet. Ils sont situés dans le dossier `src/test`. Pour lancer les tests, vous pouvez executer le commande suivante:
```bash
mvn test
```

## Utilisation
Après avoir lancé le programme, vous devriez arriver sur la page d'accueil, défini par la classe `HomeView.java`

### HomeView
________________________________________________
![Page d'accueil](readme_resources/home_page.png)
La page d'accueil est assez simple à comprendre. Au centre de la page, plusieurs informations concernant le lancement de votre partie sont présent. Vous devez notamment renseigner votre :
- nom d'utilisateur
- mode de jeu (NORMAL, CHALLENGE, MULTIPLAYER)

Enfin, vous n'avez plus qu'à cliquer sur **Play** pour lancer la partie

### Normal Mode
________________________________________________
![Normal mode](readme_resources/normal_mode.png)
Sur la page du mode normal, le **nom du joueur** est affiché en haut à gauche, ainsi que le **type de jeu** en haut à droite (de même dans le *Challenge Mode*). Au centre se trouve la zone de texte qui va contenir les prochains mots à écrire. Vous allez alors devoir écrire un maximum de mots dans le temps imparti.

Le temps restant est affiché en bas à gauche avec le nombre de mots tapés. Le partie (et donc le chronomètre) ne démarre que lorsque vous commencez à écrire le premier mot de la file. Les prochains apparaissent alors *en gris*, les erreurs *en rouges* et les lettres correctes *en noirs*.
![Exemple partie en mode normal](readme_resources/normal_mode_in_progress.png)
*Voici un exemple de déroulement d'un partie*

### Challenge Mode
Le page du mode challenge est assez simlaire à celle du mode normal. Mais il y a tout de même plusieurs changements. Cette fois, les mots arrivent avec une certaine vitesse qui au augmente au fil des niveaux. Tous les (TODO) mots, le niveau augmente et les mots arrivent de plus en plus vite. Le joueur dispose tout de même de plusieurs en cas d'échec, pour pouvoir continuer la partie. Voici comment sont disposés les différents éléments :
- Le niveau du joueur est indiqué (TODO...)
- Le nombre de vie du joueur sont affichées (TODO:...)
- La vitesse du jeu est indiquée (TODO...)

[TODO: METTRE UNE IMAGE]
*Voici un exemple de déroulement d'un partie*

### Multiplayer Mode
Nous n'avons malheureusement pas eu le temps d'implémenter cette fonctionnalité. Peut être plus tard ?

### Fin de partie (Stats)

