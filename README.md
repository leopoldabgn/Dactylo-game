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
_______________________________________________
Le page du mode challenge est assez similaire à celle du mode normal. Mais il y a tout de même plusieurs changements. Cette fois, les mots arrivent avec une certaine vitesse qui augmente au fil des niveaux. Tous les 100 mots, le niveau augmente et les mots arrivent de plus en plus vite (selon cette fonction 3 * 0.9^n). Le joueur dispose tout de même de plusieurs en cas d'échec, pour pouvoir continuer la partie. Voici comment sont disposés les différents éléments :
- Le niveau et le nombre de vie du joueur sont affichées en bas à gauche de l'écran
- Le nombre de mots écrits est également affiché

![Mode Challenge](readme_resources/challenge_mode_in_progress.png)
*Voici un exemple de déroulement d'une partie avec le mode Challenge*

### Multiplayer Mode
______________________________________________
Nous n'avons malheureusement pas eu le temps d'implémenter cette fonctionnalité. Peut être plus tard ?

### Fin de partie (Stats)
______________________________________________
............

## Implémentation
Nous avons essayé de respecter le plus possible le pattern **MVC** et surtout la partie model/vue. 

### Model
___________
Toutes les classes du model ont été placé dans un package **model**. On y retrouve plusieurs classes telles que:
- Game (+ GameFactory, ChallengeGame, NormalGame, MultiplayerGame)
- Infos
- Stats
- WordQueue
- Word
  
#### Game
___________
[TODO] Expliquer comment fonctionne Game

#### Construction d'un Game (GameFactory)
**GameFactory** est une classe qui permet de créer facilement des objets **Game**. Pour cela, il suffit d'appeler la fabrique static **getGame**. Elle prend:
- Le type de jeu qu'on veut creer (sous forme d'enum **GameType**)
- La liste des joueurs (ArrayList<Player>)
Elle appelle alors le consctructeur de ChallengeGame, NormalGame, etc... 
(Toutes ces classes héritent de Game)

#### ChallengeGame
[TODO] Expliquer setLifes...etc
#### NormalGame
[TODO] Expliquer setDuration...etc

#### MultiplayerGame
[TODO]...

#### Infos
___________
[TODO] Parler du chronomètre. Que Infos représente les informations qu'on voit durant la partie dans le petit cadran. Le nombre de mots écrit etc...

#### Stats
___________
[TODO] @leopoldabgn: Adapter le texte ci-dessous. Parler de CharSets, la liste dans Player et des Stats...
```java
// On retient le temps a chaque fois qu'un caractère est tapé
// Si un caractère est effacé on met erased à true
// A la fin on regarde tous les mots entièrement bien écrit
// Pour chacun des mots, on regarde le temps entre chaque caractère utile
// Sachant qu'un caractère utile est un caractère qui n'a pas été effacé et
// donc qui n'a pas erased à true. Il faut que erased soit faux

// On récupère les nouveaux caractères utiles écrit à chaque fois qu'on termine un mot
// On concatène cette liste à la liste des caractères utiles dans la classe Player
// On peut encuiste calculer a chaque fois le temps entre deux caractères utiles à la suite puis on calcul
```

#### WordQueue
___________
[TODO]

#### Word
___________
[TODO]

### GUI
___________
Nous avons décidé de faire l'interface graphique avec la librairie **Java Swing**.

Toutes les classes de l'interface graphique ont été placé dans un package **view**. On y retrouve plusieurs classes telles que:
- Window
- HomeView
- GameView
- WordView
- StatsView

#### Window
___________
La classe Window est celle qui permet d'ouvrir une fenêtre pour notre jeu. Elle hérite de **JFrame**. On y place ensuite à l'intérieur des **JPanel**. Chaque JPanel correspond à une page pour notre jeu, on a notamment:
- HomeView: La page d'accueil
- GameView: La page de Jeu, qui s'adapte selon le type de "Game" passé en paramètre (NormalGame, ChallengeGame, MultiplayerGame)
- StatsView: La page des statistiques (après une partie)

Chacune de ces classes a donc une instance de **Window** pour pouvoir passer d'une page à une autre facilement (setHomeView, setNormalMode...)

Enfin, cette classe gère également l'appuie des touches du clavier à l'aide d'un **KeyAdapter** où on a pu redefinir la méthode **keyPressed(KeyEvent e)**.
Pour que cette méthode fonctionne, nous devons toujours retenir une instance de **GameView** qui représente le page du jeu en cours. On peut alors vérifier au début de **keyPressed** de quel jeu il s'agit et donc adapter la réaction des touches selon le type de jeu.

