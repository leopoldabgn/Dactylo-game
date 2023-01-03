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

Le temps restant est affiché en bas à gauche avec le nombre de mots tapés. Le partie (et donc le chronomètre) ne démarre que lorsque vous commencez à écrire le premier mot de la file. Les prochains mots apparaissent alors *en gris*, les erreurs *en rouges* et les lettres correctes *en noirs*.
![Exemple partie en mode normal](readme_resources/normal_mode_in_progress.png)
*Voici un exemple de déroulement d'un partie*

### Challenge Mode
Le page du mode challenge est assez similaire à celle du mode normal. Mais il y a tout de même plusieurs changements. Cette fois, les mots arrivent avec une certaine vitesse qui augmente au fil des niveaux. Tous les 100 mots, le niveau augmente et les mots arrivent de plus en plus vite (selon cette fonction 3 * 0.9^n). Le joueur dispose tout de même de plusieurs en cas d'échec, pour pouvoir continuer la partie. Voici comment sont disposés les différents éléments :
- Le niveau et le nombre de vie du joueur sont affichées en bas à gauche de l'écran
- Le nombre de mots écrits est également affiché

![Mode Challenge](readme_resources/challenge_mode_in_progress.png)
*Voici un exemple de déroulement d'une partie avec le mode Challenge*

### Multiplayer Mode
Nous n'avons malheureusement pas eu le temps d'implémenter cette fonctionnalité. Peut être plus tard ?

### Fin de partie (Stats)


## Implémentation

### Model



### GUI
Nous avons décidé de faire l'interface graphique avec la librairie **Java Swing**.

Tout d'abord, l'intégralité des classes pour l'interface graphique ont été placé dans un package **view**. On y retrouve plusieurs classes tels que:
- Window
- HomeView
- GameView
- StatsView

#### Window
La classe Window est celle qui permet d'ouvrir une fenêtre pour notre jeu. Elle hérite de **JFrame**. On y place ensuite à l'intérieur des **JPanel**. Chaque JPanel correspond à une page pour notre jeu, on a notamment:
- HomeView: La page d'accueil
- GameView: La page de Jeu, qui s'adapte selon le type de "Game" passé en paramètre (NormalGame, ChallengeGame, MultiplayerGame)
- StatsView: La page des statistiques (après une partie)

Chacune de ces classes a donc une instance de **Window** pour pouvoir passer d'une page à une autre facilement (setHomeView, setNormalMode...)

Enfin, cette classe gère également l'appuie des touches du clavier à l'aide d'un **KeyAdapter** où on a pu redefinir la méthode **keyPressed(KeyEvent e)**.
Pour que cette méthode fonctionne, nous devons toujours retenir une instance de **GameView** qui représente le page du jeu en cours. On peut alors vérifier au début de **keyPressed** de quel jeu il s'agit et donc adapter la réaction des touches selon le type de jeu.

#### HomeView
La classe **HomeView** est une classe de la **view**. Cette classe représente la page d'accueil. L'utilisateur peut alors choisir un mode de jeu et lancer la partie.

#### GameView
**GameView** est la classe qui permet d'afficher le jeu actuel (Normal ou Challenge). Cette classe est relié en permanence à son model (**Game**). Toute modification du jeu à travers **GameView** est tout de suite repercuté sur son model. Cela pourrait nous permettre d'implémenter l'interface **Serializable** sur la classe **Game** et d'enregistrer notre partie (notre objet model Game) facilement. Nous avons d'ailleurs toujours fait attention à bien séparer le modèle, vue tout au long du projet.

Dans **GameView** se trouve notamment la classe **GameTextArea**. C'est un JPanel
qui vient se placer au milieu de l'écran et qui affiche les prochains mots à écrire. 

Cette classe contient plusieurs attributs notables:
- Queue<WordView> wordViewQueue: la queue des mots qu'on vient ajouter au JPanel (méthode **add**)
- WordView actualWord: Le mot actuel, celui sur lequel nous sommes en train d'écrire

#### StatsView


### Remarques diverses
- sealed permits
- fabriques static
