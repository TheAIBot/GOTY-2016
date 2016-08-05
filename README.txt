Spillet startes gennem kommandoprompt ved at køre den vedhæftede jarfil. Dette gøres ved kommandoen:

java -jar GOTY-2016.jar

Med ovenstående kommando startes den avancerede version af spillet, som vises i et swing vindue. Tilføjes et tal fra 3 til 100 som argument til sidst i ovenstående kommando påbegyndes det grundlæggende spil i konsollen.

Har computeren et indbygget grafikkort kan OpenGL bruges til at forbedre ydelsen for renderingen af den avancerede del af spillet, ved når spillet startes skrive følgende kommando i stedet for:

java -Dsun.java2d.opengl=true -jar GOTY-2016.jar

--- Spilstyring ---
Som standard bruges W, A, S og D til at bevæge void tilen rundt på brættet. Q bruges til at skifte til color mode. E og R bruges til at zoome ind og ud og T, F, G og H bruges til at bevæge kameraet rundt på brættet. Spillet derudover sættes på pause med mellemrum og man kan gå ud af spillet med escape.