#### HomeView
___________
La classe **HomeView** est une classe de la **view**. Cette classe représente la page d'accueil. L'utilisateur peut alors choisir un mode de jeu et lancer la partie. La page a été crée à l'aide plusieurs JPanel et d'un **BorderLayout**. Il en est de meme pour les pages **GameView** et **StatsView**

[TODO] Enfin, on utilise la classe **HomeController** pour ...

#### GameView
___________
**GameView** est la classe qui permet d'afficher le jeu actuel (Normal ou Challenge). Cette classe est relié en permanence à son model (**Game**). Toute modification du jeu à travers **GameView** est tout de suite repercuté sur son model. Cela pourrait nous permettre d'implémenter l'interface **Serializable** sur la classe **Game** et d'enregistrer notre partie (notre objet model Game) facilement. Nous avons d'ailleurs toujours fait attention à bien séparer le modèle et la vue tout au long du projet.

Dans **GameView** se trouve notamment la classe **GameTextArea**. C'est un JPanel
qui vient se placer au milieu de l'écran et qui affiche les prochains mots à écrire. 

Cette classe contient plusieurs attributs notables:
- Queue<WordView*> wordViewQueue: la queue des mots qu'on vient ajouter au JPanel (méthode **add**)
- WordView* actualWord: Le mot actuel, celui sur lequel nous sommes en train d'écrire
Elle contient de plus, plusieurs méthodes pour mettre à jour la Queue du model et de la vue. Pour ajouter/retirer des mots facilement. On appelle notamment ces méthodes depuis **keyPressed** de Window.

***WordView** est la représentation d'un **Word** (model) dans la view. Vous pouvez voir la description de cette classe un peu plus loin dans le document.

Pour finir, elle implémente l'interface **ActionListener** avec la méthode **actionPerformed**. On gère a travers cette méthode:
- Le chronomètre de la partie (Début, Fin, Temps restant)
- Le temps restant (en mode *Normal*). Si le temps est écoulé, on génére les statistiques du joueur et on lance la page des statistiques (**StatsView**)
- Le nombre de vies du joueurs (en mode *Challenge*). Si le joueur est mort, on termine la partie
- Le niveau du joueur (en mode *Challenge*). Selon son niveau, on gère un deuxième chronomètre qui aura une durée de plus en plus basse au fil des niveaux. Si le temps de ce chrono est écoulé, on ajoute un mot à la file (si file pleine, on force l'appuie de la barre ESPACE).

#### WordView
___________
**WordView** est une classe qui hérite de **JLabel**. Elle a un attribut **Word** qui est le mot qu'elle représente. Cette classe est une des classes les plus importantes, car c'est elle qui permet de changer en live la couleur d'un mot. Elle contient notamment une méthode **getColoredLabel** qui:
- Compare, lettre par lettre le mot représenté par **Word** (attribut *content*) et le mot que le joueur à tapé (attribut *pushContent*)
- Renvoie une String en **HTML** où chaque lettre est dans une balise de la forme:
```html
<!-- Chaque lettre est dans une balise <font> avec une certaine couleur -->
<font color=????></font>
<!-- Voici un exemple pour le mot "rien" où le joueur a écrit "ria"
On observe que le "r" et le "i" on été bien écrit (ils sont en noirs, #000).
Le "a" est rouge (#cf1f1f), c'est une erreur.
Le "n" est en gris (#888) parce qu'il n'a pas été encore écrit. -->
<html><font color=#000>r</font><font color=#000>i</font><font color=#cf1f1f>a</font><font color=#888>n</font></html>

<!-- On aurait pu imaginer une fonction créant le minimum de balise <font> en
mettant plusieurs lettres dans la même balise (si elles ont la même couleur).
Ici, les lettres "ri" auraient pu être dans la même balise -->
<html><font color=#000>ri</font><font color=#cf1f1f>a</font><font color=#888>n</font></html>
```
Ce qu'il faut savoir maintenant, c'est qu'un JLabel peut interpréter du **HTML** ! C'est donc grâce à cette technique que nous pouvons afficher un mot avec des couleurs différentes pour chaque lettre.

#### StatsView
___________
**StatsView** est la page qui s'affiche à la fin d'une partie (**Normal** ou **Challenge**). Elle contient un attribut **Stat** avec toutes les informations à affichées à l'écran: la *fréquence*, le nombre de mots par minute (*wpm*) et enfin la *précision*.

[TODO] Enfin, on utilise la classe **StatsController** pour ...

### Remarques diverses
___________
Une attention particulière a été donné pour appliquer les notions vu pendant les cours de CPO5 de L3 :
- sealed permits: [TODO] Game
- fabriques static: [TODO]
- Immuabilité (en partie):
- lambda expression
- stream