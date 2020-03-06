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






