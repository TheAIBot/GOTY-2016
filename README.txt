Spillet startes gennem kommandoprompt ved at k�re den vedh�ftede jarfil. Dette g�res ved kommandoen:

java -jar GOTY-2016.jar

Med ovenst�ende kommando startes den avancerede version af spillet, som vises i et swing vindue. Tilf�jes et tal fra 3 til 100 som argument til sidst i ovenst�ende kommando p�begyndes det grundl�ggende spil i konsollen.

Har computeren et indbygget grafikkort kan OpenGL bruges til at forbedre ydelsen for renderingen af den avancerede del af spillet, ved n�r spillet startes skrive f�lgende kommando i stedet for:

java -Dsun.java2d.opengl=true -jar GOTY-2016.jar

--- Spilstyring ---
Som standard bruges W, A, S og D til at bev�ge void tilen rundt p� br�ttet. Q bruges til at skifte til color mode og E og R bruges til at zoome ind og ud. Spillet kan udover at bev�ge void tilen og cameraet ogs� s�tte spillet p� pause med mellemrum og man kan g� ud af spillet med escape.
