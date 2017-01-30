# What can you do with that skill?

* Alexa, sag meister eder er soll die steckdose ein schalten
* Alexa, sag meister eder er soll die steckdose aus schalten
* Alexa, frag meister eder wieviele leute in Baden wohnen
* Alexa, frag meister eder wieviele leute in St.Pölten leben
* Alexa, frag meister eder was ist die Bevölkerungsanzahl von Krems an der Donau
* Alexa, frag meister eder wieviele Leute wohnen in Baden
* Alexa, frag meister eder wann die nächste U-bahn fahrt
* Alexa, frag meister eder wann der nächste Zug geht

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

# Data Sources

The data is based on data sets provided by
 
https://www.data.gv.at/

# Run locally

You need to disable the signature check:

Start with
```
-Dcom.amazon.speech.speechlet.servlet.disableRequestSignatureCheck=true
```
to disable signature check

To test POST a valid request json e.g.:
```json
{
  "session": {
    "sessionId": "SessionId.348a2a2b-889e-4341-a5f5-e8f78d7adf41",
    "application": {
      "applicationId": "amzn1.ask.skill.ab088d74-fbcc-4a85-b59f-eeccb07bf72c"
    },
    "attributes": {},
    "user": {
      "userId": "amzn1.ask.account.AEUO7KL74RV6XO3Q2JVNYTG6F66TPBKYRCEZN26JIT4VLFF5CACQEWQOIIZKQVZWLWCNBP4KQPIUZOPG4TU2XIZVNMLKJ67TAHZUUVGTQ7DMW3OCV5FT77EDSHSA3PCVIAV42BBB4EHJRPHNBEE22AUTX4TKT2FE3VKN2X3EMPY3SLEIAVZH2KJ5G6YGXZGRXTBMDTIBKIOCCHA"
    },
    "new": true
  },
  "request": {
    "type": "IntentRequest",
    "requestId": "EdwRequestId.c7c00404-32f3-4b3a-b588-43815610dac4",
    "locale": "de-DE",
    "timestamp": "2017-01-29T19:11:49Z",
    "intent": {
      "name": "MonitorIntent",
      "slots": {}
    }
  },
  "version": "1.0"
}
```
to

```
http://localhost:8081/meister-eder/
```

# Create a certificate for testing 

Create a Java keystore
```
https://www.sslshopper.com/article-how-to-create-a-self-signed-certificate-using-java-keytool.html
```
and a self signed certificate
```
https://www.digicert.com/easy-csr/keytool.htm
```
and import it e.g. with
```
http://keystore-explorer.org/
```
Copy the keystore in the resources folder and adapt the application.yml with name and passwords. Then you can use a spring profile e.g.
```
-Dspring.profiles.active=ssl
```
to start your local application using SSL.

See
```
https://developer.amazon.com/public/solutions/alexa/alexa-skills-kit/docs/testing-an-alexa-skill#create-a-private-key-and-self-signed-certificate-for-testing
```

for a full documenation.

# Remote Control Power plug
This just works if you have this skill running on e.g. your personal raspberry PI at home.

See 
```
https://github.com/xkonni/raspberry-remote
```
For Installation and adapt PowerPlugIntent with your settings