# Simulation centrée individus - TP

* Antoine Durigneux
* Quentin Warnant

## Introduction
Dans le cadre du cours de SCI, il nous a été demandé de développer un environnement de simulation en Java. 
Cet environnement doit permettre à l'utilisateur de suivre graphiquement l'évolution de population d'agents, 
soit des billes qui s'entre-choquent, soit l'évolution de requins/thons dans un espace clos. 


## Algorithmes
### Billes
L'environnement contenant les billes est un espace 2D non-torique leur permettant de se déplacer en horizontal, diagonal et vertical. 
Lorsque nous générons les billes, nous leur donnons un sens et une vitesse aléatoire déterminés au moment de la création. 
Notre algorithme fonctionne comme suit : 
si la bille peut se déplacer à sa vitesse (nombre de cases) et dans sa direction sans rencontrer une autre bille ou un bord, 
elle le fait, sinon elle se "rebondit" et nous inversons le sens de son déplacement. 

### Requin (prédateur) - Thon (Proie)
L'environnement contenant les requins et les thons est un espace 2D non-torique leur permettant de se déplacer en horizontal, diagonal et vertical.
Lorsque nous générons les requins, nous leur donnons un temps maximum de vie sans manger et un temps de reproduction. Même principe pour la génération
des thons bien qu'ils n'aient pas de temps sans manger. Pour les deux espèces, ils vieillissent au fil du temps et nous conservons donc l'âge de chaque individu
au cours de la simulation. 

A chaque nouvelle étape de la simulation et pour chaque individu, nous appelons la méthode décide qui fonctionne comme suit :
#### Pour le requin 
* Nous vérifions si le requin n'a pas mangé depuis trop longtemps : si c'est le cas, il meurt.
* Nous vérifions après s'il n'est pas en âge de se reproduire et si il dispose d'une place libre : si c'est le cas, nous créons un nouveau requin dans la grille
* Ensuite, nous regardons dans le voisinage du requin s'il peut manger un poisson 
* Enfin, nous regardons s'il peut se déplacer 
Dans notre algorithme, un requin ne peut faire qu'une seule de ces actions par tour de la simulation.

#### Pour le thon
* Nous vérifions si le thon est en âge de se reproduit et s'il dispose d'une place libre, si c'est le cas, nous créons un nouveau thon dans la grille.
* Nous vérifions s'il peut se déplacer dans la grille
De même que pour les requins, le thon ne peut effectuer qu'une seule action à la fois par tour.

### Attracteur - Poursuivant (Dijkstra)
L'environnement contenant les attracteurs et les poursuivants est un espace 2D non-torique permettant de se déplacer en horizontal, diagonal et vertical.
Lorsque nous générons l'environnement, nous créons un certain nombre de murs qui sont des obstacles à la progression des poursuivants qui se déplacent dans l'environnement pour attraper les attracteurs.
A chaque tour de la simulation, nous générons une carte des poids de l'environnement représentant respectivement le poids du chemin à partir des attracteurs. Ainsi, par exemple, les cases
voisines directes d'un attracteur ont un poids de 1. Celle qui entourent ces mêmes cases voisines ont un point de 2, etc. Lorsqu'un attracteur est attrapé par un poursuivant, les deux sont
supprimés de la liste des agents, et une nouvelle carte de poids est regénérée, devenant la nouvelle "feuille de route" pour les poursuivants restants. 

## Compilation du projet
* JDK > 1.7 demandé pour la compilation du projet

```bash 
javac *.java
```

## Exécution du projet
### Billes
```bash
java BilleSimulation tailleX tailleY tailleBilleCase nombreBilles nombreTours tempsArret
```
* tailleX, tailleY : la taille en X et Y de la fenêtre de simulation (pixels)
* tailleBileCase : la taille du côté d'une case représentant une bille (pixels)
* nombreBilles : le nombre de billes contenues dans la simulation
* nombreTours : le nombre de tours de la simulation avant qu'elle s'arrête
* tempsArret : le temps en ms entre chaque tour de la simulation

### Requin (prédateur) - Thon (Proie) 
```bash
java PoissonSimulation tailleX tailleY tailleCase nombreThon nombreRequins tempsReproductionThon tempsReproductionRequin tempsSansMangerRequin sleepTime
```

* tailleX, tailleY : la taille en X et Y de la fenêtre de simulation (pixels)
* tailleCase : la taille du côté d'une case représentant un individu (pixels)
* nombrePoissons : nombre de thons dans la simulation au départ
* nombreRequins : nombre de requins dans la simulation au départ
* tempsReproductionThon : temps après lequel un thon se reproduit (en tours de simulation)
* tempsReproductionRequin : temps après lequel un requin se reproduit (en tours de simulation)
* tempsSansMangerRequin : temps après lequel un requin meurt lorsqu'il n'a pas pu manger un thon
* sleepTime : le temps en ms entre chaque tour de la simulation

### Attracteur - Poursuivant (Dijkstra)
```bash
java Simulation tailleX tailleY tailleCase nombreAttracteur nombrePoursuivant nombreMur sleepTime
```

* tailleX, tailleY : la taille en X et Y de la fenêtre de simulation (pixels)
* tailleCase : la taille du côté d'une case représentant un individu (pixels)
* nombreAttracteurs : nombre d'attracteurs dans la simulation au départ
* nombrePoursuivants : nombre de requins dans la simulation
* nombreMurs : nombre de murs (obstacles) dans la simulation
* sleepTime : le temps en ms entre chaque tour de la simulation

#### Exemples de configuration

##### Attracteur - Poursuivant (Dijkstra)
```bash
java DijkstraSimulation 50 50 10 3 3 150 1000
```

* 3 attracteurs et 3 poursuivants
* 150 murs dans l'environnement

##### Requin (prédateur) - Thon (Proie)
###### En équilibre
```bash
java PoissonSimulation 200 200 4 4000 400 8 15 3 100
```

![alt tag](http://a23.imgup.net/Untitled28f0b.png)

###### En non-équilibre
```bash
java PoissonSimulation 200 200 4 4000 850 8 10 8 100
```

###### Résultats graphiques
Pour visualiser les graphiques résultant des simulations, il suffit d'ouvrir le fichier excel à la racine du dossier. La feuille de calcul est liée aux deux fichiers résultats requin.txt et thon.txt regénérés à chaque simulation.
