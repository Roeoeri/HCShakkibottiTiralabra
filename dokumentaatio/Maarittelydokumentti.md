# Shakkibotti määrittelydokumentti

## Algoritmit ja tietorakenteet

Shakkibotti toteutetaan alpha-beta pruning algoritmilla. Tietorakenteista työtä varten pitää todennäköisesti ainakin toteuttaa jonkinlainen taulukkolista, joskaan tässä vaiheessa työtä kaikki tarpeelliset tietorakenteet eivät ole vielä selvillä. Alpha-beta pruningin pariksi tulee myös toteuttaa heurestiikka-algoritmi, jonka avulla voidaan arvioida erilaisia pelitilanteita ilman, että koko pelipuuta tarvitsee käydä loppuun. Heurestiikaksi tässä tapauksessa ajateltiin toteuttaa algoritmi, joka arvioi pelitilannetta pöydällä olevan materiaalin, sekä materiiaalin sijainnin perusteella. Materiaalia eli nappuloita arvioidaan niiden arvokkuuden perusteella ja nappuloiden sijaintia arvioidaan ns. "piece square table"-metodilla, jossa jokaisella nappulalla on tietyssä ruudussa oma arvonsa.

## Ratkaistava ongelma

Työn tarkoituksena on toteuttaa algoritmi, jonka avulla tietokone voi pelata shakkia ihmistä tai itseään vastaan. Alpha-beta pruning algoritmia käytetään yleisesti ratkaisemaan monia kahden pelaajan pelejä, joten se valikoitui käytettäväksi algoritmiksi myös tässäkin työssä. Toimiakseen algoritmi vaatii taulukkolistaa tallentamaan pelitilanteita vastaavia solmuja, sekä heuristiikka-algoritmia arvioimaan pelitilanteiden arvoja. Materiaalin ja sen sijaintiin perustuva heuristiikka valittiin sen helpon toteutuksen ja nopean toiminnan takia.

## Syöte

Syötteenään algoritmi saa pelitilannetta vastaavan 8x8 taulukon, jonka jokaiseen soluun on merkitty solua vastaavaan ruudun sisältö. Algoritmi lähtee sitten käymään läpi syötteen lapsia, eli pelitilanteita, joihin on mahdollista päästä syötteenä annetusta pelitilanteesta.

## Aikavaativuus

Pahimmassa tapauksessa algoritmin aikavaativuus on O(b^d) missä b on pelitilanteella olevien lapsien keskimääräinen määrä ja d haun syvyys. Pahin tapaus siis vastaa sitä, että joudutaan käymään läpi jokainen pelitilanne.

## Lähteet

Roos,Teemu. 2019. Introduction to Artificial Intelligence. Helsingin yliopisto. Luento To 12.9.2019.






