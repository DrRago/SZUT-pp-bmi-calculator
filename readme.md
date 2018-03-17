# Aufgabe

Erstellen Sie eine Klasse zur Berechnung des BMI und des Idealgewichts abhängig von der Größe, des Gewichtes und des Geschlechtes, das angegeben wurde. Der BMI wird nach der Formel BMI=mh2\text{BMI}=\frac{m}{h^2} berechnet, wobei mm das Körpergewicht in Kilogramm und hh die Körpergröße in Metern angibt.

Auf der Website [http://www.spiegel.de/gesundheit/ernaehrung/bmi-rechner-so-ermitteln-sie-ihren-body-mass-index-a-824673.html](http://www.spiegel.de/gesundheit/ernaehrung/bmi-rechner-so-ermitteln-sie-ihren-body-mass-index-a-824673.html) [(im Cache)](https://web.archive.org/web/20150905194440/http:/www.spiegel.de/gesundheit/ernaehrung/bmi-rechner-so-ermitteln-sie-ihren-body-mass-index-a-824673.html) finden Sie die Informationen zur Bewertung des BMI. Die [Tabellen sind außerdem unten](https://hb.itslearning.com/essay/read_essay.aspx?EssayID=322551#tabellen) noch einmal angegeben. Wenn kein Geschlecht und kein Alter angegeben sind, dann soll die erste Tabelle verwendet werden. Ist ein Geschlecht angegeben, soll die zweite nach Geschlechtern differenzierte Tabelle zugrunde gelegt werden. Ist ein Alter angegeben, so soll die jeweilige Tabelle entsprechend an das Alter angepasst werden. Legen Sie dabei für die ersten beiden Tabellen ein Alter von 25 bis 34 Jahren zugrunde.

Die Kategorien sollen jeweils mit der auf der Website angegebenen Bezeichnung benannt werden. Das Idealgewicht liegt genau in der Mitte der Kategorie Normalgewicht (das ist z. B. ein BMI von 21,5kgm221,5\frac{\text{kg}}{\text{m}^2} für Frauen und 22,5kgm222,5\frac{\text{kg}}{\text{m}^2} für Männer, wenn kein Alter angegeben wurde).

## Methoden

Die Klasse BmiCalcImpl soll die angegebene Schnittstelle BmiCalc implementieren. Es dürfen selbstverständlich weitere öffentliche oder private Methoden hinzugefügt werden und weitere Klassen verwendet werden.

## Hauptprogramm

Erstellen Sie ein einfaches Hauptprogramm, mit dem eine einzige BMI Berechnung auf der Konsole durchgeführt werden kann. Dieses Hauptprogramm soll verwendet werden, um die Klasse BmiCalc zu testen.

# GUI

Erstellen Sie ein Programm zur Berechnung des BMI und des Idealgewichts abhängig von der Größe, des Gewichtes und des Geschlechtes, das angegeben wurde. Verwenden Sie dafür zur Berechnung die für das letzte Protokoll erstelle Klasse. Stellen Sie sicher, dass Fehleingaben abgefangen und an den Benutzer mit einer Fehlermeldung zurückgemeldet werden.

Das Programm kann in Java oder in Python entwickelt werden. Eine Beispieloberfläche sehen Sie im folgenden Screenshot. Alternativ kann die Neuberechnung auch direkt nach jeder Änderung erfolgen. Der Button zur Berechnung kann dann entfallen.

