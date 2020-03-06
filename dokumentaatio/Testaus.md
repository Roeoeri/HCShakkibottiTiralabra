# Testausdokumentti

## Testikattavuusraportti
[![codecov](https://codecov.io/gh/Roeoeri/HCShakkibottiTiralabra/branch/master/graph/badge.svg)](https://codecov.io/gh/Roeoeri/HCShakkibottiTiralabra)

## Mitä testattiin
### Pelimoottori
Pelimoottorin toteuttavaa MovementChecker-luokkaa yksikkötestattiin erittäin kattavasti Junitlla. Testisyötteenä käytettiin suurta määrää erilaisia kovakoodattuja pelitilanteita, jotka oli pyritty valitsemaa niin että ne paljastavat myös mahdollisimman hyvin erilaisia corner caseja (kuten pelilaudan ulkopuolelle menemisen). Rivikattavuus on sangen hyvä 98% sillä muutama itsestään selvä tilanne jätettiin testaamatta. Pelimoottoria testattiin myös manuaalisesti pelaamalla shakkia xBoardissa, eikä virheitä ainakaan pelaamisen aikana esiintynyt.

### ArrayList-toteutus
ArrayList-toteutusta eli chesslistiä testattiin pienellä määrällä Junitin yksikkötestejä, joilla testattiin lähinnä että listaan lisääminen onnistuu. Loppu testaus suoritettiin integraatiotestauksena pelimoottorin ja shakkibotin kanssa. Rivikattavuus on hyvä noin 96%.

### Heurestiikka
Testaus suoritettiin integraatiotestauksena shakkibotin kanssa.

### ShakkiBotti
Tirabottia on testattu pienellä määrllä yksikkötestejä, jossa on tarkastettu että botti parsii siirrot UCI formaatissa, osaa pelata oikein erittäin ilmi selvissä tilanteissa ja palauttaa siirron 3 siirron hakusyvyydellä alle sekunnissa. Rivikattavuus on hieman huono 75 %. Bottia on myös testattu paljon käyttäjätasolla pelaamalla shakkia xBoardissa

## Suorituskykytestaus

Suorituskykyä testattiin luomalla 100 kpl satunnaisia pelitilanteita, ja ottamalla aikaa, kuinka kauan Shakkibotilla meni luoda siirto hakusyvyyksillä 1,2,3,4 ja 5. Jokaisella hakusyvyydellä siirrot luotiin valkoisen ja mustan pelaajan näkökulmasta ja otettiin ajoista keskiarvo. Tulokset on kuvattu oheisessa taulukossa.

| Hakusyvyys        | Kuinka kauan siirron luomisessa meni keskimäärin| 
| -------------     |:-----------------------------------------------:| 
| 1                 |8.143*10^-5 sekuntia         | 
| 2                 |9.216*10^-4 sekuntia         |  
| 3                 |0.0248 sekuntia              |
| 4                 |0.3082 sekuntia           |
| 5                 |7.7887 sekuntia           |

Kuten taulukosta voi päätellä, hakusyvyydellä 5 botti vie jo suhteellisen pitkän ajan päätöksen tekemiseen. Toisaalta shakin branching factor on 31-35 [Wikipedia: Branching factor](https://en.wikipedia.org/wiki/Branching_factor), jolloin hakusyvyydellä 5 joudutaan käymään pahimmassa tapauksessa 31^5 = 28629151 pelitilannetta läpi. Silti melkein kymmenen sekunnin mietintäaika on melko pitkä, joten esimerkiksi siirtojen luontia olisi pitänyt optimoida aikavaativuudesta O(n). 
                                        
### Toistettavuus
Testit on mahdollista toistaa ajamalla projektin juuressa `gradle performanceTest`






