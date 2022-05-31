# Projet d'application web : r/place

Laurent FAINSIN - Damien GUILLOTIN - Tom HEURTEBISE - Pierre-Eliot JOURDAN

## Présentation

Pour introduire le sujet de notre projet il convient de vous expliquer ce qu’est l’évènement qui s’est tenu sur Reddit intitulé r/place. Afin de vous expliquer le principe de ce dernier voici une citation provenant d’un article de France3. "Connaissez-vous r/place ? Probablement pas. Connaissez-vous Reddit ? Non plus ? Commençons par là. Il s’agit d’un mélange entre un réseau social et un forum de discussion, dont l’usage n’est pas très répandu en France (le sous-Reddit r/France est néanmoins populaire, plein d’échanges intéressants et variés). Le 1er avril 2017, Reddit lançait un poisson d’avril doublée d’une expérimentation sociale : r/place. Une toile vierge de 1.000.000 de pixels. Les pixels, ce sont des petits carrés colorés ou non, des "atomes" en quelque sorte, qui composent les images apparaissant sur un écran. Le coloriage de chacun de ces pixels est possible pour n’importe qui de par le monde, dans le respect d’une limitation horaire de cinq minutes."

Pour notre projet nous avons décidé de créer un site web présentant une toile vierge divisée en un grand nombre de pixel. Comme pour le r/place chacun peut colorier un pixel. La différence fondamentale avec ce dernier est que notre site propose chaque pixel à la vente. Il y a ainsi un système d’achat et de vente de pixel via une monnaie virtuelle (pouvant être alimentée par de l’argent réel).

## Fonctionnalités

- Créer un compte/s’identifier :
Chaque utilisateur peut se créer un compte et s’identifier pour pouvoir acheter/vendre ses pixels. Notez que pour voir la fresque il ne sera pas nécessaire de se connecter.

- Gérer son portefeuille virtuel :
Chaque utilisateur dispose d’un portefeuille contenant son solde. Il peut augmenter le solde de cette monnaie en effectuant des paiements.

- Voir le détail de chaque pixel :
En passant la souris sur un pixel n’importe qui peut voir les informations liées à ce dernier (propriétaire, description)

- Acheter un pixel :
Un utilisateur connecté et avec un solde suffisant peut acheter un pixel. On lui propose alors de modifer les informations de ce dernier.1er

- Modifier un pixel :
La modification de pixels ne peut évidement se faire que sur des pixels que l'on possède. On peut alors choisir la couleur, le prix et la description de ses pixels.

## Architecture

Ce projet utilise wildfly, le successeur de jboss, pour la partie backend.
Le frontend est uniquement construit à partir de fichiers html, css et js. Ces fichiers sont envoyés à l'utilisateur via une servlet.
Nous avons choisi de ne pas utiliser jdbc, mais d'utiliser mariadb, simplement par comfort d'utilisation.
L'entièreté du projet peut-être lancé en une seule commande via un docker-compose.
Si vous obtenez des erreurs par rapport à lombok, veuillez checkez la page suivante: https://github.com/freefair/gradle-plugins

## Design

Nous avons réalisé notre page web en "one page". Nous proposons une page unique comportant la fresque de pixels avec une barre de navigation en haut. Sur cette barre il y a les boutons liés à la navigation (`Login`, `Signup`, `Wallet`, `Logout`). Les différents sous-menu se présentent sous forme de popups. De même pour l'achat et la modification de pixel, cela se fait directement via une popup lors du clique sur un pixel.


## Conclusion

Nous n'avons pas pu développer l'ensemble des fonctionalités que nous avions pensées, par manque de temps, mais surtout pas complexité.
Nous sommes cependant satisfait du résultat, puisque nous jugeons être allé plus loin que les TP.
