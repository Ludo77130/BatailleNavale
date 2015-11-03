create table Joueur(
	idJoueur int primary key,
	login varchar(32) unique,
	motDePasse varchar(32) not null,
	nom varchar(32),
	prenom varchar(32)
);

