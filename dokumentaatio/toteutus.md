# Toteutus

## Aikavaativuudet

### Siirtojen etsintä
Koska jokainen nappula käydään lävitse aina kuin mahdollisia siirtoja etsitään, aikavaativuus on O(n) jossa n on nappuloiden määrä, sillä itse nappuloiden mahdolliset siirrot on mahdollista saada vakioajassa.

### Minimax Alpha Beta-pruningilla
Pahimmassa tapauksessa aikavaativuus on O(b^d) missä b on pelitilanteesta seuraavien siirtojen määrän keskiarvo ja d on hakusyvyys. Kuitenkin koska myös siirrot lasketaan aikavaativuudella O(n) jokaisella hakusyvyydellä, on oikeampi pahimman tapauksen aikavaativuus O((nb)^d) missä n on nappuloiden määrä. Wikipedian perusteella parhaassa tapauksessa alpha-beta pruningin aikavaativuus laskee jopa O(sqrt(b^d)) asti [Wikipedia: Alpha-beta pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning), mikä tässä tapauksessa vastaisi aikavaativuutta O(sqrt((nb)^d)). Kuitenkin, koska työssä ei ole käytetty minkäänlaista pelipuun järjestämistä, on testeissä havaittu aikavaativuus lähempänä huonompaa skenaariota. 

