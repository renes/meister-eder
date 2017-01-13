# What can you do with that skill?

Alexa, frag meister eder wieviele leute in Baden wohnen

Alexa, frag meister eder wieviele leute in St.Pölten leben

Alexa, frag meister eder was ist die Bevölkerungsanzahl von Krems an der Donau

Alexa, frag meister eder wieviele Leute wohnen in Baden

Alexa, frag meister eder wann die nächste U-bahn fährt

Alexa, frag meister eder wann der nächste Zug geht

# Wiener Linien Setting API 
Set an environment variable named

```
WIENER_LINIEN_KEY
```

which contains your developer key for the Wiener Linien Service.

Information is provided here:

https://www.data.gv.at/katalog/dataset/add66f20-d033-4eee-b9a0-47019828e698

and you can get a key here:

https://www.wien.gv.at/formularserver2/user/formular.aspx?pid=3b49a23de1ff43efbc45ae85faee31db&pn=B0718725a79fb40f4bb4b7e0d2d49f1d1

You also need to set this environment variable for your lambda in AWS.

# How to deploy the skill

Build the skill module

```
cd skill
mvn clean package assembly:single
```

Take the
```
target/skill-1.0-jar-with-dependencies.jar
```

and deploy it to your AWS lambda.

# Data Sources

The data is based on data sets provided by
 
https://www.data.gv.at/
