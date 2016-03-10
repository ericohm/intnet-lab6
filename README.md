# intnet-lab6
Lab 6 in DD2390 at KTH.

## Connecting to the server
1. Visit `https://localhost:1234`
2. Accept the certificate

## Generating keystore, truststore and certificate
```
keytool -genkey -keyalg "RSA" -storepass ninaeric -validity 365 -alias lab6 -keystore lab6keystore
keytool -export -alias lab6 -storepass ninaeric -file lab6.cer -keystore lab6keystore
keytool -import -file lab6.cer -alias lab6 -keystore lab6truststore
```

## Listing the keystore
`keytool -list -storepass ninaeric -keystore lab6keystore`
